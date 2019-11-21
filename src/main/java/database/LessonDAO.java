package database;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LessonDAO implements JdbcDAO<Lesson>{

    private static final String CREATE_TABLE = "CREATE TABLE schedule" +
            "(Id SERIAL PRIMARY KEY," +
            " TeacherId INTEGER NOT NULL, " +
            " SubjectId INTEGER NOT NULL,  " +
            " NumberLesson INTEGER NOT NULL, " +
            " DayLesson CHARACTER  VARYING(20) NOT NULL, " +
            " ClassId INTEGER NOT NULL, " +
            " RoomId INTEGER NOT NULL," +
            "UNIQUE(ClassId, NumberLesson,DayLesson), " +
            "UNIQUE(RoomId, NumberLesson,DayLesson), " +
            "UNIQUE(TeacherId, NumberLesson,DayLesson), " +
            "FOREIGN KEY (TeacherId) REFERENCES teacher(Id)," +
            "FOREIGN KEY (SubjectId) REFERENCES subject(Id)," +
            "FOREIGN KEY (ClassId) REFERENCES class (Id)," +
            "FOREIGN KEY (RoomId) REFERENCES room (Id)" +
            ");";

    private static final String INSERT = "INSERT INTO schedule(" +
            "NumberLesson,DayLesson,ClassId,RoomId,TeacherId,SubjectId) " +
            "VALUES (?,UPPER(?),?," +
            "?,?,?) RETURNING id";


    private static final String SELECT_BY_ID = "SELECT " +
            " schedule.id as scheduleId, schedule.dayLesson as DayLesson, " +
            " schedule.numberLesson as NumberLesson, " +
            " class.id as classId, class.number as classNumber, " +
            " room.id as roomId, room.number as roomNumber, " +
            " subject.id as subjectId, subject.name as subjectName, "+
            " teacher.id as teacherId, teacher.firstName as FirstName, " +
            " teacher.lastName as LastName "+
            " from schedule " +
            " JOIN class ON class.id = schedule.classid" +
            " JOIN room ON room.id = schedule.roomid" +
            " JOIN subject ON subject.id = schedule.subjectid" +
            " JOIN teacher ON teacher.id = schedule.teacherid" +
            " WHERE schedule.Id = ?";

    private static final String SELECT_ALL = "SELECT " +
            " schedule.id as scheduleId, schedule.dayLesson as DayLesson, " +
            " schedule.numberLesson as NumberLesson, " +
            " class.id as classId, class.number as classNumber, " +
            " room.id as roomId, room.number as roomNumber, " +
            " subject.id as subjectId, subject.name as subjectName, "+
            " teacher.id as teacherId, teacher.firstName as FirstName, " +
            " teacher.lastName as LastName "+
            " from schedule " +
            " JOIN class ON class.id = schedule.classid " +
            " JOIN room ON room.id = schedule.roomid " +
            " JOIN subject ON subject.id = schedule.subjectid " +
            " JOIN teacher ON teacher.id = schedule.teacherid ";

    private static final String SELECT_BY_GROUP = "SELECT " +
            " schedule.id as scheduleId, schedule.dayLesson as DayLesson, " +
            " schedule.numberLesson as NumberLesson, " +
            " class.id as classId, class.number as classNumber, " +
            " room.id as roomId, room.number as roomNumber, " +
            " subject.id as subjectId, subject.name as subjectName, "+
            " teacher.id as teacherId, teacher.firstName as FirstName, " +
            " teacher.lastName as LastName "+
            "from schedule " +
            " JOIN class ON class.id = schedule.classid" +
            " JOIN room ON room.id = schedule.roomid" +
            " JOIN subject ON subject.id = schedule.subjectid" +
            " JOIN teacher ON teacher.id = schedule.teacherid" +
            " WHERE LOWER(class.number) = LOWER(?)";

    private static final String SELECT_BY_SUBJECT = "SELECT " +
            " schedule.id as scheduleId, schedule.dayLesson as DayLesson, " +
            " schedule.numberLesson as NumberLesson, " +
            " class.id as classId, class.number as classNumber, " +
            " room.id as roomId, room.number as roomNumber, " +
            " subject.id as subjectId, subject.name as subjectName, "+
            " teacher.id as teacherId, teacher.firstName as FirstName, " +
            " teacher.lastName as LastName "+
            "from schedule " +
            " JOIN class ON class.id = schedule.classid" +
            " JOIN room ON room.id = schedule.roomid" +
            " JOIN subject ON subject.id = schedule.subjectid" +
            " JOIN teacher ON teacher.id = schedule.teacherid" +
            " WHERE LOWER(subject.name) = LOWER(?)";

    private static final String SELECT_BY_ROOM = "SELECT " +
            " schedule.id as scheduleId, schedule.dayLesson as DayLesson, " +
            " schedule.numberLesson as NumberLesson, " +
            " class.id as classId, class.number as classNumber, " +
            " room.id as roomId, room.number as roomNumber, " +
            " subject.id as subjectId, subject.name as subjectName, "+
            " teacher.id as teacherId, teacher.firstName as FirstName, " +
            " teacher.lastName as LastName "+
            "from schedule " +
            " JOIN class ON class.id = schedule.classid" +
            " JOIN room ON room.id = schedule.roomid" +
            " JOIN subject ON subject.id = schedule.subjectid" +
            " JOIN teacher ON teacher.id = schedule.teacherid" +
            " WHERE LOWER(room.number) = LOWER(?)";

    private static final String SELECT_BY_TEACHER = "SELECT " +
            " schedule.id as scheduleId, schedule.dayLesson as DayLesson, " +
            " schedule.numberLesson as NumberLesson, " +
            " class.id as classId, class.number as classNumber, " +
            " room.id as roomId, room.number as roomNumber, " +
            " subject.id as subjectId, subject.name as subjectName, "+
            " teacher.id as teacherId, teacher.firstName as firstName, " +
            " teacher.lastName as lastName "+
            " from schedule " +
            " JOIN room ON room.id = schedule.roomid" +
            " JOIN class ON class.id = schedule.classid" +
            " JOIN subject ON subject.id = schedule.subjectid" +
            " JOIN teacher ON teacher.id = schedule.teacherid" +
            " WHERE LOWER(teacher.firstName) = LOWER(?) " +
            " and LOWER(teacher.lastName) = LOWER(?)";

    private static final String DELETE_BY_ID = "DELETE FROM SCHEDULE WHERE id = ? ";
    private static final String DROP_TABLE = "DROP TABLE SCHEDULE";
    private static Connection connection;

    public LessonDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        LessonDAO.connection = connection;
    }

    private List<Lesson> parseResultSet(ResultSet rs) throws SQLException {
        List<Lesson> lesson = new ArrayList<>();
        while (rs.next()) {
            Lesson lessonLesson = new Lesson();
            lessonLesson.setId(rs.getInt("scheduleId"));
            lessonLesson.setNumberLesson(NumberLesson.getNumberLesson(
                    rs.getInt("NumberLesson")));
            lessonLesson.setDayLesson(Day.valueOf(rs.getString("DayLesson")));

            Group group = new Group();
            group.setId(rs.getInt("classId"));
            group.setNumber(rs.getString("classNumber"));
            lessonLesson.setGroup(group);

            Subject subject = new Subject();
            subject.setId(rs.getInt("subjectId"));
            subject.setName(rs.getString("subjectName"));
            lessonLesson.setSubject(subject);

            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("teacherId"));
            teacher.setFirstName(rs.getString("firstName"));
            teacher.setLastName(rs.getString("lastName"));
            lessonLesson.setTeacher(teacher);

            Room room = new Room();
            room.setId(rs.getInt("roomId"));
            room.setNumber(rs.getString("roomNumber"));
            lessonLesson.setRoom(room);

            lesson.add(lessonLesson);
        }
        return lesson;
    }

    public void create() throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(CREATE_TABLE)){
            statement.execute();
        }
    }

    public int insert(Lesson lesson) throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(INSERT)) {

            statement.setInt(1, lesson.getNumberLesson().getNumber());
            statement.setString(2, lesson.getDayLesson().name());
            statement.setInt(3, lesson.getGroup().getId());
            statement.setInt(4, lesson.getRoom().getId());
            statement.setInt(5, lesson.getTeacher().getId());
            statement.setInt(6, lesson.getSubject().getId());

            ResultSet rs = statement.executeQuery();
            rs.next();

            return rs.getInt(1);

        }

    }

    public Optional<List<Lesson>> selectByTeacherName(String firstName, String lastName)
            throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_TEACHER)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet rs = statement.executeQuery();

            List<Lesson> lessons = parseResultSet(rs);
            if (lessons.isEmpty()){
                return Optional.empty();
            }
                return Optional.of(lessons);
        }

    }


    public Optional<List<Lesson>> selectByNumberGroup(String numberGroup) throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_GROUP)) {

            statement.setString(1, numberGroup);

            ResultSet rs = statement.executeQuery();

            List<Lesson> lessons = parseResultSet(rs);
            if (lessons.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(lessons);
            }
        }

    }

    public Optional<List<Lesson>> selectByNameSubject(String subject) throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_SUBJECT)) {

            statement.setString(1, subject);

            ResultSet rs = statement.executeQuery();

            List<Lesson> lessons = parseResultSet(rs);
            if (lessons.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(lessons);
            }
        }

    }

    public Optional<List<Lesson>> selectByNumberRoom(String number) throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_ROOM)) {

            statement.setString(1, number);

            ResultSet rs = statement.executeQuery();

            List<Lesson> lessons = parseResultSet(rs);
            if (lessons.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(lessons);
            }
        }

    }

    public Optional<Lesson> selectById(int id) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            List<Lesson> lessons = parseResultSet(rs);
            if (lessons.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(lessons.get(0));
            }
        }
    }

    public Optional<List<Lesson>> selectAll() throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_ALL)){

            ResultSet rs = statement.executeQuery();

            List<Lesson> lessons = parseResultSet(rs);
            if (lessons.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(lessons);
            }
        }

    }

    public boolean deleteById(int id) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            return statement.executeUpdate() != 0;

        }
    }

    public boolean dropTable() throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(DROP_TABLE)) {

            return statement.executeUpdate() != 0;
        }
    }
}
