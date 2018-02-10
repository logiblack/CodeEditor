package com.example.a14779.codeeditor.Controller;

import android.content.Context;

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
    public static final String C = "c";
    public static final String JAVA = "java";
    public static final String CPP = "cpp";

    private FileHelper(){

    }

    public static void saveFile(String fileName, String content, String filePath){
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void readFile(String fileName, String result, String filePath) throws IOException {
        File file = new File(filePath+fileName);
        result = "";
        if (!file.exists()){
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()){
            result += scanner.nextLine();
        }
    }

    public static void deleteFile(String fileName, String result, String filePath){
        File file = new File(filePath+fileName);
        if (!file.exists()){
            return;
        }
        file.delete();
    }

    public static long getFileSize(String fileName, String filePath){
        long size = 0;
        return size;
    }

    public static String getFileType(String fileName){
        Pattern CPattern = Pattern.compile(".*\\.c");
        Pattern JavaPattern = Pattern.compile(".*\\.java");
        Pattern CppPattern = Pattern.compile(".*\\.cpp");
        if(CPattern.matcher(fileName).matches()) return C;
        else if (CppPattern.matcher(fileName).matches()) return CPP;
        else if (JavaPattern.matcher(fileName).matches()) return JAVA;
        return "";
    }
}
