<%-- 
    Document   : searchResults
    Created on : 10 сент. 2020 г., 14:35:08
    Author     : Igor Morenko (emailto:imorenko@yandex.ru)
--%>

<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Results</title>
    </head>
    <body>
        <%@ include file="searchHeader.jspf" %>
        Search results for 
        <c:if test='${search.artist == ""}'> keyword <b><c:out value='${search.keyword}'/></b></c:if>
        <c:if test='${search.artist != ""}'> artist <b><c:out value='${search.artist}'/></b></c:if>
        :<br/>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Num</th>
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
                        <td>${item.id}</td>
                        <td>${item.num}</td>
                        <td>${item.title}</td>
                        <td>${item.artist}</td>
                        <td align='center'><fmt:formatDate value="${item.releaseDate}" type="date" pattern="dd.MM.yyyy"/></td>
                        <td align='right'>$ ${item.listPrice}</td>
                        <td align='right'><b><font color='green'>$ ${item.price}</font></b></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
