package com.company.services;
import com.company.Entities.*;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
public class AdministrationSystem { //Singleton
    private static AdministrationSystem myInstance = null;
    private CSVReaderWriter csvReaderWriter;
    private VendingBooth vendingBooth;
    private TreeSet<Client> clients = new TreeSet<Client>();
    private TreeSet<Employee> employees = new TreeSet<Employee>();
    private TreeSet<Hall> halls = new TreeSet<Hall>();
    private TreeSet<Show> shows = new TreeSet<Show>();
    private static Scanner in = new Scanner(System.in);
    private String name, email;
    private CRUD crud;
    private AdministrationSystem() {
        vendingBooth = new VendingBooth();
        csvReaderWriter = CSVReaderWriter.getInstance();
    }

    public static AdministrationSystem getInstance() {
        if (myInstance == null)
            myInstance = new AdministrationSystem();
        return myInstance;
    }

    private Client findClient(String name, String email) {
        Client auxClient  = new Client(name, email, -1);
        Client bottom = clients.floor(auxClient);
        if (bottom == null || bottom.equals(auxClient)) {
            return bottom;
        }
        else {
            return null;
        }
    }

    private Client addClient(String name, String email) {
        Client auxClient  = new Client(name, email);
        clients.add(auxClient);
        crud.updateClient(auxClient);
        return auxClient;
    }

    private void removeClient(String name, String email) {
        Client c  = findClient(name, email);
        if (c != null) {
            clients.remove(c);
            crud.removeClient(c);
        }
        else {
            System.out.println("Client inexistent");
        }
    }

    private Employee  findEmployee(String name, String email) {
        Employee auxClient  = new Employee(name, email, -1);
        Employee bottom = employees.floor(auxClient);
        if (bottom == null || bottom.equals(auxClient)) {
            return bottom;
        }
        else {
            return null;
        }
    }

    private Employee addEmployee(String name, String email) {
        Employee auxClient  = new Employee(name, email);
        employees.add(auxClient);
        crud.updateEmployee(auxClient);
        return auxClient;
    }

    private void removeEmployee(String name, String email) {
        Employee c  = findEmployee(name, email);
        if (c != null) {
            employees.remove(c);
            crud.removeEmployee(c);
        }
        else {
            System.out.println("Client inexistent");
        }
    }

    private void readName() {
        System.out.println("Introduceti numele:");
        in.nextLine();
        name = in.nextLine();
    }

    private void readNameEmail() {
        System.out.println("Introduceti numele si emailul, fiecare pe cate o linie:");
        in.nextLine();
        name = in.nextLine();
        email = in.nextLine();
    }

    private void printShows() {
        for (Show s: shows) {
            System.out.println(s);
        }
    }

    private Hall findHall (int id) {
        for (Hall h: halls) {
            if (h.getId() == id)
                return h;
        }
        return null;
    }

    private Hall findHall(String name) {
        for (Hall h: halls) {
            if (h.getName().equals(name))
                return h;
        }
        return null;
    }

    private void printHalls() {
        for (Hall h : halls) {
            System.out.println(h);
        }
    }

    private void auxInit() {
        for (int i = 0; i < 8; ++i) {
            halls.add(new Hall("Sala" + i, 10 * (i + 1)));
            clients.add(new Client("Nume" + i, "Email" + i));
            employees.add(new Employee("NumeE" + i, "EmailE" + i));
        }
        for (Hall h : halls) {
            for (int i = 0; i < 3; ++i) {
                shows.add(new Show(h.getName() + " Show" + i, h, 100 * (i + 1)));
            }
        }

    }

    private void addSeniorityAll() {
        for (Employee e: employees) {
            e.addNoYears(1);
            crud.updateEmployee(e);
        }
        csvReaderWriter.writeLog("add_seniority");
    }

