package database;

import model.Group;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.postgresql.util.PSQLException;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.NoSuchElementException;
import java.util.stream.Stream;


class GroupDAOTest {

    static GroupDAO groupDAO ;
    static Connection connection;


    @BeforeAll
   static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/schedule222Test?user=postgres&password=root");
        connection.setAutoCommit(false);
        groupDAO = new GroupDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insert(Group group) throws SQLException {
        Assertions.assertDoesNotThrow(() -> groupDAO.insert(group));
    }

    static Stream<Group> insert() {
        return Stream.of(
                new Group("333"),
                new Group("2123"),
                new Group("353-A")
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insertNegative(Group group) throws SQLException {
        Assertions.assertThrows(PSQLException.class,() -> groupDAO.insert(group));
    }

    static Stream<Group> insertNegative() {
        return Stream.of(
                new Group("321"),
                new Group("544"),
                new Group("123")
        );
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = { "123,544,321"})
    void selectByNumber(String numberGroup) throws SQLException {
        Assertions.assertEquals(numberGroup,
                groupDAO.selectByNumber(numberGroup).get().getNumber());
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = { "DDDDAS","zxc","213as"})
    void selectByNumberNegative(String numberGroup) throws SQLException {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> groupDAO.selectByNumber(numberGroup).get());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {10})
    void selectById(int id) throws SQLException {
        Assertions.assertEquals(id,
                groupDAO.selectById(id).get().getId());
    }

    @org.junit.jupiter.api.Test
    void selectAll() throws SQLException {
        System.out.println(groupDAO.selectAll());
        Assertions.assertFalse(groupDAO.selectAll().get().isEmpty());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {10,18,19})
    void deleteById(int id) throws SQLException {
        Assertions.assertTrue(groupDAO.deleteById(id));
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {-1,-2,-3,999})
    void deleteByIdNegative(int id) throws SQLException {
        Assertions.assertFalse(groupDAO.deleteById(id));
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"321","544","123"})
    void deleteByNumber(String groupNumber) throws SQLException {
        Assertions.assertTrue(groupDAO.deleteByNumber(groupNumber));
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"zxc12","df3","AS32"})
    void deleteByNumberNegative(String groupNumber) throws SQLException {
        Assertions.assertFalse(groupDAO.deleteByNumber(groupNumber));
    }

}