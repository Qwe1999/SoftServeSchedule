package model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lesson {
    private int id;
    private NumberLesson numberLesson;
    private Group group;
    private Day dayLesson;
    private Room room;
    private Teacher teacher;
    private Subject subject;


    public Lesson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public Lesson setGroup(Group group) {
        this.group = group;
        return this;
    }

    public Room getRoom() {
        return room;
    }

    public Lesson setRoom(Room room) {
        this.room = room;
        return this;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Lesson setTeacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public Subject getSubject() {
        return subject;
    }

    public Lesson setSubject(Subject subject) {
        this.subject = subject;
        return this;
    }

    public NumberLesson getNumberLesson() {
        return numberLesson;
    }

    public Lesson setNumberLesson(NumberLesson numberLesson) {
            this.numberLesson =numberLesson;
            return this;
    }

    public Day getDayLesson() {
        return dayLesson;
    }

    public Lesson setDayLesson(Day dayLesson) {
            this.dayLesson = dayLesson;
            return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                numberLesson == lesson.numberLesson &&
                Objects.equals(group, lesson.group) &&
                dayLesson == lesson.dayLesson &&
                Objects.equals(room, lesson.room) &&
                Objects.equals(teacher, lesson.teacher) &&
                Objects.equals(subject, lesson.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberLesson, group, dayLesson, room, teacher, subject);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", numberLesson=" + numberLesson +
                ", group=" + group +
                ", day=" + dayLesson +
                ", room=" + room +
                ", teacher=" + teacher +
                ", subject=" + subject +
                '}';
    }
}
