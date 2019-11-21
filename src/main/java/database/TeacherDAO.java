package database;

import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TeacherDAO implements JdbcDAO<Teacher>{

    private static final String INSERT = "INSERT INTO teacher(FirstName,LastName) VALUES (?,?) RETURNING id";
    private static final String CREATE_TABLE = "CREATE TABLE teacher(Id SERIAL PRIMARY KEY,"+
                "FirstName CHARACTER VARYING(30) NOT NULL ,LastName CHARACTER VARYING(30) NOT NULL," +
                "UNIQUE(FirstName, LastName) );";
    private static final String SELECT_BY_NAME = "SELECT * FROM Teacher WHERE " +
            "LOWER(FirstName) = LOWER(?) AND LOWER(LastName) = LOWER(?)";
    private static final String SELECT_BY_ID = "SELECT * FROM Teacher WHERE Id = ?";
    private static final String SELECT_ALL= "SELECT * FROM TEACHER";
    private static final String DELETE_BY_NAME = "DELETE FROM TEACHER WHERE " +
            "LOWER(firstName) = LOWER(?) and LOWER(lastName) = LOWER(?)";
    private static final String DELETE_BY_ID = "DELETE FROM TEACHER WHERE id = ?";
    private static final String DROP_TABLE = "DROP TABLE TEACHER ";
    private static Connection connection;

    public TeacherDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        TeacherDAO.connection = connection;
    }

    private List<Teacher> parseResultSet(ResultSet rs) throws SQLException {

        List<Teacher> teachers = new ArrayList<>();
        while (rs.next()) {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setFirstName(rs.getString("firstName"));
            teacher.setLastName(rs.getString("lastName"));
            teachers.add(teacher);
        }
        return teachers;
    }

    public void create() throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(CREATE_TABLE)) {

            statement.execute();
        }
    }

    public int insert(Teacher teacher) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(INSERT)) {

            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            return id;

        }
    }

    public Optional<Teacher> selectByName(String firstName, String lastName) throws SQLException{

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_NAME)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet rs = statement.executeQuery();

            List<Teacher> teachers = parseResultSet(rs);
            if (teachers.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(teachers.get(0));
            }
        }
    }

    public Optional<Teacher> selectById(int id) throws SQLException{

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            List<Teacher> teachers = parseResultSet(rs);
            if (teachers.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(teachers.get(0));
            }
        }
    }

    public Optional<List<Teacher>> selectAll() throws SQLException{

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();

            List<Teacher> teachers = parseResultSet(rs);
            if (teachers.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(teachers);
            }
        }
    }

    public boolean deleteById(int id) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            return statement.executeUpdate() != 0 ;

        }
    }

    public boolean deleteByName(String firstName, String lastName) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(DELETE_BY_NAME)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);
            return statement.executeUpdate() != 0 ;
        }
    }


    public void dropTable() throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(DROP_TABLE)) {
            statement.execute();
        }
    }
}
