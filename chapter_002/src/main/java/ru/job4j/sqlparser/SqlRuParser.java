package ru.job4j.sqlparser;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Medoev Ruslan (mr.r.m3@icloud.com).
 * @version $Id$.
 * @since 0.1.
 */

/**
 * Class for getting JAVA vacancies from http://www.sql.ru/forum/job-offers/.
 */

public class SqlRuParser implements Job {
    private LocalDateTime boundDate = LocalDate.of(2018, 1, 1).atStartOfDay();
    private final String REF = "http://www.sql.ru/forum/job-offers/";
    private Logger log = Logger.getLogger(SqlRuParser.class);
    public List<Vacancy> list = new ArrayList<>();
    private boolean firstStart = true;
    private BaseConnection bc;
    private int page = 1;

    public SqlRuParser() { }

    public SqlRuParser(File config) {
        bc = new BaseConnection(config);
    }

    /**
     * Method opens connecting with the site and obtain appropriate vacancies to add to database.
     */
    public void startParse() {
        log.info("Begin parsing ");
        LocalDateTime vacansyDate = LocalDate.now().atStartOfDay();
        if (!this.firstStart) {
            this.boundDate = bc.lastDate().toLocalDateTime();
        }
        this.firstStart = false;
        do {
            try {
                Document doc = Jsoup.connect(String.format("%s%d", REF, this.page++)).get();
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
                log.error("Parse error", e);
            }
        } while (vacansyDate.isAfter(this.boundDate));
        bc.addToBase(list);
    }

    /**
     * Get all vacancies from database.
     * @return List of vacancies
     */
    public List getVacancyList() {
        return bc.getVacancies();
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

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        File file = new File(jobExecutionContext.getJobDetail().getDescription());
        SqlRuParser parser = new SqlRuParser(file);
        parser.startParse();
    }

    public static void main(String[] args)  {
        File file = new File(args[0]);
        try (InputStream is = new FileInputStream(file)) {
            Properties properties = new Properties();
            properties.load(is);
            String timer = properties.getProperty("cron.time");
            JobDetail job = JobBuilder.newJob(SqlRuParser.class)
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
