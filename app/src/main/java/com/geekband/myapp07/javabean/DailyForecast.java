/**
  * Copyright 2016 aTool.org 
  */
package com.geekband.myapp07.javabean;
import java.util.Date;

/**
 * Auto-generated: 2016-06-23 18:18:46
 *
 * @author aTool.org (i@aTool.org)
 * @website http://www.atool.org/json2javabean.php
 */
public class DailyForecast {

    private Astro astro;
    private Cond cond;
    private String date;
    private String hum;
    private String pcpn;
    private String pop;
    private String pres;
    private Tmp tmp;
    private String vis;
    private Wind wind;
    public void setAstro(Astro astro) {
         this.astro = astro;
     }
     public Astro getAstro() {
         return astro;
     }

    public void setCond(Cond cond) {
         this.cond = cond;
     }
     public Cond getCond() {
         return cond;
     }

    public void setDate(String date) {
         this.date = date;
     }
     public String getDate() {
         return date;
     }

    public void setHum(String hum) {
         this.hum = hum;
     }
     public String getHum() {
         return hum;
     }

    public void setPcpn(String pcpn) {
         this.pcpn = pcpn;
     }
     public String getPcpn() {
         return pcpn;
     }

    public void setPop(String pop) {
         this.pop = pop;
     }
     public String getPop() {
         return pop;
     }

    public void setPres(String pres) {
         this.pres = pres;
     }
     public String getPres() {
         return pres;
     }

    public void setTmp(Tmp tmp) {
         this.tmp = tmp;
     }
     public Tmp getTmp() {
         return tmp;
     }

    public void setVis(String vis) {
         this.vis = vis;
     }
     public String getVis() {
         return vis;
     }

    public void setWind(Wind wind) {
         this.wind = wind;
     }
     public Wind getWind() {
         return wind;
     }

}