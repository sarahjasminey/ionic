package com.example.teddy_2;

public class wallet {

    private String serialNum;
    private String name;
    private String mobileNumber;
    public wallet(String num, String name, String mobileNumber) {
        this.serialNum = num;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }
    public String getNum() {
        return serialNum;
    }
    public void setNum(String num) {
        this.serialNum = num;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
