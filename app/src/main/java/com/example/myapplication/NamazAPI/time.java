package com.example.myapplication.NamazAPI;

import com.google.gson.annotations.SerializedName;

public class time {
    @SerializedName("Fajr")
    String fajrTime ;
    @SerializedName("Sunrise")
    String sunriseTime ;
    @SerializedName("Dhuhr")
    String dhuhrTime;
    @SerializedName("Asr")
    String asrTime ;
    @SerializedName("Sunset")
    String sunsetTime ;
    @SerializedName("Maghrib")
    String maghribTime;
    @SerializedName("Isha")
    String ishaTime ;

    public void setFajrTime(String fajrTime) {
        this.fajrTime = fajrTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public void setDhuhrTime(String dhuhrTime) {
        this.dhuhrTime = dhuhrTime;
    }

    public void setAsrTime(String asrTime) {
        this.asrTime = asrTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public void setMaghribTime(String maghribTime) {
        this.maghribTime = maghribTime;
    }

    public void setIshaTime(String ishaTime) {
        this.ishaTime = ishaTime;
    }

    public String getFajrTime() {
        return fajrTime;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public String getDhuhrTime() {
        return dhuhrTime;
    }

    public String getAsrTime() {
        return asrTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public String getMaghribTime() {
        return maghribTime;
    }

    public String getIshaTime() {
        return ishaTime;
    }

    public time(String fajrTime, String sunriseTime, String dhuhrTime, String asrTime, String sunsetTime, String maghribTime, String ishaTime) {
        this.fajrTime = fajrTime;
        this.sunriseTime = sunriseTime;
        this.dhuhrTime = dhuhrTime;
        this.asrTime = asrTime;
        this.sunsetTime = sunsetTime;
        this.maghribTime = maghribTime;
        this.ishaTime = ishaTime;
    }


}
