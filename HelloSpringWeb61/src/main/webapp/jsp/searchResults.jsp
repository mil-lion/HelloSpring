<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<HTML>
    <HEAD>
        <TITLE>Search Results</TITLE>
    </HEAD>
    <BODY>
        <%@ include file="/jsp/header.jspf" %>
        Search results for keyword <B><c:out value='${param.keyword}'/></B>:
        <BR/>
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
                <c:forEach items="${results}" var="item">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.title}</td>
                        <td>${item.artist}</td>
                        <td><fmt:formatDate value="${item.releaseDate}" type="date" pattern="dd.MM.yyyy"/></td>
                        <td>$ ${item.listPrice}</td>
                        <td><b><font color='green'>$ ${item.price}</font></b></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </BODY>
</HTML>
