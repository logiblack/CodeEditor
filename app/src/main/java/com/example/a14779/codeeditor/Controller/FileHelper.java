package com.example.a14779.codeeditor.Controller;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Created by liangtao on 18-1-24.
 */

public class FileHelper {
    public static FileHelper instance = new FileHelper();
    public static final int C = 0x000001;
    public static final int JAVA = 0x000002;
    public static final int CPP = 0x000003;

    private FileHelper(){

    }

    public void saveFile(String fileName, String content, String filePath){
        File file = new File(filePath+"/"+fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            bufferedOutputStream.write(content.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDirectory(String fileName, String filePath){
        File file = new File(filePath+"/"+fileName);
        if (!file.exists()){
            file.mkdir();
        }
    }

    public String readFile(String fileName, String filePath){
        File file = new File(filePath+"/"+fileName);
        StringBuilder result = new StringBuilder();
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNext()){
                result.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public void deleteFile(String fileName, String result, String filePath){
        File file = new File(filePath+fileName);
        if (!file.exists()){
            return;
        }
        file.delete();
    }

    public void reName(File oldFile, String newName, Context context){
        File file = new File(oldFile.getPath()+"/"+newName);
        if (file.exists()){
            Toast.makeText(context, "修改失败,新命名的文件已存在", Toast.LENGTH_SHORT).show();
        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }
    }

    public static long getFileSize(String fileName, String filePath){
        long size = 0;
        return size;
    }

    public int getFileType(String fileName){
        Pattern CPattern = Pattern.compile(".*\\.c");
        Pattern JavaPattern = Pattern.compile(".*\\.java");
        Pattern CppPattern = Pattern.compile(".*\\.cpp");
        if(CPattern.matcher(fileName).matches()) return C;
        else if (CppPattern.matcher(fileName).matches()) return CPP;
        else if (JavaPattern.matcher(fileName).matches()) return JAVA;
        return 0;
    }


}
