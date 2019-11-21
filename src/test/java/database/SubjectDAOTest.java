package database;

import model.Subject;
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

import static org.junit.jupiter.api.Assertions.*;

class SubjectDAOTest {


    static SubjectDAO subjectDAO;
    static Connection connection;

    @BeforeAll
    static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/schedule222Test?user=postgres&password=root");
        connection.setAutoCommit(false);
        subjectDAO = new SubjectDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insert(Subject subject) throws SQLException {
        Assertions.assertDoesNotThrow(() -> subjectDAO.insert(subject));
    }

    static Stream<Subject> insert() {
        return Stream.of(
                new Subject("Biology"),
                new Subject("English"),
                new Subject("German")
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insertNegative(Subject subject) throws SQLException {
        Assertions.assertThrows(PSQLException.class,() -> subjectDAO.insert(subject));
    }

    static Stream<Subject> insertNegative() {
        return Stream.of(
                new Subject("physics"),
                new Subject("programming"),
                new Subject("math")
        );
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"physics","programming","math"})
    void selectByName(String subjectName) throws SQLException {
        Assertions.assertEquals(subjectName,
                subjectDAO.selectByName(subjectName).get().getName());
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = { "DDDDAS","zxc","213as"})
    void selectByNameNegative(String subjectName) throws SQLException {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> subjectDAO.selectByName(subjectName).get());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {3,6,7})
    void selectById(int id) throws SQLException {
        Assertions.assertEquals(id,
                subjectDAO.selectById(id).get().getId());
    }

    @org.junit.jupiter.api.Test
    void selectAll() throws SQLException {
        System.out.println(subjectDAO.selectAll());
        Assertions.assertFalse(subjectDAO.selectAll().get().isEmpty());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {3,6,7})
    void deleteById(int id) throws SQLException {
        Assertions.assertTrue(subjectDAO.deleteById(id));
    }


    @Test
    @ParameterizedTest
    @ValueSource(ints = {-1,-2,-3,999})
    void deleteByIdNegative(int id) throws SQLException {
        Assertions.assertFalse(subjectDAO.deleteById(id));
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"physics","programming","math"})
    void deleteByName(String subjectName) throws SQLException {
        Assertions.assertTrue(subjectDAO.deleteByName(subjectName));
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"zxc12","df3","AS32"})
    void deleteByNameNegative(String subjectName) throws SQLException {
        Assertions.assertFalse(subjectDAO.deleteByName(subjectName));
    }

}