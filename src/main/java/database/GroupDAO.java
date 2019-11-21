package database;

import model.Group;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDAO implements JdbcDAO<Group>{

    private static final Logger log = Logger.getLogger(GroupDAO.class);
    private static final String INSERT = "INSERT INTO class(number) VALUES (LOWER (?)) RETURNING id";
    private static final String CREATE_TABLE = "CREATE TABLE class(Id SERIAL  PRIMARY KEY," +
                                        "Number CHARACTER VARYING(30) NOT NULL UNIQUE);";
    private static final String SELECT_BY_NUMBER = "SELECT * FROM CLASS WHERE LOWER(Number) = LOWER(?)";
    private static final String SELECT_BY_ID = "SELECT * FROM CLASS WHERE Id = ?";
    private static final String SELECT_ALL= "SELECT * FROM CLASS ";
    private static final String DELETE_BY_ID = "DELETE FROM CLASS WHERE id = ? ";
    private static final String DELETE_BY_NUMBER = "DELETE FROM CLASS WHERE LOWER(number) = LOWER(?) ";
    private static final String DROP_TABLE = "DROP TABLE CLASS ";

    private static Connection connection;

    public GroupDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        GroupDAO.connection = connection;
    }

    private List<Group> parseResultSet(ResultSet rs) throws SQLException {
        List<Group> groups = new ArrayList<>();
        while (rs.next()) {
            Group group = new Group();
            group.setId(rs.getInt("id"));
            group.setNumber(rs.getString("number"));
            groups.add(group);
        }

        log.info("Return groups");
        return groups;
    }

    public void create() throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(CREATE_TABLE)) {
            log.info("Call create");
            statement.execute();
        }
    }

    @Override
    public int insert(Group group) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(INSERT)) {

            statement.setString(1, group.getNumber());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            log.info("Inserted group");
            return id;
        }
    }


    public Optional<Group> selectByNumber(String  number) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(SELECT_BY_NUMBER)) {

            statement.setString(1, number);
            ResultSet rs = statement.executeQuery();

            List<Group> groups = parseResultSet(rs);
            if (groups.isEmpty()){
                log.info("Group with number = " + number + " is not exist");
                return Optional.empty();
            }
            else {
                log.info("Return group");
                return Optional.of(groups.get(0));
            }
        }
    }

    @Override
    public Optional<Group> selectById(int id) throws SQLException {

        try(PreparedStatement statement =  connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            List<Group> groups = parseResultSet(rs);
            if (groups.isEmpty()){
                log.info("Group with id = " + id + " is not exist");
                return Optional.empty();
            }
            else {
                log.info("Return group");
                return Optional.of(groups.get(0));
            }
        }
    }

    public Optional<List<Group>> selectAll() throws SQLException{

        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {


            ResultSet rs = statement.executeQuery();
            List<Group> groups = parseResultSet(rs);
            if (groups.isEmpty()){
                log.info("Are not exist any groups");
                return Optional.empty();
            }
            else {
                log.info("Return groups");
                return Optional.of(groups);
            }

        }
    }

    @Override
    public boolean deleteById(int id) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);

            return statement.executeUpdate() != 0 ;
        }
    }

    public boolean deleteByNumber(String number) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(DELETE_BY_NUMBER)) {

            statement.setString(1, number);
            return statement.executeUpdate() ==1;

        }
    }

    public boolean dropTable() throws SQLException {
        try(PreparedStatement statement =  connection.prepareStatement(DROP_TABLE)) {

            return statement.execute();
        }
    }
}
