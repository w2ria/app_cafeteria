/*package com.cafeteria.app_utp_cafeteria.data.local.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Properties;

public class DatabaseConnection {

    private static HikariDataSource dataSource;

    // Método para obtener una conexión a la base de datos
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            initializeDataSource();
        }
        return dataSource.getConnection();
    }

    // Inicializa el HikariDataSource
    private static void initializeDataSource() {
        HikariConfig config = new HikariConfig();

        // Cargar propiedades desde el archivo
        try (InputStream input = Objects.requireNonNull(DatabaseConnection.class.getClassLoader()).getResourceAsStream("../../../config/database.properties")) {
            if (input == null) {
                throw new IOException("No se pudo encontrar el archivo de propiedades");
            }
            Properties properties = new Properties();
            properties.load(input);

            // Configurar HikariCP
            config.setJdbcUrl(properties.getProperty("db.url"));
            config.setUsername(properties.getProperty("db.username"));
            config.setPassword(properties.getProperty("db.password"));
        } catch (IOException e) {
            // Manejo de excepciones
            throw new RuntimeException("Error al cargar las propiedades de la base de datos", e);
        }

        dataSource = new HikariDataSource(config);
    }

    // Método para cerrar la conexión a la base de datos
    public static void closeConnection() {
        if (dataSource != null) {
            dataSource.close();
        }
    }
}
*/