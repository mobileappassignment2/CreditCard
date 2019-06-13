package com.example.creditcard;

public class CreditCards {
    private String name;
    private String credit;
    private String expiry;
    private String pin;

    public CreditCards(String name, String credit, String expiry, String pin) {
        this.name = name;
        this.credit = credit;
        this.expiry = expiry;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.name = expiry;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}