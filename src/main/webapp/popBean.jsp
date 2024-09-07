<%@ page import="java.util.*,com.model.User %>


<jsp:useBean  class = "com.model.UserBean" id = "userBean" scope="request">
    <%
        // populate a user object
        String username = request.getParameter("user");
        String pass = request.getParameter("pass");
        User user = (User) db.getUserByCredentials(username, pass);

        //user.setFirstName(request.getParameter("firstName"));
        //user.setLastName(request.getParameter("lastName"));

        // set the object in the UserBean
        userBean.setUser(user);
    %>

    <p>First Name: ${userBean.user.getFirstName()}</p>
    <p>Last Name: ${userBean.user.getLastName()}</p>
</jsp:useBean>

<!-- <jsp:forward page="displayUser.jsp" /> -->