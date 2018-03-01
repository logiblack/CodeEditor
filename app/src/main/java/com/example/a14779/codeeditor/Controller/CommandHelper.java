package com.example.a14779.codeeditor.Controller;

import android.util.Log;

import com.example.a14779.codeeditor.Controller.CodeHelper.RunCommandResult;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by liangtao on 18-3-1.
 */

public class CommandHelper {
    public static CommandHelper instance = new CommandHelper();

    private CommandHelper(){

    }

    public void exec(Runtime runtime, String[] cmdArray, String[] envp, File dir, RunCommandResult result){
        try {
            Process process = runtime.exec(cmdArray, envp, dir);

            InputStream resInputStream = process.getInputStream();
            InputStream errInputStream = process.getErrorStream();
            if (result != null){
                result.setResult(getStringFromStream(resInputStream));
                result.setErrorMessage(getStringFromStream(errInputStream));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exec(Runtime runtime, String command, String[] envp, File dir, RunCommandResult result){
        try {
            Process process = runtime.exec(command, envp, dir);

            InputStream resInputStream = process.getInputStream();
            InputStream errInputStream = process.getErrorStream();
            if (result != null){
                result.setResult(getStringFromStream(resInputStream));
                result.setErrorMessage(getStringFromStream(errInputStream));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getStringFromStream(InputStream inputStream){
        StringBuilder result = new StringBuilder();
        Scanner scanner  = new Scanner(inputStream);
        while (scanner.hasNext()){
            result.append(scanner.nextLine()).append("\n");
        }
        Log.i("..............", "getStringFromStream: "+result.toString());
        return result.toString();
    }
}
