package com.example.myapplication.recyclerHolder_hadees;

public class hadees_data {
    public String English_hadees;
    public String urdu_hadees;

    public String getEnglish_hadees() {
        return English_hadees;
    }

    public void setEnglish_hadees(String english_hadees) {
        English_hadees = english_hadees;
    }

    public String getUrdu_hadees() {
        return urdu_hadees;
    }

    public void setUrdu_hadees(String urdu_hadees) {
        this.urdu_hadees = urdu_hadees;
    }

    public hadees_data(String english_hadees, String urdu_hadees) {
        English_hadees = english_hadees;
        this.urdu_hadees = urdu_hadees;
    }
}
