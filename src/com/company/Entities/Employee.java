package com.company.Entities;

public class Employee extends Person {
    private int noYears;
    private double salary;
    static private double baseSalary = 1000; //fictional units, I won't even bother
    public Employee(String name, String email) {
        super(name, email);
        this.noYears = 0;
        this.salary = baseSalary;
    }

    public Employee(String name, String email, int id) {
        super(name, email, id);
        this.noYears = 0;
        this.salary = baseSalary;
    }

    public int getNoYears() {
        return noYears;
    }

    public void setNoYears(int noYears) {
        this.noYears = noYears;
        this.salary = (1 + noYears / 10.0) * baseSalary;
    }

    public void addNoYears(int years) {
        setNoYears(getNoYears() + 1);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "noYears=" + noYears +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", id=" + id +
                '}';
    }

    public double getSalary() {
        return salary;
    }

}
