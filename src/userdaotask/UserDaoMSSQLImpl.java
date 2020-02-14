package userdaotask;

import java.sql.*;

public class UserDaoMSSQLImpl implements UserDao {
    static final String CONNECTION_URL = "jdbc:sqlserver://localhost:1433;integratedSecurity=true";
    static final String CREATE_DATABASE =
            "IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = N'UsersDB')\n" +
                    "BEGIN\n" +
                    "CREATE DATABASE [UsersDB]\n" +
                    "END;\n";
    static final String CREATE_TABLE =
//            "IF NOT EXISTS (SELECT * FROM sysobjects WHERE id = object_id(N'[dbo].[User]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)\n" +
            "IF OBJECT_ID(N'[dbo].[Users]','U') IS NULL\n" +
                    "BEGIN\n" +
                    "CREATE TABLE [Users] (\n" +
                    "[UserID] INT NOT NULL PRIMARY KEY,\n" +
                    "[Name] VARCHAR(12) NOT NULL,\n" +
                    "[Age] INT NOT NULL,\n" +
                    "[Gender] CHAR NOT NULL\n" +
                    ");\n" +
                    "END;\n";

    private static Statement stmt;
    private static Connection conn;

    public UserDaoMSSQLImpl() {
        try {
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(CONNECTION_URL);

            stmt = conn.createStatement();

            System.out.println("Creating database if not exist...");
            stmt.executeUpdate(CREATE_DATABASE);

            System.out.println("Creating table if not exist...");
            stmt.executeUpdate(CREATE_TABLE);

//            System.out.println("Inserting in table...");
//            System.out.println("insertUser(stmt, new User(1, \"Vahe\", 21, 'M')); row count for statement = "
//                    + insertUser(stmt, new User(1, "Vahe", 21, 'M')));
//
//            System.out.println("Inserting in table...");
//            System.out.println("insertUser(stmt, new User(2, \"Armen\", 25, 'M')); row count for statement = "
//                    + insertUser(stmt, new User(2, "Armen", 25, 'M')));
//
//            System.out.println("Inserting in table...");
//            System.out.println("insertUser(stmt, new User(3, \"Karen\", 25, 'M')); row count for statement = "
//                    + insertUser(stmt, new User(3, "Karen", 25, 'M')));

//            System.out.println("Updating table...");
//            System.out.println("updateUser(stmt, new User(2, \"Ashot\", 23, 'M')); row count for statement = "
//                    + updateUser(stmt, new User(2, "Ashot", 23, 'M')));
//
//            System.out.println("Getting record...");
//            User user2 = getUserById(stmt, 2);
//            System.out.println("user = " + user2);
//
//            System.out.println("Getting data...");
//            getUserFirstNameAndAge(stmt, 3);
//
//            //deleteUser()
//            {
//                System.out.println("Deleting record...");
//                int id = 2;
//                int row = deleteUser(stmt, id);
//                System.out.println(String.format("deleteUser(stmt, %d)", id));
//                System.out.println(row != 0 ? "Deleted successfully" : String.format("No user with id: %d", id));
//            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } /*finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException ignored) {
                // nothing we can do
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }*/
    }

    private static int updateUser(Statement stmt, User u) throws SQLException {
        return stmt.executeUpdate(String.format(
                "UPDATE [Users]\n" +
                        "SET [Name] = '%s', [Age] = %d, [Gender] = '%c'\n" +
                        "WHERE [UserID] = %s;"
                , u.getName()
                , u.getAge()
                , u.getGender()
                , u.getId()));
    }

    private static int deleteUser(Statement stmt, int id) throws SQLException {
        return stmt.executeUpdate("DELETE FROM Users WHERE UserID = " + id);
    }

    private static void getUserFirstNameAndAge(Statement stmt, int id) throws SQLException {
        String sql = "SELECT [Name], [Age] FROM Users WHERE UserID = " + id;
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String name = rs.getString("Name");
            int age = rs.getInt("Age");

            System.out.println("name = " + name);
            System.out.println("age = " + age);
        }
        rs.close();
    }

    private static User getUserById(Statement stmt, int id) {
        User user = null;
        try {
            String query = "SELECT * FROM Users WHERE UserID = " + id;
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int userID = rs.getInt("UserID");
                String name = rs.getString("Name");
                int age = rs.getInt("Age");
                char gender = rs.getString("Gender").charAt(0);

                user = new User(userID, name, age, gender);
            }
            rs.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        }
        if (user == null)
            throw new IllegalArgumentException("No user with id " + id);
        return user;
    }

    private static int insertUser(Statement stmt, User u) throws SQLException {
        return stmt.executeUpdate(String.format(
                "INSERT INTO [Users]([UserId], [Name], [Age], [Gender]) VALUES (%d, '%s', %d, '%c');"
                , u.getId()
                , u.getName()
                , u.getAge()
                , u.getGender()
        ));
    }

    @Override
    public User getUserById(int id) {
        return getUserById(stmt, id);
    }
}
