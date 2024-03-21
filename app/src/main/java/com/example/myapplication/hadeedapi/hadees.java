package com.example.myapplication.hadeedapi;

import java.util.List;

public  class hadees {
    public int current_page;
    public List<data> data;
    public String last_page_url;

    public String next_page_url;


    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public List<com.example.myapplication.hadeedapi.data> getData() {
        return data;
    }

    public void setData(List<com.example.myapplication.hadeedapi.data> data) {
        this.data = data;
    }

    public String getLast_page_url() {
        return last_page_url;
    }

    public void setLast_page_url(String last_page_url) {
        this.last_page_url = last_page_url;
    }

    public String getNext_page_url() {
        return next_page_url;
    }

    public void setNext_page_url(String next_page_url) {
        this.next_page_url = next_page_url;
    }

    public hadees(int current_page, List<com.example.myapplication.hadeedapi.data> data, String last_page_url, String next_page_url) {
        this.current_page = current_page;
        this.data = data;
        this.last_page_url = last_page_url;
        this.next_page_url = next_page_url;
    }
}