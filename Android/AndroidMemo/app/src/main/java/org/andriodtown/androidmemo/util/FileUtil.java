package org.andriodtown.androidmemo.util;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by user on 2017-09-20.
 */

public class FileUtil {

    public static String read(Context context, String filename) throws IOException{
        // 원래 이 용도는 아님
        StringBuilder sb = new StringBuilder();
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
             fis = context.openFileInput(filename);
            //버프를 달고
             bis = new BufferedInputStream(fis);
            // 한번에 읽어올 버퍼양을 설정
            byte buffer[] = new byte[1024];
            // 현재 읽은양을 담는 변수설정
            int count = 0;
            // bis.read(buffer)는 설정 값 1024까지 읽어준다
            // 꽉 차면 -1 return
            while ((count = bis.read(buffer)) != -1) {
                String data = new String(buffer, 0, count);
                sb.append(data);
            }
        }catch(IOException e){
            throw e;
        }
        try {
            bis.close();
            fis.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void write(Context context, String filename, String content) throws IOException {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(filename,MODE_PRIVATE);
            fos.write(content.getBytes());
        }catch(Exception e){
            throw e;
        }finally{
            if(fos!=null){
                try{
                    fos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
