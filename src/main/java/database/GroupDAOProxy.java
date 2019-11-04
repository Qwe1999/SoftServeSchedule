package database;

import exceptions.GroupException;
import model.Group;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GroupDAOProxy implements JdbcDAO<Group>{

    static final String PATH = "...";
    GroupDAO groupDAO;


    public GroupDAOProxy() {
        groupDAO = new GroupDAO();
    }

    public Connection getConnection() {
        return groupDAO.getConnection();
    }


    public GroupDAO setConnection(Connection connection) {
        this.groupDAO.setConnection(connection);
        return groupDAO;
    }

    @Override
    public List<Group> parseResultSet(ResultSet rs) throws SQLException {
        System.out.println("In GroupDAOProxy.parseResultSet()");
        if (rs == null){
            System.out.println("Parameter rs is null");
            return null;
        }
        else {
            System.out.println("Start GroupDAO.parseResultSet()");
            List<Group> groups = groupDAO.parseResultSet(rs);
            System.out.println("GroupDAO.parseResultSet() is done");
            System.out.println("Exit from GroupDAOProxy.parseResultSet() ");
            return groups;
        }
    }

    @Override
    public int insert(Group group) throws SQLException {
        System.out.println("In GroupDAOProxy.insert()");
        if (group == null){
            System.out.println("Parameter group is null");
            return -1;
        }
        else {
            System.out.println("Start GroupDAO.insert");
            int id = groupDAO.insert(group);
            System.out.println("GroupDAO.insert() is done");
            System.out.println("Exit from GroupDAOProxy.insert");
            return id;
        }
    }

    @Override
    public Group selectById(int id) throws SQLException {
        System.out.println("In GroupDAOProxy.insert()");
        if (id <= 0){
            System.out.println("Parameter id <= 0");
            return null;
        }
        else {
            System.out.println("Start GroupDAO.selectById");
            Group group = groupDAO.selectById(id);
            System.out.println("GroupDAO.selectById() is done");
            System.out.println("Exit from GroupDAOProxy.selectById()");
            return group;
        }
    }

    @Override
    public List<Group> selectAll() throws SQLException {

        List<Group> groups = groupDAO.selectAll();
        return groups;
    }

    @Override
    public void dropTable() throws SQLException {

        groupDAO.dropTable();
    }

    @Override
    public void deleteById(int id) throws SQLException {

        groupDAO.deleteById(id);
    }

    public Group selectByNumber(String  number) throws SQLException, GroupException {

        Group group = groupDAO.selectByNumber(number);
        return group;
    }


}
