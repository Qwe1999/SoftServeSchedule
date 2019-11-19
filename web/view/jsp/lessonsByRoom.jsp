<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.11.2019
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title>$Title$</title>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
<h2>Schedule</h2>

<table class="table table-condensed table-striped table-bordered">
    <caption>Room - ${roomNumber}</caption>
    <thead>
    <td>
    <th>Monday</th>
    <th>Tuesday</th>
    <th>Wednesday</th>
    <th>Thursday</th>
    <th>Friday</th>
    </td>
    </thead>
    <tbody>
    <c:forEach items="${numbers}" var="numberLesson">
        <tr>
            <td id="tdNumber" width="10">${numberLesson.number}</td>
            <c:forEach items="${days}" var="dayLesson">
                <c:if test="${schedule[numberLesson][dayLesson] != null}">
                    <td>
                        <p>Subject - ${schedule[numberLesson][dayLesson].subject.name}</p>
                        <p>
                            Teacher - ${schedule[numberLesson][dayLesson].teacher.firstName}
                                ${schedule[numberLesson][dayLesson].teacher.lastName}
                        </p>
                        <p>Group - ${schedule[numberLesson][dayLesson].group.number}</p>
                    </td>
                </c:if>
                <c:if test="${schedule[numberLesson][dayLesson] == null}">
                    <td></td>
                </c:if>
            </c:forEach>
        </tr>
    </c:forEach>

    </tbody>
</table>
</body>
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
</html>