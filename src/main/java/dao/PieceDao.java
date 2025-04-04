package dao;

import model.piece.Piece;
import model.piece.PieceProfile;
import model.piece.PieceType;
import model.piece.Pieces;
import model.piece.position.Position;
import model.player.Team;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PieceDao {

    private final Connection connection;

    public PieceDao(final Connection connection) {
        this.connection = connection;
    }

    public void addPiece(final Piece piece, final Team team) {
        final String query = "INSERT INTO piece(type, team, rowPosition, columnPosition) VALUES(?, ?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, piece.getPieceType().name());
            preparedStatement.setString(2, team.name());
            preparedStatement.setInt(3, piece.getPosition().getRow());
            preparedStatement.setInt(4, piece.getPosition().getColumn());
            preparedStatement.executeUpdate();
        } catch (final SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public void addPieces(final Pieces pieces, final Team team) {
        final String query = "INSERT INTO piece(type, team, rowPosition, columnPosition) VALUES (?, ?, ?, ?)";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Piece piece : pieces.getPieces()) {
                preparedStatement.setString(1, piece.getPieceType().name());
                preparedStatement.setString(2, team.name());
                preparedStatement.setInt(3, piece.getPosition().getRow());
                preparedStatement.setInt(4, piece.getPosition().getColumn());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (final SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public void deletePieceByPosition(final Position position) {
        final String query = "DELETE FROM piece WHERE rowPosition = ? AND columnPosition = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, position.getRow());
            preparedStatement.setInt(2, position.getColumn());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public void deleteAllPieces() {
        final String query = "DELETE FROM piece";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    public List<Piece> findPieceByTeam(final Team team) {
        final String query = "SELECT * FROM piece WHERE team = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, team.name());
            final ResultSet resultSet = preparedStatement.executeQuery();
            return generateTeamPieces(resultSet);
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }

    private List<Piece> generateTeamPieces(ResultSet resultSet) throws SQLException {
        List<Piece> teamPieces = new ArrayList<>();
        while (resultSet.next()) {
            PieceType pieceType = PieceType.valueOf(resultSet.getString("type"));
            Piece piece = new Piece(PieceProfile.generateFromPieceType(pieceType), new Position(resultSet.getInt("rowPosition"), resultSet.getInt("columnPosition")));
            teamPieces.add(piece);
        }
        return teamPieces;
    }

    public void updatePiecePosition(final Position originalPosition, final Position newPosition) {
        final String query = "UPDATE piece SET rowPosition = ?, columnPosition = ? WHERE rowPosition = ? AND columnPosition = ?";
        try (final PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, newPosition.getRow());
            preparedStatement.setInt(2, newPosition.getColumn());
            preparedStatement.setInt(3, originalPosition.getRow());
            preparedStatement.setInt(4, originalPosition.getColumn());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException("DB 연결 도중 오류가 발생하였습니다. 해당 게임이 갑작스럽게 종료될 경우, 추후 이어서 진행할 수 없습니다.");
        }
    }
}
