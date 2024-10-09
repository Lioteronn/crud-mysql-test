package org.example;

import com.formdev.flatlaf.FlatLightLaf;
import org.example.view.Application;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        FlatLightLaf.setup();
        JFrame app = new Application();
        app.setVisible(true);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}