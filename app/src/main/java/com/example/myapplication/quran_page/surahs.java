package com.example.myapplication.quran_page;

public class surahs {
    public int number;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public int getNumber() {
        return number;
    }

    public String getEnglishName() {
        return englishName;
    }

    public surahs(int number, String englishName) {
        this.number = number;
        this.englishName = englishName;
    }

    public String englishName;

}
