package com.geekband.myapp07;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by SUN on 2016/6/27.
 */
public class HttpUtil {

    public static String HTTP_URL = "https://api.heweather.com/x3/weather?cityid=CN101020100&key=392a54c3dd624164acaf4849322d70ce";

    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(address);
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
//                    conn.getDoInput(true);
//                    conn.getDoOutput(true);
                    InputStream is = conn.getInputStream();
                    StringBuilder response = new StringBuilder();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String line;
                    while((line = reader.readLine()) != null){
                        response.append(line);
                    }

                    if (listener != null){
                        //回调onFinish方法
                        listener.onFinish(response.toString());
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                    if (listener != null){
                        //回调onError
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }
}
