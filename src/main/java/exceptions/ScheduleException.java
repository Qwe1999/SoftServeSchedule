package exceptions;

public class ScheduleException extends Exception{
    private String message;

    public ScheduleException(String message){
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
