package dao;

import util.ConfigLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    private static final String SERVER = ConfigLoader.getProperty("database.server");
    private static final String DATABASE = ConfigLoader.getProperty("database.database");
    private static final String OPTION = ConfigLoader.getProperty("database.option");
    private static final String USERNAME = ConfigLoader.getProperty("database.username");
    private static final String PASSWORD = ConfigLoader.getProperty("database.password");

    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://" + SERVER + "/" + DATABASE + OPTION, USERNAME, PASSWORD);
        } catch (final SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }
}
