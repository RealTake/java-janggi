package dao;

import model.player.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GameDao {

    private final Connection connection;

    public GameDao(final Connection connection) {
        this.connection = connection;
    }

    public void addGame(final Team currentTurnTeam) {
        final String query = "INSERT INTO game(turn) VALUES(?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, currentTurnTeam.name());
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public void updateGameTurn(final Team newTurnTeam) {
        final String query = "UPDATE game SET turn = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, newTurnTeam.name());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public void deleteGame() {
        final String query = "DELETE FROM game";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public Team findPreviousGameTurn() {
        final String query = "SELECT turn FROM game";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            final ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Team.valueOf(resultSet.getString("turn"));
            }
            throw new SQLException();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public boolean isExist() {
        final String query = "SELECT COUNT(*) FROM game";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            return false;
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }
}
