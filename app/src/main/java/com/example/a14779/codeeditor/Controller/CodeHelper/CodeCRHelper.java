package com.example.a14779.codeeditor.Controller.CodeHelper;

import android.annotation.SuppressLint;
import android.os.Message;

/**
 * Created by liangtao on 18-2-9.
 * 用于进行异步编译运行程序
 */

public class CodeCRHelper {
    public static CodeCRHelper instance = new CodeCRHelper();
    @SuppressLint("HandlerLeak")
    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
    private CodeCRHelper(){

    }

    public void Run(String code, RunCallback callback){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

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
}
