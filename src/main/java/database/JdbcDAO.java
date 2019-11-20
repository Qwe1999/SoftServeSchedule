package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JdbcDAO <T> {


    int insert(T object) throws SQLException;

    Optional<T> selectById(int id) throws SQLException;

    Optional<List<T>> selectAll() throws SQLException;

    void deleteById(int id) throws SQLException;
}
