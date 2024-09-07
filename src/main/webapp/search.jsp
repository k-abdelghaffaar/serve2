<%@ page language="java" contentType="text/html"
    import="java.util.*,com.model.User,com.managers.EntityMgr,jakarta.persistence.Query" %>
    <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
    <%@ taglib prefix="c" uri="jakarta.tags.core" %>



    <form action="search.jsp" method="get">
        <label for="Searchfield">Find:</label>
        <input type="text" id="Searchfield" name="SearchName" required><br><br>
        <button type="submit">Search</button>
    </form>

    <c:if test="${not empty param.SearchName}">
        <% 
            String name=request.getParameter("SearchName"); 
            String res = EntityMgr.getInstance().SearchForJSP(name);
            
            //request.setAttribute("userMap", userMap);
            request.setAttribute("res", res);

            String sqlQuery = "SELECT * FROM User WHERE firstName LIKE ? OR lastName LIKE ?";

            Query query = EntityMgr.getEntityManager().createNativeQuery(sqlQuery, User.class);
            query.setParameter(1, "%" + name + "%");
            query.setParameter(2, "%" + name + "%");

            List<User> list = query.getResultList();
            request.setAttribute("list", list);
            
        %>
        <h2>Search Results:</h2>
        <h3>By List :</h3>
                    <c:if test="${not empty list}">
                        <c:forEach var="entry" items="${list}">
                            first:
                            <c:out value="${entry.firstName}" />
                            last:
                            <c:out value="${entry.lastName}" />
                            <hr/>
                        </c:forEach>
                    </c:if>
        <h3>By function that returns a String then we use split :</h3>
                    <c:if test="${not empty res}">
                        
                        <p>${res}</p>
                        <c:forEach var="entry" items="${fn:split(res, ',')}">
                            <p>${entry}</p>
                            <hr />
                        </c:forEach>
                       
                    </c:if>
                    
    </c:if>