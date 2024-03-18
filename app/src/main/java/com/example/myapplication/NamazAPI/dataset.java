package com.example.myapplication.NamazAPI;

import java.util.List;

public class dataset {
    List<namazData_holder> data;

    public void setData(List<namazData_holder> data) {
        this.data = data;
    }

    public List<namazData_holder> getData() {
        return data;
    }

    public dataset(List<namazData_holder> data) {
        this.data = data;
    }
}
