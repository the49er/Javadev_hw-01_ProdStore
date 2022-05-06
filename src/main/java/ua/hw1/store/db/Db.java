package ua.hw1.store.db;
import com.mysql.cj.jdbc.NonRegisteringDriver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Data
@EqualsAndHashCode
public class Db {
    private static final String URL = "jdbc:mysql://localhost:3306/productshop";
    private static final String USERNAME = "test_user";
    private static final String PASSWORD = "password";
    private Connection connection;

    public Db () {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        } catch (SQLException e) {
            log.error("can not set up the connection");;
        }
    }
}
