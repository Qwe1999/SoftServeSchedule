package model;

import java.util.Objects;

public class Subject {
    private String name;

    public Subject(String name) {
        this.name = name;
    }

    public Subject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        if(name != null & !name.isEmpty()) {
            this.name = name;
        }
        else {
            throw new SecurityException("name is null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name);
    }



    @Override
    public String toString() {
        return "model.Subject{" +
                "nameSubject='" + name + '\'' +
                '}';
    }
}
