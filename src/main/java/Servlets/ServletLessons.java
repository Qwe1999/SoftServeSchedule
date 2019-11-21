package Servlets;

import database.DBConnection;
import model.*;
import service.ServiceSchedule;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/ServletLessons")
public class ServletLessons extends HttpServlet {
    private static final String ERROR_PAGE = "/view/jsp/error.jsp";
    private static final String LESSONS_PAGE = "/view/jsp/lessonsAll.jsp";
    private static final String ERROR_MESSAGE = "Error, please come later";
    private static final String DAY_PARAM = "day";
    private static final String NUMBER_LESSON_PARAM = "numberLesson";
    private static final String GROUP_NUMBER_PARAM = "groupNumber";
    private static final String SUBJECT_NAME_PARAM = "subjectName";
    private static final String ROOM_NUMBER_PARAM = "roomNumber";
    private static final String TEACHER_FIRST_NAME_PARAM = "firstName";
    private static final String TEACHER_LAST_NAME_PARAM = "lastName";


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String forward;
        try {
            ServiceSchedule serviceSchedule = new ServiceSchedule();
            List<Lesson> lessons = serviceSchedule.selectAll();

            if (lessons.isEmpty()) {
                request.setAttribute("errorMessage",
                        "Schedule don't have lessons");
                forward = ERROR_PAGE;
            } else {
                Map<NumberLesson, Map<Day,Lesson>> schedule;
                schedule = serviceSchedule.getMapSchedule(lessons);
                List<Group> groups = serviceSchedule.selectAllGroup();

                request.setAttribute("groups",groups);
                request.setAttribute("schedule", schedule);
                request.setAttribute("numbers", NumberLesson.values());
                request.setAttribute("days", Day.values());

                forward = LESSONS_PAGE;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            forward = ERROR_PAGE;
            request.setAttribute("errorMessage", ERROR_MESSAGE);
        }

        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(forward);
        response.setContentType("application/json");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            ServiceSchedule serviceSchedule = new ServiceSchedule() ;

            Group group = new Group(request.getParameter(GROUP_NUMBER_PARAM).toLowerCase());
            Subject subject = new Subject(request.getParameter(SUBJECT_NAME_PARAM).toLowerCase());
            Room room = new Room(request.getParameter(ROOM_NUMBER_PARAM).toLowerCase());

            Teacher teacher = new Teacher();
            teacher.setFirstName(request.getParameter(TEACHER_FIRST_NAME_PARAM).toLowerCase());
            teacher.setLastName(request.getParameter(TEACHER_LAST_NAME_PARAM).toLowerCase());
            String dayStr = request.getParameter(DAY_PARAM).toUpperCase();

            Day day = Day.valueOf(dayStr);
            NumberLesson numberLesson =
                    NumberLesson.getNumberLesson(
                            Integer.parseInt(request.getParameter(NUMBER_LESSON_PARAM)));

            Lesson lesson = new Lesson();
            lesson.setTeacher(teacher);
            lesson.setSubject(subject);
            lesson.setRoom(room);
            lesson.setGroup(group);
            lesson.setDayLesson(day);
            lesson.setNumberLesson(numberLesson);

            serviceSchedule.insertLesson(lesson);

        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{ \"error\" : \"True\" }" );
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            ServiceSchedule serviceSchedule = new ServiceSchedule() ;

            int id = Integer.parseInt(request.getParameter("id"));
            serviceSchedule.deleteLesson(id);
        } catch (SQLException e) {
            e.printStackTrace();
            response.setContentType("application/json");
            response.getWriter().write("{ \"error\" : \"True\" }" );
        }
    }


    @Override
    public void destroy() {
        try {
            DBConnection.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
