package database;

import model.Group;
import model.Room;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

class RoomDAOTest {


    static RoomDAO roomDAO;
    static Connection connection;

    @BeforeAll
    static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/scheduleTest?user=postgres&password=root");
        connection.setAutoCommit(false);
        roomDAO = new RoomDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insert(Room room) throws SQLException {
        Assertions.assertDoesNotThrow(() -> roomDAO.insert(room));
    }

    static Stream<Room> insert() {
        return Stream.of(
                new Room("333"),
                new Room("2123"),
                new Room("353-A")
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insertNegative(Room room) throws SQLException {
        Assertions.assertThrows(PSQLException.class,() -> roomDAO.insert(room));
    }

    static Stream<Room> insertNegative() {
        return Stream.of(
                new Room("311"),
                new Room("555"),
                new Room("111")
        );
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = { "311","555","111"})
    void selectByNumber(String numberRoom) throws SQLException {
        Assertions.assertEquals(numberRoom,
                roomDAO.selectByNumber(numberRoom).get().getNumber());
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = { "DDDDAS","zxc","213as"})
    void selectByNumberNegative(String numberRoom) throws SQLException {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> roomDAO.selectByNumber(numberRoom).get());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,5})
    void selectById(int id) throws SQLException {
        Assertions.assertEquals(id,
                roomDAO.selectById(id).get().getId());
    }

    @org.junit.jupiter.api.Test
    void selectAll() throws SQLException {
        System.out.println(roomDAO.selectAll());
        Assertions.assertFalse(roomDAO.selectAll().get().isEmpty());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,5})
    void deleteById(int id) throws SQLException {
        Assertions.assertTrue(roomDAO.deleteById(id));
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {-1,-2,-3,999})
    void deleteByIdNegative(int id) throws SQLException {
        Assertions.assertFalse(roomDAO.deleteById(id));
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"311","555","111"})
    void deleteByNumber(String roomNumber) throws SQLException {
        Assertions.assertTrue(roomDAO.deleteByNumber(roomNumber));
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"zxc12","df3","AS32"})
    void deleteByNumberNegative(String roomNumber) throws SQLException {
        Assertions.assertFalse(roomDAO.deleteByNumber(roomNumber));
    }

}