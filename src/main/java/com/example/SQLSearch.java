package com.example;

import java.io.IOException;
import java.util.List;

import com.managers.EntityMgr;
import com.model.User;

import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SQLSearch extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("    <title>SQL Search</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("Search Form");
        response.getWriter().println("<p>Please enter a name to search for</p>");
        response.getWriter().println("<form action=\"SQL_Search\" method=\"Post\">");
        response.getWriter().println("<label for=\"Searchfield\">Enter Query:</label>");
        response.getWriter().println("<textarea id=\"Searchfield\" name=\"SearchQuery\" style=\"height:100px; width:300px;\"></textarea><br><br>");
        response.getWriter().println("<button type=\"submit\">Execute</button></form>");

        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String qr = request.getParameter("SearchQuery");

        response.setContentType("text/html");

        response.getWriter().println("<!DOCTYPE html>");
        response.getWriter().println("<html>");
        response.getWriter().println("<head>");
        response.getWriter().println("    <title>SQL Search</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("    <title>SQL Search</title>");
        response.getWriter().println("</head>");
        response.getWriter().println("<body>");
        response.getWriter().println("Search Form");
        response.getWriter().println("<p>Please enter a name to search for</p>");
        response.getWriter().println("<form action=\"SQL_Search\" method=\"Post\">");
        response.getWriter().println("<label for=\"Searchfield\">Enter Query:</label>");
        response.getWriter().println("<textarea id=\"Searchfield\" name=\"SearchQuery\" style=\"height:100px; width:300px;\"></textarea><br><br>");
        response.getWriter().println("<button type=\"submit\">Execute</button></form><br>");

        Query query;
        List<User> list = null;
        try {
            if (qr != null) {
                query = EntityMgr.getEntityManager().createNativeQuery(qr, User.class);
                @SuppressWarnings("unchecked")
                List<User> l = query.getResultList();
                list = l;
            }
            if (list != null && !list.isEmpty()) {
                for (User a : list) {
                    response.getWriter().println("user " + a.getId() + " is " + a.getFirstName() + " " + a.getLastName() + "<br>");
                }
            }
        } catch (PersistenceException e) {

            response.getWriter().println("Invalid query! " + e.getMessage());
        }
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");

    }

}
