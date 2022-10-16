<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<html>
    <head>
        <title>Search Results</title>
    </head>
    <body>
        <%@ include file="/WEB-INF/views/searchHeader.jspf" %>
        Search results for 
        <c:if test='${search.artist == ""}'> <i>keyword</i> <b><c:out value='${search.keyword}'/></b></c:if>
        <c:if test='${search.artist != ""}'> <i>artist</i> <b><c:out value='${search.artist}'/></b></c:if>
        :<br/>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Artist</th>
                    <th>Release Date</th>
                    <th>List Price</th>
                    <th><font color='green'>Your Price</font></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${search.results}" var="item">
                    <tr>
                        <td><c:out value="${item.id}"/></td>
                        <td><c:out value="${item.title}"/></td>
                        <td><c:out value="${item.artist}"/></td>
                        <td align='center'><fmt:formatDate value="${item.releaseDate}" type="date" pattern="dd.MM.yyyy"/></td>
                        <td align='right'>$<c:out value="${item.listPrice}"/></td>
                        <td align='right'><b><font color='green'>$<c:out value="${item.price}"/></font></b></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
