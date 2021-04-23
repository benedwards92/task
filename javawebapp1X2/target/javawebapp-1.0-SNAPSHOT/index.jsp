<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import = "java.sql.Connection" %>
<%@ page import = "com.example.javawebapp.JSONToDatabase" %>
<%@ page import = "java.sql.DriverManager" %>

<!DOCTYPE html>
<html>
<head>
    <title>1X2 Java Web Application</title>
</head>

<body>

<h1><%= "1X2 Java Web Application" %>
</h1>
<br/>
<%
    //print statement on index page to show if connection to the database has been established
    JSONToDatabase db = new JSONToDatabase();
    Connection conn = db.getConnection();
    if (conn == null){
        out.print("Connection to database failed!");
    } else {
        out.print("Connection to database established...");
    }

%>



</body>











</html>