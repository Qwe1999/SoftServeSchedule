
import database.*;
import exceptions.TeacherException;
import model.*;
import service.ServiceJson;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
        public String call()  {
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
            GroupDAO groupDAO = (GroupDAO) new GroupDAO().setConnection(connection);
            RoomDAO roomDAO = (RoomDAO) new RoomDAO().setConnection(connection);
            SubjectDAO subjectDAO = (SubjectDAO) new SubjectDAO().setConnection(connection);
            TeacherDAO teacherDAO = (TeacherDAO) new TeacherDAO().setConnection(connection);
            ScheduleDAO scheduleDAO = (ScheduleDAO) new ScheduleDAO().setConnection(connection);

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

                try {
                    id = groupDAO.insert(schedule.getGroup());
                }
                catch (SQLException e){
                    id = groupDAO.selectByNumber(
                            schedule.getGroup().getNumber() ).getId();
                }

                schedule.getGroup().setId(id);

                try {
                    id = roomDAO.insert(schedule.getRoom());
                }
                catch (SQLException e){
                    id = roomDAO.selectByNumber(
                            schedule.getRoom().getNumber()).getId();
                }
                schedule.getRoom().setId(id);

                try {
                    id = subjectDAO.insert(schedule.getSubject());
                }
                catch (SQLException e){
                    id = subjectDAO.selectByName(
                            schedule.getSubject().getName()).getId();
                    e.printStackTrace(System.out);
                }

                schedule.getSubject().setId(id);

                try {
                    id = teacherDAO.insert(schedule.getTeacher());
                }
                catch (SQLException e){
                    id = teacherDAO.selectByName(schedule.getTeacher().getFirstName(),
                            schedule.getTeacher().getLastName()).getId();

                }
                schedule.getTeacher().setId(id);

                id = scheduleDAO.insert(schedule);



            }





        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
