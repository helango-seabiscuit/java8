package com.ocp.ResourceBundle;

import java.util.ListResourceBundle;

/**
 * Created by helangovan on 7/29/16.
 */

public class StatsBundle_fr_FR extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "GDP", new Integer(44) },
            { "Population", new Integer(44) },
            { "Literacy", new Double(0.44) },
    };
}

