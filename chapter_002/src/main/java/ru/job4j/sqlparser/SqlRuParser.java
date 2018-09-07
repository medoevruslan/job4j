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
public class SqlRuParser {
    private final Parser parser = new Parser();

    public void init(String input) {
        parser.startQuartz(input);
    }

    public static void main(String[] args)  {
       new SqlRuParser().init(args[0]);
    }
}
