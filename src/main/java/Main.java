
import database.*;
import exceptions.TeacherException;
import model.*;
import service.ServiceJson;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Callable;

public class Main {

    static final String PATH_JSON = "src/main/resources/schedule.json";
    static final String PATH_PROPERTIES = "src/main/resources/config.properties";
    static  class ServiceJsonThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                ServiceJson serviceJson = new ServiceJson();
                ArrayList<Schedule> schedules =
                        (ArrayList<Schedule>) serviceJson.read(PATH_JSON);
                return "Successfully";
            } catch (IOException e) {
                return "Error";
            }
        }
    }

    public static void main(String[] args) throws SQLException {


        service.ServiceSchedule serviceSchedule = new service.ServiceSchedule();
        service.ServiceJson serviceJson = new service.ServiceJson();


        try (Connection connection = DBConnection.getConnection()){
            GroupDAO groupDAO = new GroupDAO().setConnection(connection);
            RoomDAO roomDAO = new RoomDAO().setConnection(connection);
            SubjectDAO subjectDAO = new SubjectDAO().setConnection(connection);
            TeacherDAO teacherDAO = new TeacherDAO().setConnection(connection);
            ScheduleDAO scheduleDAO = new ScheduleDAO().setConnection(connection);

            scheduleDAO.dropTable();
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

            for (Schedule schedule: schedules){

                int id;

                id = groupDAO.insert(schedule.getGroup());
                schedule.getGroup().setId(id);

                id = roomDAO.insert(schedule.getRoom());
                schedule.getRoom().setId(id);

                id = subjectDAO.insert(schedule.getSubject());
                schedule.getSubject().setId(id);

                id = teacherDAO.insert(schedule.getTeacher());
                schedule.getTeacher().setId(id);

                scheduleDAO.insert(schedule);

            }

            GroupDAOProxy groupDAOProxy = new GroupDAOProxy();
            groupDAOProxy.setConnection(connection);
            int id = groupDAOProxy.insert(new Group("333"));
            System.out.println(id);
            System.out.println(groupDAOProxy.selectById(3));




        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
