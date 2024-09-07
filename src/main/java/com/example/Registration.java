package com.example;

import java.io.IOException;

import com.managers.EntityMgr;
import com.model.User;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/Register")
public class Registration extends HttpServlet {

    private EntityMgr db;

    @Override
    public void init() throws ServletException {
        super.init();
        db = EntityMgr.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");

        User newUser = new User();
        newUser.setUsername(username);
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setPassword(password);
        // String csrfToken = request.getParameter("csrfToken");

        // String sessionCsrfToken = (String) request.getSession().getAttribute("csrfToken");
        // if (csrfToken == null || !csrfToken.equals(sessionCsrfToken)) {
        //     // Invalid token, reject the request
        //     response.sendError(HttpServletResponse.SC_FORBIDDEN, "Invalid CSRF token");
        //     return;
        // }
        String target = "Login";
        boolean success = true;
        try {
            db.addUser(newUser);
        } catch (PersistenceException e) {
            success = false;
            
        }
        if (success) {
            request.setAttribute("reg", "done");
        } else {
            target = "Register?regfail=y";
        }

        response.sendRedirect(target);
        //?reg=done
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("<title>Registration Page</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("<h2>Register</h2>");
        response.getWriter().println("<form action=\"Register\" method=\"post\">");
        response.getWriter().println("<label for=\"username\">Username:</label>");
        response.getWriter().println("<input type=\"text\" id=\"username\" name=\"username\" required><br><br>");
        response.getWriter().println("<label for=\"firstName\">First Name:</label>");
        response.getWriter().println("<input type=\"text\" id=\"firstName\" name=\"firstName\" required><br><br>");
        response.getWriter().println("<label for=\"lastName\">Last Name:</label>");
        response.getWriter().println("<input type=\"text\" id=\"lastName\" name=\"lastName\" required><br><br>");
        response.getWriter().println("<label for=\"password\">Password:</label>");
        response.getWriter().println("<input type=\"password\" id=\"password\" name=\"password\" required><br><br>");
        response.getWriter().println("<input type=\"hidden\" name=\"csrfToken\" value=\"" + request.getSession().getAttribute("csrfToken") + "\">");
        response.getWriter().println("<button type=\"submit\">Register</button><br>");
        if (request.getParameter("regfail") != null) {
            response.getWriter().println("<P> Unfortunately, registration failed. An Error occures or invalid entries</p>");
        }

        response.getWriter().println("</form>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");

    }
}
