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
public class WeatherData {

    @SerializedName("HeWeather data service 3.0")
    private List<Heweatherdata> heweatherdata;
    public void setHeweatherdata(List<Heweatherdata> heweatherdata) {
         this.heweatherdata = heweatherdata;
     }
     public List<Heweatherdata> getHeweatherdata() {
         return heweatherdata;
     }

}