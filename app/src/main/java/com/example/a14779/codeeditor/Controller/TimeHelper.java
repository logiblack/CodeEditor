package com.example.a14779.codeeditor.Controller;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Created by liangtao on 18-1-29.
 */

public class TimeHelper {
    public static TimeHelper instance = new TimeHelper();

    private TimeHelper(){

    }

    public static String FormatTime(long time){
        String result = "";
        Date date = new Date(time);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("YY-MM-DD HH-mm-ss");
        result = dateFormat.format(date);
        return result;
    }
}
