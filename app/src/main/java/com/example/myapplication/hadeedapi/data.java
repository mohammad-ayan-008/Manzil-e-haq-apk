package com.example.myapplication.hadeedapi;

public  class data{
    public String  hadithEnglish;
    public String hadithUrdu;

    public String getHadithEnglish() {
        return hadithEnglish;
    }

    public void setHadithEnglish(String hadithEnglish) {
        this.hadithEnglish = hadithEnglish;
    }

    public String getHadithUrdu() {
        return hadithUrdu;
    }

    public void setHadithUrdu(String hadithUrdu) {
        this.hadithUrdu = hadithUrdu;
    }

    public data(String hadithEnglish, String hadithUrdu) {
        this.hadithEnglish = hadithEnglish;
        this.hadithUrdu = hadithUrdu;
    }
}