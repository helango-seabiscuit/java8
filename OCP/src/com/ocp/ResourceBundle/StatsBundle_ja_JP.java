package com.ocp.ResourceBundle;

import java.util.ListResourceBundle;

/**
 * Created by helangovan on 7/29/16.
 */

public class StatsBundle_ja_JP extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }

    private Object[][] contents = {
            { "GDP", new Integer(21300) },
            { "Population", new Integer(125449703) },
            { "Literacy", new Double(0.99) },
    };
}

