
$(document).on("click", "#showGroup", function () {
    location.href =
        "http://localhost:8082/unnamed4/ServletGroup?groupNumber=" + $('#groupNumber').val() ;
});

$(document).on("click", "#showRoom", function () {
    location.href =
        "http://localhost:8082/unnamed4/ServletRoom?roomNumber=" + $('#roomNumber').val() ;
});

$(document).on("click", "#showSubject", function () {
    location.href =
        "http://localhost:8082/unnamed4/ServletSubject?subjectName=" + $('#subjectName').val() ;
});

$(document).on("click", "#showTeacher", function () {
    location.href =
        "http://localhost:8082/unnamed4/ServletTeacher?firstName=" + $('#teacherFirstName').val()
                    + "&lastName=" + $('#teacherLastName').val() ;
});
