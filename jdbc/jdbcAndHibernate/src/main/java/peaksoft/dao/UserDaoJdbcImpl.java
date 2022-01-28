package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbcImpl implements UserDao {


    public UserDaoJdbcImpl() {

    }

    //if not exists
    public void createUsersTable() {
        try (Connection conn = Util.connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("CREATE TABLE IF NOT EXISTS users(id serial primary key ,name varchar ,lastName VARCHAR ,age int)")) {
            while (rs.next()) {
                System.out.println(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Ошибка уже есть таблица users!");
        }

    }

    public void dropUsersTable() {
        try (Connection conn = Util.connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("DROP TABLE if exists users")) {
            System.out.println(rs);

        } catch (SQLException ex) {
            System.out.println("Вы успешно удалили таблицу users!" + ex.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.connection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO users(name,lastName,age) VALUES(?,?,?)")) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Вы успешно добавили информацию в таблицу users!" + ex.getMessage());
        }
    }

    public void removeUserById(long id) {
        try {
            Connection conn = Util.connection();
            PreparedStatement statement = conn.prepareStatement("DELETE  FROM users WHERE id=(?)");
            if (id > 0) {
                statement.setLong(1, id);
                statement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Вы успешно удалили 'id' таблицу users!");
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection conn = Util.connection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users")) {
            System.out.println("\nThis is a Users Table:");
            System.out.println("########################################");
            System.out.println("id\t| name\t\t| lastName\t\t| age |");
            System.out.println("----+-----------+---------------+-----+");
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setAge(rs.getByte(4));
                list.add(user);

            }
            System.out.println("########################################");
        } catch (SQLException ex) {
            System.out.println("Вы успешно вызывали таблицу users!");
        }
        return list;
    }


    public void cleanUsersTable() {
        try {
            Connection conn = Util.connection();
            PreparedStatement statement = conn.prepareStatement("DELETE  FROM users");
            statement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Вы успешно очистили таблицу users!");
        }

    }


}