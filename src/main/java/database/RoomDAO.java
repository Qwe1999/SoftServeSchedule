package database;

import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAO implements JdbcDAO<Room>{



    private static final String CREATE_TABLE = "CREATE TABLE room(Id SERIAL PRIMARY KEY," +
            "                               Number CHARACTER VARYING(20) NOT NULL UNIQUE);";
    private static final String INSERT = "INSERT INTO room(number) VALUES (LOWER(?)) RETURNING id";
    private static final String SELECT_BY_NUMBER = "SELECT * FROM ROOM WHERE LOWER(Number) = LOWER(?)";
    private static final String SELECT_BY_ID = "SELECT * FROM ROOM WHERE Id = ?";
    private static final String SELECT_ALL = "SELECT * FROM ROOM";
    private static final String DELETE_BY_ID = "DELETE FROM ROOM WHERE id = ?";
    private static final String DELETE_BY_NUMBER = "DELETE FROM ROOM WHERE " +
            "LOWER(number) = LOWER(?)";
    private static final String DROP_TABLE = "DROP TABLE ROOM";
    private static Connection connection;

    public RoomDAO(Connection connection) throws SQLException {
        this.connection = connection;
    }

    public static Connection getConnection() {
        return connection;
    }

    public static void setConnection(Connection connection) {
        RoomDAO.connection = connection;
    }

    private List<Room> parseResultSet(ResultSet rs) throws SQLException {
        List<Room> rooms = new ArrayList<>();

        while (rs.next()) {
            Room room = new Room();
            room.setId(rs.getInt("id"));
            room.setNumber(rs.getString("number"));
            rooms.add(room);
        }
        return rooms;
    }


    public void create() throws SQLException {
        try(PreparedStatement statement =
                    connection.prepareStatement(CREATE_TABLE)) {
            statement.execute();
        }
    }

    public int insert(Room room) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(INSERT)) {

            statement.setString(1, room.getNumber());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            return id;
        }

    }

    public Optional<Room> selectByNumber(String number) throws SQLException{

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_NUMBER)) {

            statement.setString(1, number);

            ResultSet rs = statement.executeQuery();

            List<Room> rooms = parseResultSet(rs);
            if (rooms.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(rooms.get(0));
            }
        }
    }

    public Optional<Room> selectById(int id) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            List<Room> rooms = parseResultSet(rs);
            if (rooms.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(rooms.get(0));
            }
        }

    }

    public Optional<List<Room>> selectAll() throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();

            List<Room> rooms = parseResultSet(rs);
            if (rooms.isEmpty()){
                return Optional.empty();
            }
            else {
                return Optional.of(rooms);
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

    public boolean deleteByNumber(String number) throws SQLException {

        try(PreparedStatement statement =
                    connection.prepareStatement(DELETE_BY_NUMBER)) {
            statement.setString(1, number);
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
