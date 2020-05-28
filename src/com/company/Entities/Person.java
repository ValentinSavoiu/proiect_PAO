package com.company.Entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.TreeSet;

public class Person implements Comparable<Person>{
    protected String name;
    protected String email;
    static protected int ID = 0;
    protected int id;
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        this.id = ID++;
    }

    public Person(String name, String email, int id) { // so I don't have useless ID's
        this.name = name;
        this.email = email;
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return Objects.equals(getName(), person.getName()) &&
                Objects.equals(getEmail(), person.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmail());
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int compareTo(Person person) {
        if (this.name.equals(person.name)) {
            return this.email.compareTo(person.email);
        }
        return this.name.compareTo(person.name);
    }
}




