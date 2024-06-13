package org.example;
// import jdbc driver
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        // Set mysql login config
        String url = "jdbc:mysql://localhost:3306/mysql_test";
        String username = "root";
        String password = "123456";

        // test code
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            if (connection != null) {
                System.out.println("Connected to the database.");

                boolean CREATE = false;
                boolean ADD = false;
                boolean DEL = false;
                boolean UPDATE = false;
                boolean QUERY = true;

                if (CREATE) {
                    // create new data table
                    try (Statement statement = connection.createStatement()) {
                        String sql = "CREATE TABLE IF NOT EXISTS users" +
                                "(id INT AUTO_INCREMENT PRIMARY KEY," +
                                "name VARCHAR(255) NOT NULL," +
                                "email VARCHAR(255) UNIQUE NOT NULL )";
                        statement.execute(sql);
                        System.out.println("Table created successfully.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (ADD) {
                    // add new data
                    String sql_add = "INSERT INTO users (name, email) VALUES (?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql_add)) {
                        preparedStatement.setString(1, "Kukdo Query3");
                        preparedStatement.setString(2,"kukdo_query3@email.com");
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            // System.out.println(rowsAffected);
                            System.out.println(("A new user added successfully."));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }
                if (DEL) {
                    // delete data
                    String sql_del = "DELETE FROM users WHERE name = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql_del)) {
                        preparedStatement.setString(1, "Kukdo Delete");
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0) {
                            // System.out.println(rowsAffected);
                            System.out.println(("A new user added successfully."));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }
                }

                if (UPDATE) {
                    // update data
                    String sql_update = "UPDATE users SET email = ? WHERE name = ?";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql_update)) {
                        preparedStatement.setString(1, "kukdo_jiang@gmail.com");
                        preparedStatement.setString(2,"Kukdo Jiang");
                        int rowsAffected = preparedStatement.executeUpdate();
                        if (rowsAffected > 0){
                            System.out.println("User updated successfully.");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (QUERY) {
                    // query data test
                    String sql_query = "SELECT * FROM users";
                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(sql_query)) {
                        System.out.println("Begin querying...");
                        while (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String name = resultSet.getString("name");
                            String email = resultSet.getString("email");
                            System.out.println("ID: " + id + ", Name: " + name + ", Email: " + email);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("ERROR: Cannot connect the database!", e);
        }
    }
}
