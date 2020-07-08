package com.harts.DO;

public class Data{

    private String date;
    private String tNumber;
    private String city;
    private String money;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Data{" +
                "date='" + date + '\'' +
                ", tNumber='" + tNumber + '\'' +
                ", city='" + city + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
