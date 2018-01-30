<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Orik
  Date: 05.01.2018
  Time: 0:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        * {
            margin: 0;
            padding: 0
        }

        html, code {
            font: 15px/22px arial, sans-serif
        }

        html {
            background: #fff;
            color: #222;
            padding: 15px
        }

        body {
            margin: 7% auto 0;
            max-width: 390px;
            min-height: 180px;
            padding: 30px 0 15px
        }

        * > body {
            background: url(//www.google.com/images/errors/robot.png) 100% 5px no-repeat;
            padding-right: 205px
        }

        p {
            margin: 11px 0 22px;
            overflow: hidden
        }

        ins {
            color: #777;
            text-decoration: none
        }

        a img {
            border: 0
        }

        @media screen and (max-width: 772px) {
            body {
                background: none;
                margin-top: 0;
                max-width: none;
                padding-right: 0
            }
        }


    </style>
</head>
<body>
<center>
    <c:out value="${throwable}"/>
    <p>The requested URL:<br>
        <c:out value="${uri}"/>
        <br>
        <ins>Code of error</ins>
    <p><b> <c:out value="${code}"/>
    </b>
    <br>
    <ins>That’s all we know.</ins>
</center>

</body>
</html>
