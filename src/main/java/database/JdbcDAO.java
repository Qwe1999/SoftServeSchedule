package database;

import model.Group;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface JdbcDAO <T> {

    List<T> parseResultSet(ResultSet rs) throws SQLException;

    int insert(T object) throws SQLException;

    T selectById(int id) throws SQLException;

    List<T> selectAll() throws SQLException;

    void dropTable() throws SQLException;

    void deleteById(int id) throws SQLException;
}
