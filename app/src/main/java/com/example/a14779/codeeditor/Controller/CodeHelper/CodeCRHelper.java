package com.example.a14779.codeeditor.Controller.CodeHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Message;
import android.util.Log;

import com.example.a14779.codeeditor.Controller.CommandHelper;
import com.example.a14779.codeeditor.Controller.FileHelper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by liangtao on 18-2-9.
 * 用于进行异步编译运行程序
 */

public class CodeCRHelper {
    @SuppressLint("StaticFieldLeak")
    public static CodeCRHelper instance = new CodeCRHelper();
    @SuppressLint("HandlerLeak")
    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0x104012:
                    Object[] obj1 = (Object[]) msg.obj;
                    RunCommandResult result1 = (RunCommandResult) obj1[0];
                    RunCallback callback1 = (RunCallback) obj1[1];
                    callback1.onRunning(result1);
                    break;
                case 0x104013:
                    Object[] obj2 = (Object[]) msg.obj;
                    RunCommandResult result2 = (RunCommandResult) obj2[0];
                    CompileCallback callback2 = (CompileCallback) obj2[1];
                    callback2.onCompiling(result2);
                    break;

            }
        }
    };
    private CodeCRHelper(){

    }


    public void Run(final String fileName, final RunCallback callback){
        @SuppressLint("SdCardPath") final String executableFilePath = "/data/user/0/com.example.a14779.codeeditor/tem";

        final String[] runCCommands = {"./exe"};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runtime runtime = Runtime.getRuntime();
                RunCommandResult result = new RunCommandResult();
                CommandHelper.instance.exec(runtime ,runCCommands, null, new File(executableFilePath), result);
                Message msg = new Message();
                Object[] object = new Object[2];
                object[0] = result;
                object[1] = callback;
                msg.obj = object;
                msg.what = 0x104012;
                handler.sendMessage(msg);

            }
        });
        thread.start();
    }

    public void Compile(Context context, String fileName, final CompileCallback callback){
        @SuppressLint("SdCardPath")
        final String gccPath = "/data/user/0/com.example.a14779.codeeditor/files/gcc/bin";
        @SuppressLint("SdCardPath")
        final String filePath = "/storage/emulated/0/Android/data/com.example.a14779.codeeditor/files/"+fileName;
        @SuppressLint("SdCardPath")
        final String homePath = "/data/user/0/com.example.a14779.codeeditor";
        //创建存放C语言可运行文件的文件夹
        File file = new File(homePath+"/tem");
        if (!file.exists()){
            file.mkdir();
        }
        File shellFile = new File(homePath+"/shell");
        if (!shellFile.exists()){
            shellFile.mkdir();
        }
        //创建编译C语言的脚本文件
        @SuppressLint("SdCardPath")
        String compileC_sh = gccPath+"/aarch64-linux-android-gcc -fPIE -pie "+filePath+" -o ./exe";
        final File compile_c_sh = new File(homePath+"/shell/compileC.sh");
        if (!compile_c_sh.exists()){
            FileHelper.instance.saveFile("compileC.sh", compileC_sh, homePath+"/tem");
        }
        final String[] CompileCCommands = {"./shell/compileC.sh"};
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Runtime runtime = Runtime.getRuntime();
                RunCommandResult result = new RunCommandResult();
                CommandHelper.instance.exec(runtime, "chmod -R 711 "+"./shell", null, new File(homePath), null);
                CommandHelper.instance.exec(runtime, CompileCCommands, null, new File(homePath+"/shell"), result);
                Message msg = new Message();
                Object[] object = new Object[2];
                object[0] = result;
                object[1] = callback;
                msg.obj = object;
                msg.what = 0x104013;
                handler.sendMessage(msg);
            }
        });
        thread.start();
    }
}
