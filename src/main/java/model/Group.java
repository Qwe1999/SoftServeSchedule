package model;

import exceptions.GroupException;

import java.util.Objects;

public class Group {
    public String number;

    public Group() {
    }

    public Group(String numberGroup) {
        this.number = numberGroup;
    }



    public String getNumberGroup() {
        return number;
    }

    public void setNumberGroup(String number) throws GroupException {
        this.number = number;
        if(number.isEmpty()) {
            throw new GroupException("number is empty");
        }
        else {
            this.number = number;
        }
    }

    @Override
    public String toString() {
        return "model.Group{" +
                "numberGroup=" + number +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return Objects.equals(number, group.number);
    }


}
