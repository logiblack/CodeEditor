package com.example.a14779.codeeditor.Controller;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by liangtao on 18-1-24.
 */

public class FileHelper {
    public static FileHelper instance = new FileHelper();

    private FileHelper(){

    }

    public static void saveFile(String fileName, String content, String filePath) throws IOException {
        File file = new File(filePath+fileName);
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream outputStream = new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(content.getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
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

}
