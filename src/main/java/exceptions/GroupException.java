package exceptions;

public class GroupException extends Exception{

    private String message;

    public GroupException(String message){
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
