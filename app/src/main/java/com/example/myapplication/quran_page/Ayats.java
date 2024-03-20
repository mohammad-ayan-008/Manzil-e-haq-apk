package com.example.myapplication.quran_page;

public class Ayats {
    public int number;
    public String text;
    public surahs surah;

    public void setNumber(int number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSurah(surahs surah) {
        this.surah = surah;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public surahs getSurah() {
        return surah;
    }

    public Ayats(int number, String text, surahs surah) {
        this.number = number;
        this.text = text;
        this.surah = surah;
    }
}
