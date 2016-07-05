package com.geekband.myapp07;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.geekband.myapp07.javabean.*;
import com.google.gson.Gson;

/**
 * Created by SUN on 2016/6/24.
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper{


    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    public MySQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);

    }

    @Override
    public void onCreate(final SQLiteDatabase db) {
        String sql = "create table if not exists weather_tb(_id integer primary key autoincrement,city text not null,date text not null,day_weather text not null," +
                "night_weather text not null,tmp text not null)";
        db.execSQL(sql);
        Log.i("info","helper建立了表");

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

                ContentValues contentValues = new ContentValues();
                contentValues.put("city",cityName);
                contentValues.put("date",date);
                contentValues.put("day_weather",dayWeather);
                contentValues.put("night_weather",nightWeather);
                contentValues.put("tmp",tmp);
                db.insert("weather_tb",null,contentValues);
                Log.i("info","helper插入了表");

            }

            @Override
            public void onError(Exception e) {

            }
        });

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
