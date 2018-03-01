package com.example.a14779.codeeditor.Controller;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by liangtao on 18-3-1.
 */

public class ZipFileHelper {
    public static void unzip(Context context, String zipFileName, String targetDir) {
        InputStream in = null;
        try {
            in = context.getAssets().open(zipFileName);

            File file = new File(targetDir);
            if (!file.isDirectory()) {
                throw new IOException("Invalid Unzip destination " + file.getPath());
            }
            if (in == null) {
                throw new IOException("InputStream is null");
            }

            ZipInputStream zipInputStream = new ZipInputStream(in);
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            byte[] buffer = new byte[1024];
            int count;
            while (zipEntry != null) {
                if (zipEntry.isDirectory()) {
                    file = new File(targetDir + File.separator + zipEntry.getName());
                    if (!file.exists()) {
                        file.mkdir();
                    }
                } else {
                    file = new File(targetDir + File.separator + zipEntry.getName());
                    if (!file.exists()) {
                        file.createNewFile();
                        FileOutputStream fout = new FileOutputStream(file);
                        while ((count = zipInputStream.read(buffer)) > 0) {
                            fout.write(buffer, 0, count);
                        }
                        fout.close();
                    }
                }
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
