package com.example;

import java.io.IOException;
import java.util.List;

import com.managers.EntityMgr;
import com.model.User;

import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Search extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("SearchName");
        response.setContentType("text/html");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("    <title>Search</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("Search Form");
        response.getWriter().println("<p>Please enter a name to search for</p>");
        response.getWriter().println("<form action=\"Search\" method=\"get\">");
        response.getWriter().println("<label for=\"Searchfield\">Find:</label>");
        response.getWriter().println("<input type=\"text\" id=\"Searchfield\" name=\"SearchName\" required><br><br>");
        response.getWriter().println("<button type=\"submit\">Search</button></form>");

        String sqlQuery = "SELECT * FROM User WHERE firstName LIKE ? OR lastName LIKE ?";

        Query query = EntityMgr.getEntityManager().createNativeQuery(sqlQuery, User.class);
        query.setParameter(1, "%" + name + "%");
        query.setParameter(2, "%" + name + "%");

        @SuppressWarnings("unchecked")
        List<User> list = query.getResultList();
        if (name != null) {
            //list = list.stream().filter((a) -> a.getFirstName().contains(name) && a.getLastName().contains(name)).toList();
            
            for (User a : list) {
                response.getWriter().println("user "+a.getId()+" is "  + a.getFirstName() + " " + a.getLastName() + "<br>");
            }
        }

        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

}
