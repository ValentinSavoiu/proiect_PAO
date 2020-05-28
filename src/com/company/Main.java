package com.company;

import com.company.services.AdministrationSystem;

public class Main {

    public static void main(String[] args) {
        AdministrationSystem as = AdministrationSystem.getInstance();
        as.master();
    }
}
