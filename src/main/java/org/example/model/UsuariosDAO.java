package org.example.model;

import org.example.view.Application;

import java.sql.*;

public class UsuariosDAO {

    private static Connection conn = DatabaseConnection.getConnection();

    public static void createUser(int DNI, String name, String surname, String date) {
        String query = "CALL insertar_usuario(?, ?, ?, ?)";

        executeQuery(DNI, name, surname, date, query);
    }

    public static void readUser() {
        String query = "SELECT * FROM Usuarios";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            Application.model.clear();

            while (rs.next()) {
                Application.model.addElement(
                        rs.getInt("dni")
                                + " " + rs.getString("nombre")
                                + " " + rs.getString("apellido")
                                + " " + rs.getDate("fecha"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readUser(int DNI) {
        String query = "CALL obtener_usuario(?)";

        executeQuery(DNI, query);
    }

    public static void updateUser(int DNI, String name, String surname, String date) {
        String query = "CALL actualizar_usuario(?, ?, ?, ?)";

        executeQuery(DNI, name, surname, date, query);
    }

    public static void deleteUser(int DNI) {
        String query = "CALL borrar_usuario(?)";

        executeQuery(DNI, query);
    }

    private static void executeQuery(int DNI, String query) {
        try {
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, DNI);

            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeQuery(int DNI, String name, String surname, String date, String query) {
        try {
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setInt(1, DNI);
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setDate(4, Date.valueOf(date));

            statement.execute();

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
