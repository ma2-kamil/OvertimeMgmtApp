package com.example.overtimemgmtapp.classes;




public class Jobs {

    private String id;
    private String shifttitle;
    private String description;
    private String hourlyrate;
    private String date;
    private String starttime;
    private String endtime;
    private String uniquecode;
    private String employee;

    public Jobs() {
    }

    public Jobs(String id, String shifttitle, String description, String hourlyrate, String date, String starttime, String endtime, String uniquecode, String employee) {
        this.id = id;
        this.shifttitle = shifttitle;
        this.description = description;
        this.hourlyrate = hourlyrate;
        this.date = date;
        this.starttime = starttime;
        this.endtime = endtime;
        this.uniquecode = uniquecode;
        this.employee = employee;
    }

    public String getId() {
        return id;
    }

    public String getShifttitle() {
        return shifttitle;
    }

    public String getDescription() {
        return description;
    }

    public String getHourlyrate() {
        return hourlyrate;
    }

    public String getDate() {
        return date;
    }

    public String getStarttime() {
        return starttime;
    }

    public String getEndtime() {
        return endtime;
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

    public void setShifttitle(String shifttitle) {
        this.shifttitle = shifttitle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHourlyrate(String hourlyrate) {
        this.hourlyrate = hourlyrate;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public void setUniquecode(String uniquecode) {
        this.uniquecode = uniquecode;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }
}
