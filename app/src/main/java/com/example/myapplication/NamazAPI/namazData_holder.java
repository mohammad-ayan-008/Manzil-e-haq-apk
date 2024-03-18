package com.example.myapplication.NamazAPI;

import java.util.List;

public class namazData_holder {


    public time timings;

    public void setTimings(time timings) {
        this.timings = timings;
    }

    public time getTimings() {
        return timings;
    }

    public namazData_holder(time timings) {
        this.timings = timings;
    }
}
