package com.marioaliaga.tipcalc;

import android.app.Application;

/**
 * Created by maliaga on 12-06-16.
 */
public class TipCalcApp extends Application {
    public static String getAboutUrl() {
        return ABOUT_URL;
    }

    private static final String ABOUT_URL = "http://www.marioaliaga.com";


}
