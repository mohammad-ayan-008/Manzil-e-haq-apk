package com.example.myapplication.hadeedapi;

import java.util.List;

public class hadeed_data {
    public int status;
    public int total;

    public hadees  hadiths;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public hadees getHadiths() {
        return hadiths;
    }

    public void setHadiths(hadees hadiths) {
        this.hadiths = hadiths;
    }

    public hadeed_data(int status, int total, hadees hadiths) {
        this.status = status;
        this.total = total;
        this.hadiths = hadiths;
    }
}
