package dao;

import model.player.Team;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class GameDaoTest {

    private static Connection connection;
    private GameDao gameDao;

    @BeforeAll
    static void setUp() {
        DatabaseConnectionManager databaseConnectionManager = new DatabaseConnectionManager();
        connection = databaseConnectionManager.getConnection();
    }

    @BeforeEach
    void initialize() {
        gameDao = new GameDao(connection);
        gameDao.deleteGame();
    }

    @AfterEach
    void deleteGame() {
        gameDao.deleteGame();
    }

    @AfterAll
    static void close() throws SQLException {
        connection.close();
    }

    @Test
    void 게임을_DB에_저장한다() {
        // Given
        Team currentTurnTeam = Team.RED;

        // When
        gameDao.addGame(currentTurnTeam);

        // Then
        assertThat(gameDao.findPreviousGameTurn()).isEqualTo(Team.RED);
    }

    @Test
    void 턴을_업데이트한다() {
        // Given
        Team currentTurnTeam = Team.GREEN;
        gameDao.addGame(currentTurnTeam);
        Team nextTurnTeam = Team.RED;

        // When
        gameDao.updateGameTurn(nextTurnTeam);

        // Then
        assertThat(gameDao.findPreviousGameTurn()).isEqualTo(Team.RED);
    }

    @Test
    void 이전에_진행된_게임이_없다면_false를_반환한다() {
        // Given
        // When
        // Then
        assertThat(gameDao.isExist()).isFalse();
    }

    @Test
    void 이전에_진행된_게임이_있다면_true를_반환한다() {
        // Given
        // When
        gameDao.addGame(Team.GREEN);

        // Then
        assertThat(gameDao.isExist()).isTrue();
    }
}
