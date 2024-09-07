package com.listeners;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class UserCountFileListener implements ServletContextListener {

    private static String countPath;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        
        countPath = event.getServletContext().getRealPath("/count.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(countPath))) {
            String count = reader.readLine();
            if (count != null) {
                UserCountListener.setActiveUsers( Integer.parseInt(count));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {

        countPath = event.getServletContext().getRealPath("/count.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(countPath))) {
            writer.write(String.valueOf(UserCountListener.getActiveUsers()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
