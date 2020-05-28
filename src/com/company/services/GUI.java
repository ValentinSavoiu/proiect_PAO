package com.company.services;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUI extends JFrame implements ActionListener {
    private AdministrationSystem as;
    public GUI() {
        as = AdministrationSystem.getInstance();

        JButton button1 = new JButton("Show clients");
        JButton button2 = new JButton("Show employees");
        JButton button3 = new JButton("Show halls");
        JButton button4 = new JButton("Show shows");
        JButton button5 = new JButton("Add seniority to all employees");
        JButton button6 = new JButton("Exit");


        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);


        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(button6);
        setLayout(new GridLayout(3, 2));
        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equals("Show clients")) {
            as.guiHandler(this, 1);
        }
        else if (action.equals("Show employees")) {
            as.guiHandler(this, 2);
        }
        else if (action.equals("Show halls")) {
            as.guiHandler(this, 3);
        }
        else if (action.equals("Show shows")) {
            as.guiHandler(this, 4);
        }
        else if (action.equals("Add seniority to all employees")) {
            as.guiHandler(this, 5);
        }
        else if (action.equals("Exit")) {
            as.guiHandler(this, 6);
        }
    }
}