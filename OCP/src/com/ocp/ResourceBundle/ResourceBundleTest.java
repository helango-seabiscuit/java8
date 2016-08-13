package com.ocp.ResourceBundle;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by helangovan on 7/29/16.
 */
public class ResourceBundleTest {


    public static void main(String[] args) {
        Locale[] supportedLocales = {
                Locale.FRENCH,
                Locale.GERMAN,
                Locale.ENGLISH
        };
        System.out.println(supportedLocales[0]);

        ResourceBundle rb = ResourceBundle.getBundle("LabelsBundle",supportedLocales[0]);
        System.out.println(rb.getString("s1"));

        rb = ResourceBundle.getBundle("LabelsBundle",new Locale("es","US"));
        System.out.println(rb.getLocale());

        Locale[] supportedLocalesClass = {
                new Locale("en", "CA"),
                new Locale("ja", "JP"),
                new Locale("fr", "FR")
        };

        ResourceBundle stats = ResourceBundle.getBundle("com.ocp.ResourceBundle.StatsBundle", new Locale("en","CA"));
        System.out.println(stats.getObject("Literacy"));
        System.out.println(Locale.ROOT);
    }
}
