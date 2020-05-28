package com.company.Entities;

import java.util.*;
public class Hall implements Comparable<Hall>{
    private int id, noSeats;
    private static int ID;
    private String name;
    private ArrayList<Seat> seats;
    public Hall(String name, int noSeats) {
        this.id = ID++;
        this.noSeats = noSeats;
        this.seats = new ArrayList<Seat>();
        this.name = name;
        for (int i = 0; i < noSeats; ++i) {
            seats.add(new Seat());
        }
    }

    public int getNoSeats() {
        return noSeats;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "id=" + id +
                ", no_seats=" + noSeats +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Hall hall) {
        return name.compareTo(hall.name);
    }
}



