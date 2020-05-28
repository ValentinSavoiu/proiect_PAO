package com.company.Entities;

public class Client extends Person {
    private double spentMoney = 0;
    public Client(String name, String email) {
        super(name, email);
        this.spentMoney = 0;
    }

    public Client(String name, String email, int id) {
        super(name, email, id);
        this.spentMoney = 0;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void addSpentMoney( double x) {
        spentMoney += x;
    }

    @Override
    public String toString() {
        return "Client{" +
                "spentMoney=" + spentMoney +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }
}
