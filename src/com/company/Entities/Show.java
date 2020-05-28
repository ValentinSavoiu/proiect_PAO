package com.company.Entities;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeMap;

public class Show implements Comparable<Show>{
    private int id;
    static private int ID = 0;
    private String name;
    private Hall location;
    private int price;

    public Show(String name, Hall location, int price) {
        this.name = name;
        this.location = location;
        this.price = price;
        this.id = ID++;
    }

    public Show(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public Hall getLocation() {
        return location;
    }

    public Seat getFirstUnoccupiedSeat () {
        ArrayList<Seat> seats = this.location.getSeats();
        for (Seat s : seats) {
            TreeMap<Show, Client> assignments = s.getAssignments();
            if (!assignments.containsKey(this))
                return s;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Show)) return false;
        Show show = (Show) o;
        return name.equals(show.name);
    }

    @Override
    public String toString() {
        return "Show{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Show show) {
        return name.compareTo(show.name);
    }
}
