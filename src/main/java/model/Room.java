package model;

import exceptions.RoomException;

public class Room {
    private int number;

    public Room(int number) {
        this.number = number;
    }

    public Room() {
    }

    @Override
    public String toString() {
        return "model.Room{" +
                "numberRoom=" + number +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return number == room.number;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) throws RoomException{
        if(number >=0) {
            this.number = number;
        }
        else {
            throw new RoomException("number  < 0 ");
        }
    }
}
