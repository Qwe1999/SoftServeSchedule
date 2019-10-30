package exceptions;

public class ScheduleServiceException extends Exception {
    private String message;

    public ScheduleServiceException(String message){
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
