package com.listeners;

import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

public class LoginLogoutListener implements HttpSessionListener {

    private static int loggedInUsers = 0;

    @Override
    public void sessionCreated(HttpSessionEvent event) {
        // Increment when the user logs in (handled in your Login servlet)
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent event) {
        loggedInUsers--;
    }

    public static int getLoggedInUsers() {
        return loggedInUsers;
    }

    public static void userLoggedIn() {
        loggedInUsers++;
    }

    public static void userLoggedOut() {
        loggedInUsers--;
    }
}
