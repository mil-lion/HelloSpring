<%-- 
    Document   : index
    Created on : 10 сент. 2020 г., 14:35:08
    Author     : Igor Morenko (emailto:imorenko@yandex.ru)
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to JavaTunes</title>
    </head>
    <body>
        <%@ include file="header.jspf" %>
        <hr/>
        <h2><a href="${pageContext.request.contextPath}/search.do">Search for tunes</a></h2>
    </body>
</html>