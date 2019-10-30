import exceptions.ScheduleException;
import exceptions.ScheduleServiceException;
import model.*;
import service.ServiceJson;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {

    static  class ServiceJsonThread implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                ServiceJson serviceJson = new ServiceJson();
                ArrayList<Schedule> schedules =
                        (ArrayList<Schedule>) serviceJson.read("src/main/resources/schedule.json");
                return "Successfully";
            } catch (IOException e) {
                return "Error";
            }
        }
    }

    public static void main(String[] args) {

        ServiceJsonThread jsonThread = new ServiceJsonThread();
        FutureTask<String> future = new FutureTask<>(jsonThread);
        new Thread(future).start();

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }



        service.ServiceSchedule serviceSchedule = new service.ServiceSchedule();
        service.ServiceJson serviceJson = new service.ServiceJson();

        try {
            ArrayList<model.Schedule> schedules = (ArrayList<model.Schedule>) serviceJson.read("src/main/resources/schedule.json");
            serviceSchedule.setSchedules(schedules);

            Schedule schedule  = new Schedule();
            schedule.setDay(Day.Friday);
            schedule.setGroup(new Group("123"));
            schedule.setNumberLesson(NumberLesson.eight);
            schedule.setSubject(new Subject("Math"));
            schedule.setTeacher(new Teacher("ASD","FVC"));

            serviceSchedule.addLesson(schedule);
            System.out.println(serviceSchedule.getByTeacher(new model.Teacher("ASD","FVC")));
        } catch (IOException | ScheduleServiceException e) {
            e.printStackTrace();
        }

    }

}
