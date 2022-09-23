package com.kenzie.app.data;

public class Address {
    //declare properties
    private String mailToName = "";
    private String address = "";
    private String city = "";
    private String state = "";
    private String zipCode = "";
    //constructors

    public Address(String mailToName, String address, String city, String state, String zipCode) {
        this.mailToName = mailToName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
    public Address(){
        this("", "", "", "", "");
    }

    //methods

    public String getMailToName() {
        return mailToName;
    }

    public void setMailToName(String mailToName) {
        this.mailToName = mailToName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{" +
                "mailToName='" + mailToName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
