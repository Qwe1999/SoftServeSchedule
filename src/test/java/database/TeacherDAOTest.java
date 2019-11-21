package database;

import model.Teacher;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.postgresql.util.PSQLException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOTest {



    static TeacherDAO teacherDAO;
    static Connection connection;

    @BeforeAll
    static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/schedule222Test?user=postgres&password=root");
        connection.setAutoCommit(false);
        teacherDAO = new TeacherDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insert(Teacher teacher) throws SQLException {
        Assertions.assertDoesNotThrow(() -> teacherDAO.insert(teacher));
    }

    static Stream<Teacher> insert() {
        return Stream.of(
                new Teacher("ABC","DCB"),
                new Teacher("QWE","ZXC"),
                new Teacher("FFF","DDD")
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insertNegative(Teacher teacher) throws SQLException {
        Assertions.assertThrows(PSQLException.class,() -> teacherDAO.insert(teacher));
    }

    static Stream<Teacher> insertNegative() {
        return Stream.of(
                new Teacher("Ivan","Kovlov"),
                new Teacher("Dima","Tartor"),
                new Teacher("Jack","Ruby")
        );
    }

    @Test
    @ParameterizedTest
    @CsvSource({
            "Ivan, Kovlov",
            "Dima, Tartar",
            "Jack, Ruby"
    })
    void selectByName(String firstName,String lastName) throws SQLException {
        Assertions.assertEquals(firstName,
                teacherDAO.selectByName(firstName,lastName).get().getFirstName());
        Assertions.assertEquals(lastName,
                teacherDAO.selectByName(firstName,lastName).get().getLastName());
    }

    @Test
    @ParameterizedTest
    @CsvSource({
            "AAA, BBB",
            "23A, 999",
            "Jack, "
    })
    void selectByNameNegative(String firstName,String lastName) throws SQLException {
        Assertions.assertThrows(NoSuchElementException.class,
                () -> teacherDAO.selectByName(firstName,lastName).get());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void selectById(int id) throws SQLException {
        Assertions.assertEquals(id,
                teacherDAO.selectById(id).get().getId());
    }

    @org.junit.jupiter.api.Test
    void selectAll() throws SQLException {
        System.out.println(teacherDAO.selectAll());
        Assertions.assertFalse(teacherDAO.selectAll().get().isEmpty());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void deleteById(int id) throws SQLException {
        Assertions.assertTrue(teacherDAO.deleteById(id));
    }


    @Test
    @ParameterizedTest
    @ValueSource(ints = {-1,-2,-3,999})
    void deleteByIdNegative(int id) throws SQLException {
        Assertions.assertFalse(teacherDAO.deleteById(id));
    }

    @Test
    @ParameterizedTest
    @CsvSource({
            "Ivan, Kovlov",
            "Dima, Tartor",
            "Jack, Ruby"
    })
    void deleteByName(String firstName,String lastName) throws SQLException {
        Assertions.assertTrue(teacherDAO.deleteByName(firstName,lastName));
    }

    @Test
    @ParameterizedTest
    @CsvSource({
            "AAA, BBB",
            "23A, 999",
            "Jack, "
    })
    void deleteByNameNegative(String firstName,String lastName) throws SQLException {
        Assertions.assertFalse(teacherDAO.deleteByName(firstName,lastName));
    }

}