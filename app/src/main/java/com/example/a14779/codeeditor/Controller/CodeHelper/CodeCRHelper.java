package com.example.a14779.codeeditor.Controller.CodeHelper;

import android.annotation.SuppressLint;
import android.os.Message;

import com.example.a14779.codeeditor.Controller.FileHelper;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by liangtao on 18-2-9.
 * 用于进行异步编译运行程序
 */

public class CodeCRHelper {
    public static CodeCRHelper instance = new CodeCRHelper();
    private String[] runCCommands = {"gcc -fPIE -pie file.c -o ./codeFile/file", "cd "};
    @SuppressLint("HandlerLeak")
    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private CodeCRHelper(){

    }

    public void Run(final String fileName, final RunCallback callback){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runtime runtime = Runtime.getRuntime();
                try {
                    Process process = runtime.exec(runCCommands);
                    RunResult result = new RunResult();
                    InputStream resInputStream = process.getInputStream();
                    InputStream errInputStream = process.getErrorStream();
                    result.setResult(getStringFromStream(resInputStream));
                    result.setErrorMessage(getStringFromStream(errInputStream));
                    callback.onRunning(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();
    }

    public void Compile(String code, CompileCallback callback){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        thread.start();
    }

    private String getStringFromStream(InputStream inputStream){
        StringBuilder result = new StringBuilder();
        Scanner scanner  = new Scanner(inputStream);
        while (scanner.hasNext()){
            result.append(scanner.nextLine());
        }
        return result.toString();
    }
}
