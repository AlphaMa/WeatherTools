package com.geekband.myapp07;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geekband.myapp07.javabean.*;
import com.geekband.myapp07.javabean.WeatherData;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {


    private TextView textViewDate;
    private TextView textViewCity;
    private TextView textViewTmp;
    private TextView textViewDayWeather;
    private TextView textViewNightWeather;


    private Button refreshButton;

    private ContentResolver resolver;


    private String cityName;
    private String date;
    private String dayWeather;
    private String nightWeather;
    private String tmp;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String response = (String) msg.obj;

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

            textViewDate.setText(date);
            textViewCity.setText(cityName);
            textViewTmp.setText(tmp + "（摄氏度）");
            textViewDayWeather.setText("白天：" + dayWeather);
            textViewNightWeather.setText("夜间："+ nightWeather);


        }
    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textViewDate = (TextView) findViewById(R.id.main_tv_date);
        textViewCity = (TextView) findViewById(R.id.main_tv_city);
        textViewTmp = (TextView) findViewById(R.id.main_tv_tmp);
        textViewDayWeather = (TextView) findViewById(R.id.main_tv_day_weather);
        textViewNightWeather = (TextView) findViewById(R.id.main_tv_night_weather);
        refreshButton = (Button) findViewById(R.id.main_button_refresh);



        resolver = getContentResolver();

        HttpUtil.sendHttpRequest(HttpUtil.HTTP_URL, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message msg = new Message();
                msg.obj = response;
                handler.sendMessage(msg);
            }

            @Override
            public void onError(Exception e) {

            }
        });




        startService(new Intent(this,MyService.class));


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshUI();

                Log.i("info","我被刷新按钮刷新了");
                Log.i("info","cityName = " + cityName);
                Log.i("info","date = " + date);
                Log.i("info","dayWeather = " + dayWeather);
                Log.i("info","nightWeather = " + nightWeather);
                Log.i("info","tmp = " + tmp);



            }
        });






    }

    private void refreshUI() {
        Cursor c = resolver.query(Uri.parse(MyContentProvider.URI),null,null,null,null);
        if(c!=null){
            while(c.moveToNext()){
                cityName = c.getString(c.getColumnIndex("city"));
                date = c.getString(c.getColumnIndex("date"));
                dayWeather = c.getString(c.getColumnIndex("day_weather"));
                nightWeather = c.getString(c.getColumnIndex("night_weather"));
                tmp = c.getString(c.getColumnIndex("tmp"));

            }
            c.close();
        }

        textViewDate.setText(date);
        textViewCity.setText(cityName);
        textViewTmp.setText(tmp + "（摄氏度）");
        textViewDayWeather.setText("白天：" + dayWeather);
        textViewNightWeather.setText("夜间："+ nightWeather);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this,MyService.class));
    }
}
