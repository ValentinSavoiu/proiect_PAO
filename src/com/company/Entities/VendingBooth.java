package com.company.Entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.TreeSet;

public class VendingBooth {
    private static int cokePrice = 10;
    private static int popcornPrice = 20;

    public VendingBooth(){};
    public int sell(Scanner in) {
        System.out.println("Doriti popcorn sau suc?\n1: Suc\n2: Popcorn\n3: Ambele\nOrice altceva: nimic");
        int x = in.nextInt();
        if (1 <= x && x <= 3)
            return x * 10;
        return 0;
    }
}


