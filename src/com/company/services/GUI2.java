package com.company.services;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI2 extends JFrame implements ActionListener {
    private AdministrationSystem as;
    public GUI2(String []l) {
        as = AdministrationSystem.getInstance();
        setLayout(new BorderLayout());
        JButton button = new JButton("Back to menu");
        JScrollPane jScrollPane = new JScrollPane();
        button.addActionListener(this);
        JList list = new JList(l);
        jScrollPane.setViewportView(list);
        add(jScrollPane, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);

        setSize(500, 500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //String action = e.getActionCommand();
        as.guiHandler(this, 0);
    }
}