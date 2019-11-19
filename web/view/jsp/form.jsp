<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 11.11.2019
  Time: 16:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <div class="header">
        <h1>Schedule</h1>
    </div>
</head>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
<link href="view/style/form.css" rel="stylesheet"/>
<body>
    <div class="body">
        <dl>
            <dt>Group</dt>
            <dd>
                <input id="groupNumber" type="text" name="id" placeholder="Group's number"/>
                <button id="showGroup" class="btn btn-sm btn-primary">Show group's lessons</button>
            </dd>

            <dt>Teacher</dt>
            <dd>
            <input id="teacherFirstName" type="text" name="id" placeholder="Teacher's first name"/>
            <input id="teacherLastName" type="text" name="id" placeholder="Teacher's last name"/>
            <button id="showTeacher" class="btn btn-sm btn-primary">Show teacher's lessons</button>
            </dd>

            <dt>Room</dt>
            <dd>
            <input id="roomNumber" type="text" name="id" placeholder="Room's number"/>
            <button id="showRoom" class="btn btn-sm btn-primary">Show room's lessons</button>
            </dd>

            <dt>Subject</dt>
            <dd>
            <input id="subjectName" type="text" name="id" placeholder="Subject's name"/>
            <button id="showSubject" class="btn btn-sm btn-primary">Show subject's lessons</button>
            </dd>


        </dl>
    </div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="view/script/form.js"></script>
</html>
