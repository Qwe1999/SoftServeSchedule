package database;

import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectDAO implements JdbcDAO<Subject> {


    private static final String CREATE_TABLE = "CREATE TABLE subject(Id SERIAL PRIMARY KEY," +
            "                       Name CHARACTER VARYING(30) NOT NULL UNIQUE);";
    private static final String INSERT = "INSERT INTO SUBJECT(Name) VALUES (LOWER(?)) RETURNING id";
    private static final String SELECT_BY_NAME = "SELECT * FROM SUBJECT WHERE LOWER(Name) = LOWER(?)";
    private static final String SELECT_BY_ID = "SELECT * FROM SUBJECT WHERE Id =?";
    private static final String SELECT_ALL = "SELECT * FROM SUBJECT";
    private static final String DELETE_BY_ID = "DELETE FROM SUBJECT WHERE ID = ?";
    private static final String DELETE_BY_NAME = "DELETE FROM SUBJECT WHERE LOWER(NAME) = LOWER(?)";
    private static final String DROP_TABLE = "DROP TABLE SUBJECT";
    private static Connection connection;


    public SubjectDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        SubjectDAO.connection = connection;
    }

    private List<Subject> parseResultSet(ResultSet rs) throws SQLException {

        List<Subject> subjects = new ArrayList<>();
        while (rs.next()) {
            Subject subject = new Subject();
            subject.setId(rs.getInt("id"));
            subject.setName(rs.getString("name"));
            subjects.add(subject);
        }
        return subjects;
    }

    public void create() throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(CREATE_TABLE)) {
            statement.execute();
        }
    }

    public int insert(Subject subject) throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(INSERT)) {
            statement.setString(1, subject.getName());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            return id;
        }
    }

    public Optional<Subject> selectByName(String name) throws SQLException{

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_NAME)) {
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            List<Subject> subjects = parseResultSet(rs);
            if (subjects.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(subjects.get(0));
            }
        }
    }

    public Optional<Subject> selectById(int id) throws SQLException {

       try(PreparedStatement statement =
                   connection.prepareStatement(SELECT_BY_ID)) {
           statement.setInt(1, id);

           ResultSet rs = statement.executeQuery();

           List<Subject> subjects = parseResultSet(rs);
           if (subjects.isEmpty()){
               return Optional.empty();
           }
           else {
               return Optional.of(subjects.get(0));
           }

       }
    }

    public Optional<List<Subject>> selectAll() throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();

            List<Subject> subjects = parseResultSet(rs);
            if (subjects.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(subjects);
            }
        }
    }

    public boolean deleteById(int id) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            return statement.executeUpdate() !=0;
        }
    }

    public boolean deleteByName(String name) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(DELETE_BY_NAME)) {

            statement.setString(1, name);
            return statement.executeUpdate() != 0;

        }
    }

    public boolean dropTable() throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(DROP_TABLE)) {
            statement.execute();
            return true;
        }
    }
}
