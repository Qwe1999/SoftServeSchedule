
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import database.*;
import model.*;
import service.ServiceJson;
import service.ServiceLesson;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.Callable;

public class Main {

    static final String PATH_JSON = "src/main/resources/schedule.json";
    static final String PATH_PROPERTIES = "src/main/resources/config.properties";
    static class ServiceJsonThread implements Callable<String> {

        @Override
        public String call()  {
            try {
                ServiceJson serviceJson = new ServiceJson();
                ArrayList<Lesson> lessons =
                        (ArrayList<Lesson>) serviceJson.read(PATH_JSON);
                return "Successfully";
            } catch (IOException e) {
                return "Error";
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        ServiceLesson serviceLesson1 = new ServiceLesson();
        service.ServiceJson serviceJson = new service.ServiceJson();

        try (Connection connection = DBConnection.getConnection()){
            GroupDAO groupDAO = new GroupDAO(DBConnection.getConnection());
            RoomDAO roomDAO =  new RoomDAO(DBConnection.getConnection());
            SubjectDAO subjectDAO =  new SubjectDAO(DBConnection.getConnection());
            TeacherDAO teacherDAO =  new TeacherDAO(DBConnection.getConnection());
            LessonDAO lessonDAO =  new LessonDAO(DBConnection.getConnection());
            List<String> strings = new ArrayList<>();
            System.out.println(groupDAO.deleteById(10));
            /*scheduleDAO.dropTable();
            groupDAO.dropTable();
            roomDAO.dropTable();
            subjectDAO.dropTable();
            teacherDAO.dropTable();

            groupDAO.create();
            roomDAO.create();
            subjectDAO.create();
            teacherDAO.create();
            scheduleDAO.create();

            ArrayList<Schedule> schedules = (ArrayList<Schedule>) serviceJson.read(PATH_JSON);
            ServiceLesson serviceLesson = new ServiceLesson();
            for (Schedule schedule: schedules){
                serviceLesson.insertLesson(schedule);
            }
            System.out.println(serviceLesson.selectByDay(Day.Monday));*/
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
