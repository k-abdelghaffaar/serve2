package com.example;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiPredicate;

import com.listeners.LoginLogoutListener;
import com.managers.EntityMgr;
import com.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login extends HttpServlet {

    private EntityMgr db;

    @Override
    public void init() throws ServletException {
        super.init();
        db = EntityMgr.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String username = request.getParameter("username");
        String pass = request.getParameter("pass");

        Optional<Object> loggedUser = Optional.ofNullable(request.getSession(false).getAttribute("user"));
        User user = loggedUser.isPresent() ? (User) loggedUser.get() : (User) db.getUserByCredentials(username, pass);
        if (user != null) {
            // Successful login
            HttpSession session = request.getSession(true);
            request.setAttribute("user", user.getFirstName() + " " + user.getLastName());

            String csrfToken = UUID.randomUUID().toString();
            session.setAttribute("csrfToken", csrfToken);
            session.setAttribute("id", user.getId());
            session.setAttribute("logged", "logged");
            request.setAttribute("csrfToken", csrfToken);

            LoginLogoutListener.userLoggedIn();

            RequestDispatcher rd = request.getRequestDispatcher("/LoginCookie");
            rd.forward(request, response);
        } else {
            // Failed login
            response.sendRedirect("login.jsp?color=red");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // if (request.getSession().getAttribute("logged") != "logged")
        // showLoginPage(request, response);
        // else
        // alreadyLogged(request, response);

        BiPredicate<String, HttpSession> p = (a, s) -> (a != null ? !a.equals("false") : true)
                && s != null && s.getAttribute("id") != null;

        String aa = request.getParameter("logout");
        HttpSession session = request.getSession();
        if (p.test(aa, session)) {
            //User user = (User) db.getUserById((String) session.getAttribute("id").toString());

            alreadyLogged(request, response);
            // response.getWriter().println("<!DOCTYPE html>");
            // response.getWriter().println("<html>");
            // response.getWriter().println("<head>");
            // response.getWriter().println(" <title>Welcome Page</title>");
            // response.getWriter().println("</head>");
            // response.getWriter().println("<body>");
            // response.getWriter().println(" <h2>You are logged in, " + user.getFirstName()
            // + "!</h2>\n<form action=\"Login\" method=\"post\">");
            // response.getWriter().println("<input type=\"hidden\" name=\"csrfToken\"
            // value=\"" + session.getAttribute("csrfToken") + "\">");
            // response.getWriter().println("<button
            // type=\"submit\">Continue</button></form>");
            // response.getWriter().println("\n<form action=\"Login\" method=\"get\">");
            // response.getWriter().println("<input type=\"hidden\" name=\"logout\"
            // value=\"false\"required><br><br>");
            // response.getWriter().println("<button
            // type=\"submit\">Logout</button></form>");
            // response.getWriter().println("</body>");
            // response.getWriter().println("</html>");

        } else {
            showLoginPage(request, response);
        }

    }

    private void alreadyLogged(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = (String) request.getSession().getAttribute("id").toString();
        User user = db.getUserById(id);
        request.getSession(false).setAttribute("user", user);
        // response.getWriter().println("already logged in," + user.getFirstName());
        // request.setAttribute("user", user);
        // request.getRequestDispatcher("/Login").forward(request, response);
        response.getWriter().println("    <h2>You are logged in, " + user.getFirstName()
                + "!</h2>\n<form action=\"Login\" method=\"post\">");
        response.getWriter().println(
                "<input type=\"hidden\" name=\"csrfToken\" value=\"" + request.getSession().getAttribute("csrfToken")
                        + "\">");
        response.getWriter().println("<button type=\"submit\">Continue</button></form>");
        response.getWriter().println("\n<form action=\"Login\" method=\"get\">");
        response.getWriter().println("<input type=\"hidden\" name=\"logout\" value=\"false\"required><br><br>");
        response.getWriter().println("<button type=\"submit\">Logout</button></form>");
        


    }

    private void showLoginPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().invalidate();
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.include(request, response);
    }
}
