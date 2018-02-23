package com.example.a14779.codeeditor.Controller;

import android.content.Context;

/**
 * Created by liangtao on 18-2-22.
 */

public class PluginInit {
    private static PluginInit instance = new PluginInit();

    private PluginInit(){

    }

    public static PluginInit getInstance(){
        return instance;
    }

    private void addGCC(Context context){
        Runtime runtime = Runtime.getRuntime();
    }

}
