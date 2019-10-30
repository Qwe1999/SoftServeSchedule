package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceSQL {

    Connection conn;

    public ServiceSQL(Connection conn) {
        this.conn = conn;
    }


    public void createRoom() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE room" +
                "(" +
                "    Id SERIAL PRIMARY KEY," +
                "    numberRoom INTEGER " +
                ");");
    }

    public void createClass() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE class" +
                "(" +
                "    Id SERIAL  PRIMARY KEY," +
                "    numberClass INTEGER " +
                ");");
    }

    public void createTeacher() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE teacher" +
                "(" +
                " Id SERIAL PRIMARY KEY," +
                " FirstName NAME ," +
                " LastName NAME "+
                ");");
    }

    public void createSubject() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE subject" +
                "(" +
                " Id SERIAL PRIMARY KEY," +
                " nameSubject CHARACTER VARYING(30)" +
                ");");
    }

    public void createTeacherSubject() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE teacherSubject" +
                "(" +
                " Id SERIAL PRIMARY KEY," +
                " subjectId INTEGER, " +
                " teacherId INTEGER, " +
                "FOREIGN KEY (subjectId) REFERENCES Subject (Id)," +
                "FOREIGN KEY (teacherId) REFERENCES Teacher (Id)" +
                ");");
    }

    public void createSchedule() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("CREATE TABLE schedule" +
                "(" +
                " Id SERIAL PRIMARY KEY," +
                " teacherSubjectId INTEGER, " +
                " classId INTEGER, " +
                " roomId INTEGER, " +
                "FOREIGN KEY (teacherSubjectId) REFERENCES teacherSubject (Id)," +
                "FOREIGN KEY (classId) REFERENCES class (Id)," +
                "FOREIGN KEY (roomId) REFERENCES room (Id)" +
                ");");
    }

    public void dropAllTables() throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("DROP TABLE room,class,teacher,subject,teacherSubject,schedule");
    }

    public void addClass(int numberClass) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO class VALUES (" + numberClass + ")");
    }

    public void addRoom(int numberRoom) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO room VALUES (" + numberRoom + ")");
    }

    public void addTeacher(String firstName,String lastName) throws SQLException {
        Statement statement = conn.createStatement();
        statement.execute("INSERT INTO teacher VALUES ('" + firstName + "' , '"
                + lastName + "')");
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


}
