package com.example.a14779.codeeditor.Controller;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

/**
 * Created by liangtao on 18-2-22.
 */

public class PluginInit {
    public static PluginInit instance = new PluginInit();
    public static String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();

    private PluginInit(){

    }

    public void addGCC(Context context){
        Runtime runtime = Runtime.getRuntime();
        String path = context.getExternalFilesDir("").getPath();
        try {
            String[] cmd = {"ls "+path, "cd "+path+"/gcc", "ls -l"};
            Process process = runtime.exec("cd "+path);
            StringBuilder sb = new StringBuilder();
            StringBuilder sbE = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = "";
            while ((line = reader.readLine()) != null){
                sb.append(line+"\n");
            }
            while ((line = reader1.readLine()) != null){
                sbE.append(line+"\n");
            }
            Log.i(TAG, "addGCC: *************"+sb.toString()+"\n" +
                    "error:"+sbE.toString()+"\n" +
                    "path:"+ path +"\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
