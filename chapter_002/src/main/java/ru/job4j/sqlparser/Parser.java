package ru.job4j.sqlparser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Class for parsing vacancies
 */
public class Parser implements Job {
    private LocalDateTime boundDate = LocalDate.of(2018, 1, 1).atStartOfDay();
    private final String ref = "http://www.sql.ru/forum/job-offers/";
    private static final Logger LOG = Logger.getLogger(Parser.class);
    public List<Vacancy> list = new ArrayList<>();
    private boolean firstStart = true;
    private BaseConnection bc;
    private int page = 1;

    public Parser() { }

    public Parser(String config) {
        bc = new BaseConnection(config);
    }

    /**
     * Method opens connecting with the site and obtain appropriate vacancies to add to database.
     */
    public void startParse() {
        LOG.info("Begin parsing ");
        LocalDateTime vacansyDate = LocalDate.now().atStartOfDay();
        if (!this.firstStart) {
            this.boundDate = bc.lastDate().toLocalDateTime();
        }
        this.firstStart = false;
        do {
            try {
                Document doc = Jsoup.connect(String.format("%s%d", ref, this.page++)).get();
                Elements elements = doc.select("tr:has(td.postslisttopic)");
                for (Element element : elements) {
                    String name = element.child(1).text();
                    String author = element.child(2).text();
                    int views = Integer.valueOf(element.child(3).text());
                    String date = element.child(5).text();
                    vacansyDate = this.checkDate(date);
                    if (name.matches("^.*[Jj][Aa][Vv][Aa].*$")
                            && (!name.matches("^.*[Jj][Aa][Vv][Aa][Ss][Cc][]Rr][Ii][]Pp][Tt].*$"))
                            && vacansyDate.isAfter(this.boundDate)) {
                        list.add(new Vacancy(name, author, views, vacansyDate));
                    }
                }
            } catch (IOException e) {
                LOG.error("Parse error", e);
            }
        } while (vacansyDate.isAfter(this.boundDate));
        bc.addToBase(list);
    }

    /**
     * Check if date is correct.
     * @param date Date to check.
     * @return Correct date.
     */
    private LocalDateTime checkDate(String date) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd MMM yy");
        if (date.contains("сегодня")) {
            date = date.replace("сегодня",  LocalDate.now().format(format));
        } else if (date.contains("вчера")) {
            date = date.replace("вчера",  LocalDate.now().minusDays(1).format(format));
        }
        date = date.replace("май", "мая");
        return  LocalDateTime.parse(date, DateTimeFormatter.ofPattern("d MMM yy, HH:mm"));
    }

    /**
     * Get all vacancies from database.
     * @return List of vacancies
     */
    public List getVacancyList() {
        return bc.getVacancies();
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String file = jobExecutionContext.getJobDetail().getDescription();
        Parser parser = new Parser(file);
        parser.startParse();
    }

    public void startQuartz(String input) {
        File file = new File(input);
        try (InputStream is = new FileInputStream(input)) {
            Properties properties = new Properties();
            properties.load(is);
            String timer = properties.getProperty("cron.time");
            JobDetail job = JobBuilder.newJob(Parser.class)
                    .withIdentity("SqlRuParser", "groupOne")
                    .withDescription(file.getAbsolutePath()).build();
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("ParseTrigger", "groupOne")
                    .withSchedule(CronScheduleBuilder.cronSchedule(timer)).build();
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, trigger);
        } catch (IOException io) {
            io.printStackTrace();
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}
