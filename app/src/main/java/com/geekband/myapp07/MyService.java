package com.geekband.myapp07;

import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.geekband.myapp07.javabean.Heweatherdata;
import com.geekband.myapp07.javabean.WeatherData;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by SUN on 2016/6/27.
 */
public class MyService extends Service{

    private Timer timer = new Timer();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                HttpUtil.sendHttpRequest(HttpUtil.HTTP_URL, new HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Gson gson = new Gson();
                        WeatherData weatherData = gson.fromJson(response, WeatherData.class);
                        Heweatherdata heweatherdata = weatherData.getHeweatherdata().get(0);
                        String cityName = heweatherdata.getBasic().getCity();
                        String date = heweatherdata.getDailyForecast().get(0).getDate();
                        String dayWeather = heweatherdata.getDailyForecast().get(0).getCond().getTxt_d();
                        String nightWeather = heweatherdata.getDailyForecast().get(0).getCond().getTxt_n();
                        String minTmp = heweatherdata.getDailyForecast().get(0).getTmp().getMin();
                        String maxTmp = heweatherdata.getDailyForecast().get(0).getTmp().getMax();

                        String tmp = minTmp + " ~ " + maxTmp;

                        ContentResolver resolver = getContentResolver();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("city",cityName);
                        contentValues.put("date",date);
                        contentValues.put("day_weather",dayWeather);
                        contentValues.put("night_weather",nightWeather);
                        contentValues.put("tmp",tmp);


                        Cursor c = resolver.query(Uri.parse(MyContentProvider.URI),null,null,null,null);
                        if(c.getCount() == 0){
                            resolver.insert(Uri.parse(MyContentProvider.URI),contentValues);
                            Log.i("info","service插入了表");
                        }else{
                            resolver.update(Uri.parse(MyContentProvider.URI),contentValues,"_id=?",new String[]{"1"} );
                            Log.i("info","service更新了表");

                        }
                        c.close();
                        Log.i("info","后台定时刷新服务执行 msg= " + response);


                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
            }
        }, 10000, 10000);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
