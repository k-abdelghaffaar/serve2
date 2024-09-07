package com.listeners;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class UserCountListener implements HttpSessionListener {

    private static int activeUsers = 0;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        activeUsers++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        activeUsers--;
    }

    public static int getActiveUsers() {
        return activeUsers;
    }

    public static void setActiveUsers(int userCount) {
        activeUsers = userCount;
    }
}
