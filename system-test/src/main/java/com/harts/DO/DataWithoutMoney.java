package com.harts.DO;

public class DataWithoutMoney {
    private String date;
    private String tNumber;
    private String city;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String gettNumber() {
        return tNumber;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "DataWithoutMoney{" +
                "date='" + date + '\'' +
                ", tNumber='" + tNumber + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
