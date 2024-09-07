package com.example;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Welcome extends HttpServlet {

    private ServletConfig sc;
    private ServletContext sCon;

    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config;
        sCon = sc.getServletContext();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //String user_name = (String) request.getAttribute("user");
        String csrfToken = (String) request.getAttribute("csrf");
        Cookie[] cookies = request.getCookies();
        boolean cookieFound = false;

        Cookie reqCok;
        HttpSession session = request.getSession(false); // Get the existing session, do not create a new one
        
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("Login");
            return;
        }


        
        if (session != null && session.getAttribute("user") != null) {
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if ("logCookie".equals(cookie.getName())) {
                        cookieFound = true;
                        reqCok = cookie;
                        showWelcome(response, reqCok.getValue().replace("+", " "));
                        response.getWriter().println("Session CSRF Token:  " + session.getAttribute("csrfToken"));
                        response.getWriter().println("<br>Request CSRF Token: " + csrfToken);
                        break;
                    }
                }
            }
        }

        //String sessionCsrfToken = (session != null) ? (String) session.getAttribute("csrfToken") : null;

        // if (csrfToken == null || !csrfToken.equals(sessionCsrfToken)) {
        //     //response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
        //     response.getWriter().println("a " + sessionCsrfToken + "<br>");
        //     response.getWriter().println("b " + csrfToken);
        //     return;
        // }

        if (!cookieFound) {
            showError(response);
        }

    }

    private void showWelcome(HttpServletResponse response, String a) throws ServletException, IOException {

        response.setContentType("text/html");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("    <title>Welcome Page</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("    <h2>Welcome</h2>");
        response.getWriter().println("    <p>Hello " + a + "</p>");
        response.getWriter().println("<h3>Country :" + sCon.getInitParameter("Country") + "</h3>");

        response.getWriter().println("<button onclick=\"location.href=\'Pic\'\" type=\"button\" style=\"width:150px;\">Pic Pic Pic</button><br><br>");

        response.getWriter().println("<button onclick=\"location.href=\'search.jsp\'\" type=\"button\" style=\"width:150px;\">Search users</button><br><br>");

        response.getWriter().println("<form action=\"SQL_Search\" method=\"POST\">");
        response.getWriter().println("<button type=\"submit\" style=\"width:150px;\">Query Users</button></form>");

        response.getWriter().println("<button onclick=\"location.href=\'userForm.jsp\'\" type=\"button\" style=\"width:150px;\">User form</button><br><br>");

        response.getWriter().println("<button onclick=\"location.href=\'usernameForm.html\'\" type=\"button\" style=\"width:150px;\">Username form</button><br><br>");

        response.getWriter().println("<button onclick=\"location.href=\'randomNumber.jsp\'\" type=\"button\" style=\"width:150px;\">Random numbers</button><br><br>");

        response.getWriter().println("<button onclick=\"location.href=\'LogoutCookie\'\" type=\"button\" style=\"width:150px;\">Logout</button><br><br>");

        response.getWriter().println("</body>");
        response.getWriter().println("</html>");

    }

    private void showError(HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("    <title>Error!</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("    <h2>Please enable cookies to proceed</h2>");
        response.getWriter().println("<button onclick=\"location.href=\'Login\'\" type=\"button\" style=\"width:150px;\">Go to Login</button><br><br>");

        response.getWriter().println("</body>");
        response.getWriter().println("</html>");

    }
}
