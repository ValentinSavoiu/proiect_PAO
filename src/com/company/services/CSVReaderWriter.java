package com.company.services;

import com.company.Entities.Client;
import com.company.Entities.Employee;
import com.company.Entities.Hall;
import com.company.Entities.Show;

import java.io.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TreeSet;

public class CSVReaderWriter {
    private static CSVReaderWriter instance = null;
    private static char CSV_SEPARATOR = ',';
    private CSVReaderWriter() {

    }
    public static CSVReaderWriter getInstance() {
        if (instance == null) {
            instance = new CSVReaderWriter();
        }
        return instance;
    }
    public void writeHalls(TreeSet<Hall> halls) throws IOException {
        FileWriter csvWriter = new FileWriter("src/com/company/csvfiles/halls.csv");
        for (Hall h : halls) {
            csvWriter.append(h.getName());
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(String.valueOf(h.getNoSeats()));
            csvWriter.append('\n');
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public TreeSet<Hall> readHalls() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/company/csvfiles/halls.csv"));
        String row;
        TreeSet<Hall> res = new TreeSet<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(String.valueOf(CSV_SEPARATOR));
            res.add(new Hall(data[0], Integer.parseInt(data[1])));
        }
        csvReader.close();
        return res;
    }

    public void writeShows(TreeSet<Show> shows) throws IOException {
        FileWriter csvWriter = new FileWriter("src/com/company/csvfiles/shows.csv");
        for (Show h : shows) {
            csvWriter.append(h.getName());
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(String.valueOf(h.getLocation().getName()));
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(String.valueOf(h.getPrice()));
            csvWriter.append('\n');
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public TreeSet<Show> readShows(TreeSet<Hall> halls) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/company/csvfiles/shows.csv"));
        String row;
        TreeSet<Show> res = new TreeSet<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(String.valueOf(CSV_SEPARATOR));
            for (Hall h : halls) {
                if (h.getName().equals(data[1])) {
                    res.add(new Show(data[0], h, Integer.parseInt(data[2])));
                    break;
                }
            }
        }
        csvReader.close();
        return res;
    }


    public void writeClients(TreeSet<Client> clients) throws IOException {
        FileWriter csvWriter = new FileWriter("src/com/company/csvfiles/clients.csv");
        for (Client h : clients) {
            csvWriter.append(h.getName());
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(h.getEmail());
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(String.valueOf(h.getSpentMoney()));
            csvWriter.append('\n');
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public TreeSet<Client> readClients() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/company/csvfiles/clients.csv"));
        String row;
        TreeSet<Client> res = new TreeSet<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(String.valueOf(CSV_SEPARATOR));
            Client C = new Client(data[0], data[1]);
            C.addSpentMoney(Double.parseDouble(data[2]));
            res.add(C);
        }
        csvReader.close();
        return res;
    }

    public void writeEmployees(TreeSet<Employee> employees) throws IOException {
        FileWriter csvWriter = new FileWriter("src/com/company/csvfiles/employees.csv");
        for (Employee h : employees) {
            csvWriter.append(h.getName());
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(h.getEmail());
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(String.valueOf(h.getNoYears()));
            csvWriter.append('\n');
        }
        csvWriter.flush();
        csvWriter.close();
    }

    public TreeSet<Employee> readEmployees() throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader("src/com/company/csvfiles/employees.csv"));
        String row;
        TreeSet<Employee> res = new TreeSet<>();
        while ((row = csvReader.readLine()) != null) {
            String[] data = row.split(String.valueOf(CSV_SEPARATOR));
            Employee C = new Employee(data[0], data[1]);
            C.setNoYears(Integer.parseInt(data[2]));
            res.add(C);
        }
        csvReader.close();
        return res;
    }

    public synchronized void writeLog(String s) {
        try {
            FileWriter csvWriter = new FileWriter("src/com/company/csvfiles/log.csv", true);
            csvWriter.append(s);
            Date date = new Date();
            long time = date.getTime();
            Timestamp ts = new Timestamp(time);
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(String.valueOf(ts));
            csvWriter.append(CSV_SEPARATOR);
            csvWriter.append(Thread.currentThread().getName());
            csvWriter.append('\n');
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
