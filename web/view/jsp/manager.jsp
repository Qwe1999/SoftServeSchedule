<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 18.11.2019
  Time: 18:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <div class="header">
        <h1>Schedule</h1>
    </div>
    <link href="view/style/manager.css" rel="stylesheet"/>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body>
    <div class="body">
        <dl>
            <dt>Group</dt>
            <dd>

                    <input id="groupNumber" type="text" name="id" placeholder="Group number"/>
                    <button id="deleteGroup" class="btn btn-sm btn-primary">Delete Group</button>
                    <button id="addGroup" class="btn btn-sm btn-primary">Add Group</button>
                    <p id="messageGroup"></p>

            </dd>

            <dt>Teacher</dt>
            <dd>

                <input id="teacherFirstName" type="text" name="id" placeholder="Teacher's first name"/>
                <input id="teacherLastName" type="text" name="id" placeholder="Teacher's last name"/>
                <button id="deleteTeacher" class="btn btn-sm btn-primary">Delete Teacher</button>
                <button id="addTeacher" class="btn btn-sm btn-primary">Add Teacher</button>
                <p id="messageTeacher"></p>

            </dd>
            <dt>Room</dt>
            <dd>

                <input id="roomNumber" type="text" name="id" placeholder="Room's number"/>
                <button id="deleteRoom" class="btn btn-sm btn-primary">Delete Room </button>
                <button id="addRoom" class="btn btn-sm btn-primary">Add Room</button>
                <p id="messageRoom"></p>

            </dd>
            <dt>Subject</dt>
            <dd>

                <input id="SubjectName" type="text" name="id" placeholder="Subject's number"/>
                <button id="deleteSubject" class="btn btn-sm btn-primary">Delete Subject</button>
                <button id="addSubject" class="btn btn-sm btn-primary">Add Subject</button>
                <p id="messageSubject"></p>

            </dd>
            <dt>Lesson Add</dt>
            <dd>

                <input id="numberLesson" type="text" name="id" placeholder="Number lesson"/>
                <input id="day" type="text" name="id" placeholder="Day"/>
                <input id="groupLesson" type="text" name="id" placeholder="Group's number"/>
                <p></p>
                <input id="subjectLesson" type="text" name="id" placeholder="Subject's number"/>
                <input id="firstNameLesson" type="text" name="id" placeholder="Teacher's first name"/>
                <input id="lastNameLesson" type="text" name="id" placeholder="Teacher's last name"/>
                <input id="roomLesson" type="text" name="id" placeholder="Room"/>
                <button id="addLesson" class="btn btn-sm btn-primary">Add Lesson</button>
                <p id="messageLessonAdd"></p>

            </dd>

            <dt>Lesson delete</dt>
            <dd>
                <input id="idLesson" type="text" name="id" placeholder="id Lesson"/>
                <button id="deleteLesson" class="btn btn-sm btn-primary">Delete Lesson</button>
                <p id="messageLessonDelete"></p>

            </dd>
        </dl>
    </div>
</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="view/script/manager.js"></script>
</html>
