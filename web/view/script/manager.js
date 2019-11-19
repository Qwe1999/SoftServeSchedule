$(document).on("click", "#deleteGroup", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletGroup?groupNumber=" + $('#groupNumber').val(),
        success: function(data){
            if (data.error) {
                $("#messageGroup").text("Error");
            }
            else{
                $("#messageGroup").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageGroup").text(status + error);
        },
        type : "delete"
    });
});

$(document).on("click", "#addGroup", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletGroup?groupNumber=" + $('#groupNumber').val(),
        success: function(data){
            if (data.error) {
                $("#messageGroup").text("Error");
            }
            else{
                $("#messageGroup").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageGroup").text(status);
        },
        type : "POST"
    });
});

$(document).on("click", "#deleteSubject", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletSubject?subjectName=" + $('#subjectName').val(),
        success: function(data){
            if (data.error) {
                $("#messageSubject").text("Error");
            }
            else{
                $("#messageSubject").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageSubject").text(status + error);
        },
        type : "delete"
    });
});

$(document).on("click", "#addSubject", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletSubject?subjectName=" + $('#subjectName').val(),
        success: function(data){
            if (data.error) {
                $("#messageSubject").text("Error");
            }
            else{
                $("#messageSubject").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageSubject").text(status);
        },
        type : "POST"
    });
});

$(document).on("click", "#deleteRoom", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletRoom?roomNumber=" + $('#roomNumber').val(),
        success: function(data){
            if (data.error) {
                $("#messageRoom").text("Error");
            }
            else{
                $("#messageRoom").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageRoom").text(status + error);
        },
        type : "delete"
    });
});

$(document).on("click", "#addRoom", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletRoom?roomNumber=" + $('#roomNumber').val(),
        success: function(data){
            if (data.error) {
                $("#messageRoom").text("Error");
            }
            else{
                $("#messageRoom").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageRoom").text(status);
        },
        type : "POST"
    });
});

$(document).on("click", "#deleteTeacher", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletTeacher?firstName=" + $('#teacherFirstName').val()
            + "&lastName=" + $('#teacherLastName').val(),
        success: function(data){
            if (data.error) {
                $("#messageTeacher").text("Error");
            }
            else{
                $("#messageTeacher").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageTeacher").text(status + error);
        },
        type : "delete"
    });
});

$(document).on("click", "#addTeacher", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletTeacher?firstName=" + $('#teacherFirstName').val()
            + "&lastName=" + $('#teacherLastName').val(),
        success: function(data){
            if (data.error) {
                $("#messageTeacher").text("Error");
            }
            else{
                $("#messageTeacher").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageTeacher").text(status);
        },
        type : "POST"
    });
});

$(document).on("click", "#addLesson", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletLessons?firstName=" + $('#firstNameLesson').val()
            + "&lastName=" + $('#lastNameLesson').val()
            + "&groupNumber=" + $('#groupLesson').val()
            + "&subjectName=" + $('#subjectLesson').val()
            + "&roomNumber=" + $('#roomLesson').val()
            + "&day=" + $('#day').val()
            + "&numberLesson=" + $('#numberLesson').val(),
        success: function(data){
            if (data.error) {
                $("#messageLessonAdd").text("Error");
            }
            else{
                $("#messageLessonAdd").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageLessonAdd").text("asd");
        },
        type : "POST"
    });
});

$(document).on("click", "#deleteLesson", function () {
    $.ajax({
        url : "http://localhost:8082/unnamed4/ServletLessons?id=" + $('#idLesson').val(),
        success: function(data){
            if (data.error) {
                $("#messageLessonDelete").text("Error");
            }
            else{
                $("#messageLessonDelete").text("Success");
            }
        },
        error: function (request,status, error) {
            console.log(error);
            $("#messageLessonDelete").text(status + error);
        },
        type : "delete"
    });
});