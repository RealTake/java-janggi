package model.player;

import model.piece.Piece;
import model.piece.PieceProfile;
import model.piece.PieceType;
import model.piece.Pieces;
import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlayersTest {

    private Player greenPlayer;
    private Player redPlayer;
    private Players players;

    @BeforeEach
    void initialize() {
        greenPlayer = new Player(Pieces.initializeGreenTeamPieces(), Team.GREEN);
        redPlayer = new Player(Pieces.initializeRedTeamPieces(), Team.RED);
        players = new Players(List.of(greenPlayer, redPlayer));
    }
    
    @Test
    void 초나라_플레이어의_기물을_움직인다() {
        // Given & When
        Position startPosition = new Position(8, 4);
        Position destination = new Position(7, 4);
        players.movePiece(startPosition, destination, Team.GREEN);

        // Then
        assertThat(players.getPlayerByTeam(Team.GREEN).findPieceAt(new Position(7, 4)))
                .isEqualTo(new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), destination));
    }

    @Test
    void 해당_기물이_움직일_수_없는_곳으로_움직이려고_할_경우_예외를_발생시킨다() {
        // Given
        Position startPosition = new Position(8, 4);
        Position destination = new Position(7, 0);

        // When & Then
        assertThatThrownBy(() -> players.movePiece(startPosition, destination, Team.GREEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    void 이동_경로에_다른_기물이_있다면_이동할_수_없고_예외를_발생시킨다() {
        // Given
        Position startPositionOfChariot = new Position(9, 0);
        Position destination = new Position(5, 0);

        // When & Then
        assertThatThrownBy(() -> players.movePiece(startPositionOfChariot, destination, Team.GREEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 목적지에_같은_팀_기물이_있다면_움직일_수_없다() {
        // Given
        Position startPositionOfChariot = new Position(9, 0);
        Position destination = new Position(9, 1);

        // When & Then
        assertThatThrownBy(() -> players.movePiece(startPositionOfChariot, destination, Team.GREEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 포는_무조건_기물을_하나_뛰어_넘어야_한다() {
        // Given
        players.getPlayerByTeam(Team.GREEN).findPieceAt(new Position(6, 0)).changePosition(new Position(6, 1));
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(5, 1);

        // When
        players.movePiece(startPositionOfCannon, destination, Team.GREEN);

        // Then
        assertThat(players.getPlayerByTeam(Team.GREEN).findPieceAt(new Position(5, 1)))
                .isEqualTo(new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), destination));
    }

    @Test
    void 포의_이동_경로에_기물이_없다면_움직일_수_없다() {
        // Given
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(5, 1);

        // When & Then
        assertThatThrownBy(() -> players.movePiece(startPositionOfCannon, destination, Team.GREEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 포는_두_개_이상의_기물은_뛰어넘을_수_없다() {
        // Given
        players.getPlayerByTeam(Team.GREEN).findPieceAt(new Position(6, 0)).changePosition(new Position(6, 1));
        players.getPlayerByTeam(Team.GREEN).findPieceAt(new Position(6, 2)).changePosition(new Position(5, 1));
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(4, 1);

        // When & Then
        assertThatThrownBy(() -> players.movePiece(startPositionOfCannon, destination, Team.GREEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 포는_상대팀_포를_죽일_수_없다() {
        // Given
        players.getPlayerByTeam(Team.GREEN).findPieceAt(new Position(6, 0)).changePosition(new Position(6, 1));
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(2, 1);

        // When & Then
        assertThatThrownBy(() -> players.movePiece(startPositionOfCannon, destination, Team.GREEN))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 주어진_팀에_해당하는_플레이어를_찾는다() {
        // Given
        Player greenPlayer = new Player(Pieces.initializeGreenTeamPieces(), Team.GREEN);
        Player redPlayer = new Player(Pieces.initializeRedTeamPieces(), Team.RED);
        Players players = new Players(List.of(greenPlayer, redPlayer));

        // When & Then
        assertThat(players.getPlayerByTeam(Team.GREEN)).isEqualTo(greenPlayer);
        assertThat(players.getPlayerByTeam(Team.RED)).isEqualTo(redPlayer);
    }

    @Test
    void 주어진_팀에_해당하는_플레이어의_점수를_계산한다() {
        // Given
        Player greenPlayer = new Player(Pieces.initializeGreenTeamPieces(), Team.GREEN);
        Player redPlayer = new Player(Pieces.initializeRedTeamPieces(), Team.RED);
        redPlayer.removePieceAt(new Position(0, 0));
        Players players = new Players(List.of(greenPlayer, redPlayer));

        // When & Then
        assertThat(players.calculateTeamPoints(Team.GREEN)).isEqualTo(172);
        assertThat(players.calculateTeamPoints(Team.RED)).isEqualTo(159);
    }

    @Test
    void 살아있는_플레이어의_수를_구한다() {
        // Given
        Player greenPlayer = new Player(Pieces.initializeGreenTeamPieces(), Team.GREEN);
        Player redPlayer = new Player(Pieces.initializeRedTeamPieces(), Team.RED);
        Players players = new Players(List.of(greenPlayer, redPlayer));

        // When & Then
        assertThat(players.countAlivePlayers())
                .isEqualTo(2);
    }

    @Test
    void 살아있는_플레이어를_한_명_가져온다() {
        // Given
        Player greenPlayer = new Player(Pieces.initializeGreenTeamPieces(), Team.GREEN);
        Player redPlayer = new Player(Pieces.initializeRedTeamPieces(), Team.RED);
        redPlayer.makeDead();
        Players players = new Players(List.of(greenPlayer, redPlayer));

        // When & Then
        assertThat(players.getFirstAlivePlayers())
                .isEqualTo(greenPlayer);
    }
}
