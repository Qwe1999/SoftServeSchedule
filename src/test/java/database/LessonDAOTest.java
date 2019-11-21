package database;

import model.*;
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
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LessonDAOTest {

    static LessonDAO lessonDAO;
    static Connection connection;

    @BeforeAll
    static void createConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/scheduleTest?user=postgres&password=root");
        connection.setAutoCommit(false);
        lessonDAO = new LessonDAO(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        connection.rollback();
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insert(Lesson lesson) throws SQLException {
        Assertions.assertDoesNotThrow(() -> lessonDAO.insert(lesson));
    }

    static Stream<Lesson> insert() {
        return Stream.of(
                new Lesson().setSubject(new Subject().setId(3))
                        .setTeacher(new Teacher().setId(1))
                        .setNumberLesson(NumberLesson.eight)
                        .setDayLesson(Day.FRIDAY)
                        .setGroup(new Group().setId(10))
                        .setRoom(new Room().setId(1)),

                new Lesson().setSubject(new Subject().setId(6))
                        .setTeacher(new Teacher().setId(2))
                        .setNumberLesson(NumberLesson.five)
                        .setDayLesson(Day.WEDNESDAY)
                        .setGroup(new Group().setId(18))
                        .setRoom(new Room().setId(2))

        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void insertNegative(Lesson lesson) throws SQLException {
        Assertions.assertThrows(Exception.class,() -> lessonDAO.insert(lesson));
    }

    static Stream<Lesson> insertNegative() {
        return Stream.of(
                new Lesson().setSubject(new Subject().setId(3))
                        .setTeacher(new Teacher().setId(1))
                        .setNumberLesson(NumberLesson.two)
                        .setDayLesson(Day.MONDAY)
                        .setGroup(new Group().setId(10))
                        .setRoom(new Room().setId(1)),

                new Lesson().setSubject(new Subject().setId(3))
                        .setTeacher(new Teacher().setId(2))
                        .setNumberLesson(NumberLesson.two)
                        .setDayLesson(Day.TUESDAY)
                        .setGroup(new Group().setId(10))
                        .setRoom(new Room().setId(1)),

                new Lesson().setSubject(new Subject().setId(-1))
                        .setTeacher(new Teacher().setId(-1))
                        .setNumberLesson(NumberLesson.two)
                        .setDayLesson(Day.MONDAY)
                        .setGroup(new Group().setId(10))
                        .setRoom(new Room().setId(1))
        );
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"311","555","111"})
    public void selectByNumberRoom(String number) throws SQLException {
        List<Lesson> lessons =lessonDAO.selectByNumberRoom(number).get();
        for (Lesson lesson : lessons){
            assertEquals(number,lesson.getRoom().getNumber());
        }
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"24rfd","00-1","wqe"})
    public void selectByNumberRoomNegative(String number) throws SQLException {
        assertFalse(lessonDAO.selectByNumberRoom(number).isPresent());
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"math","physics","programming"})
    public void selectByNameSubject(String name) throws SQLException {
        List<Lesson> lessons = lessonDAO.selectByNameSubject(name).get();
        for (Lesson lesson : lessons) {
            assertEquals(name, lesson.getSubject().getName());
        }
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"ZZZ32","CCC","AAA"})
    public void selectByNameNegative(String name) throws SQLException {
        assertFalse(lessonDAO.selectByNameSubject(name).isPresent());
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"321","544","123"})
    public void selectByNumberGroup(String number) throws SQLException {
        List<Lesson> lessons = lessonDAO.selectByNumberGroup(number).get();
        for (Lesson lesson : lessons) {
            assertEquals(number, lesson.getGroup().getNumber());
        }
    }

    @Test
    @ParameterizedTest
    @ValueSource(strings = {"sad","9900","sad-a"})
    public void selectByNumberNegative(String number) throws SQLException {
        assertFalse(lessonDAO.selectByNumberGroup(number).isPresent());
    }

    @Test
    @ParameterizedTest
    @CsvSource({
            "Ivan, Kovlov",
            "Dima, Tartor",
            "Jack, Ruby"
    })
    public void selectByTeacherName(String firstName,String lastName) throws SQLException {
        List<Lesson> lessons = lessonDAO.selectByTeacherName(firstName,lastName).get();
        for (Lesson lesson : lessons) {
            assertEquals(firstName, lesson.getTeacher().getFirstName());
            assertEquals(lastName, lesson.getTeacher().getLastName());
        }
    }

    @Test
    @ParameterizedTest
    @CsvSource({
            "ew12, ASD",
            "zxc, Tartor",
            " , "
    })
    public void selectByTeacherNameNegative(String firstName,String lastName) throws SQLException {
        assertFalse(lessonDAO.selectByTeacherName(firstName,lastName).isPresent());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,4,7})
    void selectById(int id) throws SQLException {
        Assertions.assertEquals(id,
                lessonDAO.selectById(id).get().getId());
    }



    @org.junit.jupiter.api.Test
    void selectAll() throws SQLException {
        Assertions.assertFalse(lessonDAO.selectAll().get().isEmpty());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {1,2,4,7})
    void deleteById(int id) throws SQLException {
        Assertions.assertTrue(lessonDAO.deleteById(id));
    }


    @Test
    @ParameterizedTest
    @ValueSource(ints = {-1,-2,-3,999})
    void deleteByIdNegative(int id) throws SQLException {
        Assertions.assertFalse(lessonDAO.deleteById(id));
    }



}