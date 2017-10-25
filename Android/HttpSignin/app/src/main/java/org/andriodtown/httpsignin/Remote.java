package org.andriodtown.httpsignin;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by user on 2017-10-16.
 */

public class Remote {

    public static String sendPost(String address, String json){
        String result = "";
        try{
            URL url = new URL(address);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            // 데이터를 전송
            con.setDoOutput(true);
            OutputStream os = con.getOutputStream();
            os.write(json.getBytes());
            os.flush();
            os.close();


            if(con.getResponseCode() == HttpURLConnection.HTTP_OK){
                // 여기서 부터는 파일에서 데이터 가져오는 것과 도일
                InputStreamReader isr = new InputStreamReader(con.getInputStream());
                BufferedReader br = new BufferedReader(isr);
                String temp = "";
                while ((temp = br.readLine()) != null) {
                    result += temp;
                }
                br.close();
                isr.close();
            }else{

            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static String getData(String s) {
            final StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(s);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                // 통신이 성공인지 체크
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 여기서 부터는 파일에서 데이터를 가져오는 것도 동일
                    InputStreamReader isr = new InputStreamReader(con.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String temp = "";
                    while ((temp = br.readLine()) != null) {
                        result.append(temp).append("\n");
                    }
                    br.close();
                    isr.close();
                } else {
                    Log.e("ServerError", con.getResponseCode() + "");
                }
                con.disconnect();
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            return result.toString();
        }
    }
