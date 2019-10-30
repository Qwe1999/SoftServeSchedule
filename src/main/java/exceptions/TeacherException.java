package exceptions;

import model.Teacher;

public class TeacherException extends Exception{
    private String message;

    public TeacherException(String message){
        super();
        this.message = message;
    }


    @Override
    public String toString() {
        return "exceptions.ScheduleException{" +
                "message='" + message + '\'' +
                '}';
    }
}
