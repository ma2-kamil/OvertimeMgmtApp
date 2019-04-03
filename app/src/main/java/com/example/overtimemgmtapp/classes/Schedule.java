package com.example.overtimemgmtapp.classes;

public class Schedule {

    private String id;
    private String date;
    private String fromtime;
    private String totime;
    private String uniquecode;
    private String employee;

    public Schedule(){

    }

    public Schedule(String id, String date, String fromtime, String totime, String uniquecode, String employee) {
        this.id = id;
        this.date = date;
        this.fromtime = fromtime;
        this.totime = totime;
        this.uniquecode = uniquecode;
        this.employee = employee;
    }


    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getFromtime() {
        return fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public String getUniquecode() {
        return uniquecode;
    }

    public String getEmployee() {
        return employee;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
