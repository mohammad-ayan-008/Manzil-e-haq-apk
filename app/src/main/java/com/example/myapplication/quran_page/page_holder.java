package com.example.myapplication.quran_page;

import java.util.List;

public class page_holder {
    public List<Ayats> ayahs;

    public List<Ayats> getAyahs() {
        return ayahs;
    }

    public void setAyahs(List<Ayats> ayahs) {
        this.ayahs = ayahs;
    }

    public page_holder(List<Ayats> ayahs) {
        this.ayahs = ayahs;
    }
}
