package com.ocp.ResourceBundle;

import java.util.ListResourceBundle;

/**
 * Created by helangovan on 7/29/16.
 */

public class StatsBundle_en_CA extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "GDP", new Integer(88) },
            { "Population", new Integer(88) },
            { "Literacy", new Double(0.88) },
    };
}

