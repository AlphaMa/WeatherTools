/**
  * Copyright 2016 aTool.org 
  */
package com.geekband.myapp07.javabean;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Auto-generated: 2016-06-23 18:18:46
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class Heweatherdata {

    private Aqi aqi;
    private Basic basic;
    @SerializedName("daily_forecast")
    private List<DailyForecast> dailyForecast;
    @SerializedName("hourly_forecast")
    private List<HourlyForecast> hourlyForecast;
    private Now now;
    private String status;
    private Suggestion suggestion;
    public void setAqi(Aqi aqi) {
         this.aqi = aqi;
     }
     public Aqi getAqi() {
         return aqi;
     }

    public void setBasic(Basic basic) {
         this.basic = basic;
     }
     public Basic getBasic() {
         return basic;
     }

    public void setDailyForecast(List<DailyForecast> dailyForecast) {
         this.dailyForecast = dailyForecast;
     }
     public List<DailyForecast> getDailyForecast() {
         return dailyForecast;
     }

    public void setHourlyForecast(List<HourlyForecast> hourlyForecast) {
         this.hourlyForecast = hourlyForecast;
     }
     public List<HourlyForecast> getHourlyForecast() {
         return hourlyForecast;
     }

    public void setNow(Now now) {
         this.now = now;
     }
     public Now getNow() {
         return now;
     }

    public void setStatus(String status) {
         this.status = status;
     }
     public String getStatus() {
         return status;
     }

    public void setSuggestion(Suggestion suggestion) {
         this.suggestion = suggestion;
     }
     public Suggestion getSuggestion() {
         return suggestion;
     }

}