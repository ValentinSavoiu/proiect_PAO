package com.company.services;
import com.company.Entities.Client;
import com.company.Entities.Employee;
import com.company.Entities.Hall;
import com.company.Entities.Show;
import java.sql.*;
import java.util.TreeSet;

public class CRUD {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/pao";
    private static final String USER = "root";
    private static final String PASS = "MyNewPass";
    private Statement stmt;

    public CRUD() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        stmt = conn.createStatement();
    }

    public boolean existsTable(String s) throws SQLException {
        String sql = "SELECT count(*) FROM information_schema.TABLES  WHERE TABLE_NAME = '" + s + "';";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        int x = rs.getInt(1);
        if (x == 0) {
            return false;
        }
        return true;
    }

    public void createHalls(TreeSet<Hall> halls) throws SQLException {
        String sql = "create table halls (name varchar(20), noSeats int);";
        stmt.execute(sql);
        for (Hall h : halls) {
            sql = "insert into halls values( '" + h.getName() + "', '" + h.getNoSeats() + "' );";
            stmt.execute(sql);
        }
    }

    public TreeSet<Hall> readHalls() throws SQLException {
        TreeSet<Hall> res = new TreeSet<>();
        String sql = "select * from halls;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            int noSeats = rs.getInt(2);
            res.add(new Hall(name, noSeats));
        }
        return res;
    }

    public void removeHall(Hall h){
        try {
            String sql = "delete from halls where name = '" + h.getName() + "';";
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHall(Hall h) {
        try {
            String sql = "select * from halls where name = '" + h.getName() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                sql = "update halls set noSeats = " + h.getNoSeats() + " where name = '" + h.getName() + "';";
            }
            else {
                sql = "insert into halls values ('" + h.getName() + "', " +  h.getNoSeats()  + "  );";
            }
            stmt.execute(sql);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createShows(TreeSet<Show> shows) throws SQLException {
        String sql = "create table shows (name varchar(20), location varchar(20), price int);";
        stmt.execute(sql);
        for (Show h : shows) {
            sql = "insert into shows values( '" + h.getName() + "', '" + h.getLocation().getName() + "', " +  h.getPrice() + " );";
            stmt.execute(sql);
        }
    }

    public TreeSet<Show> readShows(TreeSet<Hall> halls) throws SQLException {
        TreeSet<Show> res = new TreeSet<>();
        String sql = "select * from shows;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            String locName = rs.getString(2);
            int price = rs.getInt(3);
            for (Hall h : halls) {
                if (h.getName().equals(locName)) {
                    Show s = new Show(name, h, price);
                    res.add(s);
                    break;
                }
            }
        }
        return res;
    }

    public void removeShow(Show h){
        try {
            String sql = "delete from shows where name = '" + h.getName() + "';";
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateShow(Show h) {
        try {
            String sql = "select * from shows where name = '" + h.getName() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                sql = "update shows set price = " + h.getPrice() + " where name = '" + h.getName() + "';";
            }
            else {
                sql = "insert into shows values ('" + h.getName() + "', '" +  h.getLocation().getName() +"', " + h.getPrice()  + "  );";
            }
            stmt.execute(sql);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createClients(TreeSet<Client> clients) throws SQLException {
        String sql = "create table clients (name varchar(20), email varchar(20), spentMoney double);";
        stmt.execute(sql);
        for (Client h : clients) {
            sql = "insert into clients values( '" + h.getName() + "', '" + h.getEmail() + "', " + h.getSpentMoney() +  " );";
            stmt.execute(sql);
        }
    }

    public TreeSet<Client> readClients() throws SQLException {
        TreeSet<Client> res = new TreeSet<>();
        String sql = "select * from clients;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            String email = rs.getString(2);
            double spentMoney = rs.getDouble(3);
            Client c = new Client(name, email);
            c.addSpentMoney(spentMoney);
            res.add(c);
        }
        return res;
    }

    public void removeClient(Client h){
        try {
            String sql = "delete from clients where name = '" + h.getName() + "';";
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateClient(Client h) {
        try {
            String sql = "select * from clients where name = '" + h.getName() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                sql = "update clients set spentMoney = " + h.getSpentMoney() + " where name = '" + h.getName() + "';";
            }
            else {
                sql = "insert into clients values( '" + h.getName() + "', '" + h.getEmail() + "', " + h.getSpentMoney() +  " );";
            }
            stmt.execute(sql);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createEmployees(TreeSet<Employee> employees) throws SQLException {
        String sql = "create table employees (name varchar(20), email varchar(20), noYears int);";
        stmt.execute(sql);
        for (Employee h : employees) {
            sql = "insert into employees values( '" + h.getName() + "', '" + h.getEmail() + "', " + h.getNoYears() +  " );";
            stmt.execute(sql);
        }
    }

    public TreeSet<Employee> readEmployees() throws SQLException {
        TreeSet<Employee> res = new TreeSet<>();
        String sql = "select * from employees;";
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String name = rs.getString(1);
            String email = rs.getString(2);
            int years = rs.getInt(3);
            Employee c = new Employee(name, email);
            c.addNoYears(years);
            res.add(c);
        }
        return res;
    }

    public void removeEmployee(Employee h){
        try {
            String sql = "delete from employees where name = '" + h.getName() + "';";
            stmt.execute(sql);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(Employee h) {
        try {
            String sql = "select * from employees where name = '" + h.getName() + "';";
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                sql = "update employees set noYears = " + h.getNoYears() + " where name = '" + h.getName() + "';";
            }
            else {
                sql = "insert into employees values( '" + h.getName() + "', '" + h.getEmail() + "', " + h.getNoYears() +  " );";
            }
            stmt.execute(sql);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void commit() {
        try {
            String sql = "COMMIT";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            String sql = "ROLLBACK";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
