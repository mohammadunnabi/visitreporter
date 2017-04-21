package com.prangroup.VisitReporter.Utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Manik on 4/20/2017.
 */
public class Calender {
static int numter=0;
    public static String generateVisitNumber(){
        Random rnd = new Random();
        int n = 100000 + rnd.nextInt(900000);
        return "vt"+new SimpleDateFormat("yyMMdd").format(new Date())+n;
    }
    public static String currentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    public static String currentDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
}
