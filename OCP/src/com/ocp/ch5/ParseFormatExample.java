package com.ocp.ch5;

import java.nio.file.FileSystems;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * Created by helangovan on 7/26/16.
 */
public class ParseFormatExample {

    public static void main(String[] args) throws ParseException {
        DecimalFormat one = new DecimalFormat("#,#,##.####");
        System.out.println(one.format(1235678.3455d));

        DecimalFormat two = new DecimalFormat("0,00.00000");
        System.out.println(two.format(1235678.3455d));
        
        Date d = new Date();
        DateFormat s = DateFormat.getDateInstance(DateFormat.SHORT);
        System.out.println(s.format(d));

        DateFormat dtf = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.FULL);
        System.out.println(dtf.format(d));

        DateFormat shortFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        String str = "01/31/1986";
        Date date = shortFormat.parse(str);
        DateFormat fullFormat = DateFormat.getDateInstance(DateFormat.FULL,Locale.FRANCE);
        System.out.println(fullFormat.format(date));

        DateFormat dFormat;
        //Insert code here
        dFormat = DateFormat.getDateInstance(DateFormat.DEFAULT,Locale.UK);
        System.out.println(LocalDate.now());
        String fNow = dFormat.format(new Date());
        System.out.println(fNow);

        Date curDate = new Date();
        SimpleDateFormat df =
                new SimpleDateFormat("E, MMM d, yyyy", Locale.FRANCE);
        System.out.println(df.format(curDate));



    }
}
