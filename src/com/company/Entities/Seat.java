package com.company.Entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Seat {
    private int id;
    private static int ID;
    private TreeMap<Show, Client> assignments ;

    public Seat() {
        this.id = ID++;
        this.assignments = new TreeMap<Show, Client>();
    }


    public boolean assign(Show s, Client c) {
        if(assignments.containsKey(s)) {
            return false;
        }
        assignments.put(s, c);
        return true;
    }

    public TreeMap<Show, Client> getAssignments() {
        return assignments;
    }
}



