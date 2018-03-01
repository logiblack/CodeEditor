package com.example.a14779.codeeditor.Controller;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.a14779.codeeditor.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

import static android.content.ContentValues.TAG;

/**
 * Created by liangtao on 18-2-22.
 */

public class PluginInit {
    public static PluginInit instance = new PluginInit();
    @SuppressLint("SdCardPath")
    public static String HOMEPATH = "/data/user/0/com.example.a14779.codeeditor/";

    private PluginInit(){

    }

    public void addGCC(final Context context){

        final String path = context.getFilesDir().getPath();

        new Thread(new Runnable() {
            @Override
            public void run() {
                ZipFileHelper.unzip(context, "gcc.zip", path);
                Runtime runtime = Runtime.getRuntime();
                try {
                    Process process = runtime.exec("chmod -R 777 "+path+"/gcc");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
