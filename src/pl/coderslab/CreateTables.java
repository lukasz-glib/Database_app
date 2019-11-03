package pl.coderslab;

import pl.coderslab.utils.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    private static final String QUERY1 = "CREATE TABLE user_group (\n" +
            "    id int (11) AUTO_INCREMENT PRIMARY KEY,\n" +
            "    name varchar(255)\n" +
            ");";

    private static final String QUERY2 = "CREATE TABLE users (\n" +
            "    id int (11) AUTO_INCREMENT PRIMARY KEY,\n" +
            "    username varchar(255),\n" +
            "    email varchar(255) UNIQUE,\n" +
            "    password varchar(60),\n" +
            "    user_group_id int(11) NOT NULL,\n" +
            "    FOREIGN KEY (user_group_id) REFERENCES user_group(id)\n" +
            ");";

    private static final String QUERY3 = "CREATE TABLE exercise (\n" +
            "    id int (11) AUTO_INCREMENT PRIMARY KEY,\n" +
            "    title varchar(255),\n" +
            "    description text\n" +
            ");";

    private static final String QUERY4 = "CREATE TABLE solution (\n" +
            "        id int (11) AUTO_INCREMENT PRIMARY KEY,\n" +
            "        created DATETIME,\n" +
            "        updated DATETIME,\n" +
            "        description TEXT,\n" +
            "        exercise_id int(11) NOT NULL,\n" +
            "        users_id int(11) NOT NULL,\n" +
            "        FOREIGN KEY (exercise_id) REFERENCES exercise(id),\n" +
            "        FOREIGN KEY (users_id) REFERENCES users(id)\n" +
            "    );";


    public static void main(String[] args) {

        try(Connection conn = DBUtil.getConnection()) {
            final Statement statement = conn.createStatement();



            statement.executeUpdate(QUERY1);
            statement.executeUpdate(QUERY2);
            statement.executeUpdate(QUERY3);
            statement.executeUpdate(QUERY4);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
