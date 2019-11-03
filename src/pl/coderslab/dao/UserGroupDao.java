package pl.coderslab.dao;

import pl.coderslab.entity.UserGroup;
import pl.coderslab.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

//import static sun.plugin.javascript.navig.JSType.URL;

public class UserGroupDao {

    private static final String CREATE_USER_GROUP_QUERY =
            "INSERT INTO user_group(name) VALUES (?)";
    private static final String READ_USER_GROUP_QUERY =
            "SELECT * FROM user_group WHERE id = ?";  //pobiera z metod niżej napisanych!
    private static final String UPDATE_USER_GROUP_QUERY =
            "UPDATE user_group SET name = ? WHERE id = ?";
    private static final String DELETE_USER_GROUP_QUERY =
            "DELETE FROM user_group WHERE id = ?";
    private static final String FIND_ALL_USER_GROUP_QUERY =
            "SELECT * FROM user_group";


    public UserGroup create(UserGroup userGroup) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement =
                    conn.prepareStatement(CREATE_USER_GROUP_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userGroup.getName());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                userGroup.setId(resultSet.getInt(1));
            }
            return userGroup;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    public UserGroup read(int userGroupId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(READ_USER_GROUP_QUERY);
            statement.setInt(1, userGroupId); //to wchodzi w znak zapytania wyżej - do READ_USER_QUERY, podobnie jak pozostałe metody
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt("id"));
                userGroup.setName(resultSet.getString("name"));
                return userGroup;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(UserGroup userGroup) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(UPDATE_USER_GROUP_QUERY);
            statement.setString(1, userGroup.getName());
            statement.setInt(2, userGroup.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int userGroupId) {
        try (Connection conn = DBUtil.getConnection()) {
            PreparedStatement statement = conn.prepareStatement(DELETE_USER_GROUP_QUERY);
            statement.setInt(1, userGroupId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserGroup[] findAll() {
        try (Connection conn = DBUtil.getConnection()) {
            UserGroup[] userGroups = new UserGroup[0];
            PreparedStatement statement = conn.prepareStatement(FIND_ALL_USER_GROUP_QUERY);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                UserGroup userGroup = new UserGroup();
                userGroup.setId(resultSet.getInt("id"));
                userGroup.setName(resultSet.getString("name"));
                userGroups = addToArray(userGroup, userGroups);
            }
            return userGroups;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private UserGroup[] addToArray(UserGroup u, UserGroup[] userGroups) {
        UserGroup[] tmpUsersGroups = Arrays.copyOf(userGroups, userGroups.length + 1);
        tmpUsersGroups[userGroups.length] = u;
        return tmpUsersGroups;
    }

}