    public void startGUI() {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    public void guiHandler(JFrame j, int i) {
       j.dispose();
       if (i == 0) {
            csvReaderWriter.writeLog("back_to_menu");
            GUI gui = new GUI();
            return;
       }
       if (i == 6) {
           csvReaderWriter.writeLog("exit");
           return;
       }
       String s = new String();
       if (i == 1) {
           for (Client c: clients) {
               s = s + c.toString() + "&";
           }
           csvReaderWriter.writeLog("print_clients");
       }
       if (i == 2) {
            for (Employee e: employees) {
                s = s + e.toString() + "&";
            }
           csvReaderWriter.writeLog("print_employees");
       }
       if (i == 3) {
            for (Hall h: halls) {
                s = s + h.toString() + "&";
            }
           csvReaderWriter.writeLog("print_halls");
       }
       if (i == 4) {
            for (Show sh: shows) {
                s = s + sh.toString() + "&";
            }
            csvReaderWriter.writeLog("print_shows");
       }
       if (i == 5) {
            s = "Done";
            addSeniorityAll();
       }
       GUI2 gui2 = new GUI2(s.split("&"));
    }

    public void master() {
       // auxInit();
        try {
            crud = new CRUD();

            if (crud.existsTable("halls")) {
                halls = crud.readHalls();
            }
            else {
                halls = csvReaderWriter.readHalls();
                crud.createHalls(halls);
            }
            if (crud.existsTable("shows")) {
                shows = crud.readShows(halls);
            }
            else {
                shows = csvReaderWriter.readShows(halls);
                crud.createShows(shows);
            }
            if (crud.existsTable("clients")) {
                clients = crud.readClients();
            }
            else {
                clients = csvReaderWriter.readClients();
                crud.createClients(clients);
            }
            if (crud.existsTable("employees")) {
                employees = crud.readEmployees();
            }
            else {
                employees = csvReaderWriter.readEmployees();
                crud.createEmployees(employees);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return;
        }
        startGUI();
        while (true) {
            System.out.println("Introduceti operatia:\n-1: Iesire fara salvare\n0: Iesire cu salvare\n1: Adauga client\n2: Adauga vechime angajati\n3: Sterge client:");
            System.out.println("4: Afiseaza clienti\n5: Schimba vechime singur angajat\n6: Adauga Angajat\n7: Sterge Angajat");
            System.out.println("8: Afiseaza sali\n9: Afiseaza spectacole\n10: Cumpara bilet\n11: Adauga spectacole");
            System.out.println("12: Sterge spectacol\n13: Afiseaza angajati\n14: Sterge sala\n15: Adauga sala");
            int option = in.nextInt();
            if (option == -1) {
                csvReaderWriter.writeLog("exit_without_saving");
                crud.rollback();
                return;
            }
            if (option == 0) {
                try{
                    /*
                    csvReaderWriter.writeHalls(halls);
                    csvReaderWriter.writeShows(shows);
                    csvReaderWriter.writeClients(clients);
                    csvReaderWriter.writeEmployees(employees);

                     */
                    crud.commit();
                    System.out.println("Procesul s-a incheiat cu succes");
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Eroare la salvare modificari");
                }
                csvReaderWriter.writeLog("exit_with_saving");
                break;
            }
            if (option == 1) {
                readNameEmail();
                Client c = findClient(name, email);
                if (c == null) {
                    c = addClient(name, email);
                }
                else {
                    System.out.println("Clientul exista deja");
                }
                csvReaderWriter.writeLog("add_client");
            }

            if (option == 2) {
                addSeniorityAll();
            }

            if (option == 3) {
                readNameEmail();
                removeClient(name, email);
                csvReaderWriter.writeLog("remove_client");
            }

            if (option == 4) {
                for (Client c : clients) {
                    System.out.println(c);
                }
                csvReaderWriter.writeLog("print_clients");
            }

            if (option == 5) {
                readNameEmail();
                Employee e = findEmployee(name, email);
                if (e == null) {
                    System.out.println("Angajat inexistent");
                }
                else {
                    System.out.println("Introduceti noua vechime");
                    int x = in.nextInt();
                    e.setNoYears(x);
                    crud.updateEmployee(e);
                }
                csvReaderWriter.writeLog("change_seniority");
            }

            if (option == 6) {
                readNameEmail();
                Employee e = findEmployee(name, email);
                if (e == null) {
                    e = addEmployee(name, email);
                }
                else {
                    System.out.println("Angajatul exista deja");
                }
                csvReaderWriter.writeLog("add_employee");
            }

            if (option == 7) {
                readNameEmail();
                removeEmployee(name, email);
                csvReaderWriter.writeLog("remove_employee");
            }

            if (option == 8) {
                printHalls();
                csvReaderWriter.writeLog("print_halls");
            }

            if (option == 9) {
                printShows();
                csvReaderWriter.writeLog("print_shows");
            }

            if (option == 10) {
                readNameEmail();
                Client C = findClient(name, email);
                if (C == null) {
                    String s = null;
                    System.out.println("Clientul nu exista, Doriti sa creati client nou? Y/N");
                    s = in.next();
                    if (s.equals("Y")) {
                        C = addClient(name, email);
                    }
                    else {
                        continue;
                    }
                }
                System.out.println("Introduceti ID-ul spectacolui. -1 Pentru a vedea toate spectacolele");
                int x = in.nextInt();
                while (x == -1) {
                    printShows();
                    System.out.println("Introduceti ID-ul spectacolui. -1 Pentru a vedea toate spectacolele");
                    x = in.nextInt();
                }
                System.out.println(x);
                Show sh = null;
                for (Show s: shows) {
                    if (s.getId() == x) {
                        sh = s;
                        break;
                    }
                }
                if (sh == null) {
                    System.out.println("Spectacol inexistent");
                    continue;
                }
                double price = sh.getPrice();
                if (C.getSpentMoney() > 1000) {
                    price = price * 0.9;
                }
                Seat seat = sh.getFirstUnoccupiedSeat();
                if (seat == null) {
                    System.out.println("Sold out");
                    continue;
                }
                price += vendingBooth.sell(in);
                System.out.println("Aveti de platit " + Double.toString(price));
                seat.assign(sh, C);
                C.addSpentMoney(price);
                crud.updateClient(C);
                System.out.println("Succes");
                csvReaderWriter.writeLog("buy_ticket");
            }

            if (option == 11) {
                readName();
                Show sh = new Show(name);
                if (shows.contains(sh)) {
                    System.out.println("Exista deja un show cu acest nume");
                }
                else {
                    System.out.println("Introduceti ID-ul salii in care se afla, sau -1 pt a afisa salile:");
                    int x = in.nextInt();
                    while (x == -1) {
                        printHalls();
                        System.out.println("Introduceti ID-ul salii in care se afla, sau -1 pt a afisa salile:");
                        x = in.nextInt();
                    }
                    Hall h = findHall(x);
                    if (h == null) {
                        System.out.println("Sala inexistenta");
                        continue;
                    }
                    System.out.println("Introduceti pretul:");
                    int price = in.nextInt();
                    Show s = new Show(name, h, price);
                    shows.add(s);
                    crud.updateShow(s);
                }
                csvReaderWriter.writeLog("add_show");
            }

            if (option == 12) {
                readName();
                Show sh = new Show(name);
                if (shows.contains(sh)) {
                    shows.remove(sh);
                    crud.removeShow(sh);
                }
                else {
                    System.out.println("Nu exista show cu acest nume");
                }
                csvReaderWriter.writeLog("remove_show");
            }

            if (option == 13) {
                for (Employee e: employees) {
                    System.out.println(e);
                }
                csvReaderWriter.writeLog("print_employees");
            }

            if (option == 14) {
                readName();
                System.out.println(name);
                Hall h = findHall(name);
                if (h != null) {
                    halls.remove(h);
                    crud.removeHall(h);
                }
                else {
                    System.out.println("Nu exista sala cu acest nume");
                }
                csvReaderWriter.writeLog("remove_hall");
            }

            if (option == 15) {
                readName();
                System.out.println("Introduceti numar scaune:");
                int noSeats = in.nextInt();
                Hall h = findHall(name);
                if (h != null) {
                    System.out.println("Deja exista sala cu acest nume, actualizez numarul de scaune");
                }
                else {
                    h = new Hall(name, noSeats);
                    halls.add(h);
                }
                crud.updateHall(h);
            }
        }

    }
}