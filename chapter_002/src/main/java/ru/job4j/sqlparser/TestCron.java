package ru.job4j.sqlparser;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TestCron implements Job {
    JobDetail job = JobBuilder.newJob(TestCron.class).withIdentity("TestCron", "group").build();
    Trigger trigger = TriggerBuilder.newTrigger().withIdentity("TestCron", "group")
            .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ?")).build();

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("testCron");
    }

//    public static void main(String[] args) throws SchedulerException {
//        TestCron testCron = new TestCron();
//        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//        scheduler.start();
//        scheduler.scheduleJob(testCron.job, testCron.trigger);
//    }
}
