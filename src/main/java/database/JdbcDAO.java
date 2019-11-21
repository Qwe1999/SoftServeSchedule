package database;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface JdbcDAO <T> {


    int insert(T object) throws SQLException;

    Optional<T> selectById(int id) throws SQLException;

    Optional<List<T>> selectAll() throws SQLException;

    boolean deleteById(int id) throws SQLException;
}
