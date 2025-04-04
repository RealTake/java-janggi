package model;

import model.piece.Piece;
import model.piece.PieceProfile;
import model.piece.PieceType;
import model.piece.Pieces;
import model.piece.position.Position;
import model.player.Player;
import model.player.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JanggiProcessTest {

    private Player greenPlayer;
    private Player redPlayer;
    private JanggiProcess janggiProcess;

    @BeforeEach
    void initialize() {
        greenPlayer = new Player(Pieces.initializeGreenTeamPieces(), Team.GREEN);
        redPlayer = new Player(Pieces.initializeRedTeamPieces(), Team.RED);
        janggiProcess = JanggiProcess.initializeJanggi(greenPlayer, redPlayer, Team.GREEN);
    }

    @Test
    void 두_플레이어가_모두_생존한_상태인지_확인한다() {
        // Given
        // When & Then
        assertThat(janggiProcess.isAllPlayersAlive()).isTrue();
    }

    @Test
    void 현재_턴_플레이어의_팀을_가져온다() {
        // Given
        // When & Then
        assertThat(janggiProcess.getCurrentTurnTeam()).isEqualTo(Team.GREEN);
    }

    @Test
    void 현재_턴_플레이어가_가진_기물_중_해당_위치의_기물을_가져온다() {
        // Given
        Position position = new Position(8, 4);

        // When & Then
        assertThat(janggiProcess.findCurrentTurnPlayerPieceAt(position))
                .isEqualTo(new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), position));
    }

    @Test
    void 현재_턴_플레이어가_가진_기물_중_해당_위치에_기물이_없는_경우_예외를_발생시킨다() {
        // Given
        Position position = new Position(1, 4);

        // When & Then
        assertThatThrownBy(() -> janggiProcess.findCurrentTurnPlayerPieceAt(position))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @Test
    void 현재_턴_플레이어의_기물을_움직인다() {
        // Given & When
        Position startPosition = new Position(8, 4);
        Position destination = new Position(7, 4);
        janggiProcess.movePiece(startPosition, destination);

        // Then
        assertThat(janggiProcess.findCurrentTurnPlayerPieceAt(new Position(7, 4)))
                .isEqualTo(new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), destination));
    }

    @Test
    void 해당_기물이_움직일_수_없는_곳으로_움직이려고_할_경우_예외를_발생시킨다() {
        // Given
        Position startPosition = new Position(8, 4);
        Position destination = new Position(7, 0);

        // When & Then
        assertThatThrownBy(() -> janggiProcess.movePiece(startPosition, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    void 이동_경로에_다른_기물이_있다면_이동할_수_없고_예외를_발생시킨다() {
        // Given
        Position startPositionOfChariot = new Position(9, 0);
        Position destination = new Position(5, 0);

        // When & Then
        assertThatThrownBy(() -> janggiProcess.movePiece(startPositionOfChariot, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 목적지에_같은_팀_기물이_있다면_움직일_수_없다() {
        // Given
        Position startPositionOfChariot = new Position(9, 0);
        Position destination = new Position(9, 1);

        // When & Then
        assertThatThrownBy(() -> janggiProcess.movePiece(startPositionOfChariot, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 포는_무조건_기물을_하나_뛰어_넘어야_한다() {
        // Given
        janggiProcess.findCurrentTurnPlayerPieceAt(new Position(6, 0)).changePosition(new Position(6, 1));
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(5, 1);

        // When
        janggiProcess.movePiece(startPositionOfCannon, destination);

        // Then
        assertThat(janggiProcess.findCurrentTurnPlayerPieceAt(new Position(5, 1)))
                .isEqualTo(new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), destination));
    }

    @Test
    void 포의_이동_경로에_기물이_없다면_움직일_수_없다() {
        // Given
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(5, 1);

        // When & Then
        assertThatThrownBy(() -> janggiProcess.movePiece(startPositionOfCannon, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 포는_두_개_이상의_기물은_뛰어넘을_수_없다() {
        // Given
        janggiProcess.findCurrentTurnPlayerPieceAt(new Position(6, 0)).changePosition(new Position(6, 1));
        janggiProcess.findCurrentTurnPlayerPieceAt(new Position(6, 2)).changePosition(new Position(5, 1));
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(4, 1);

        // When & Then
        assertThatThrownBy(() -> janggiProcess.movePiece(startPositionOfCannon, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 포는_상대팀_포를_죽일_수_없다() {
        // Given
        janggiProcess.findCurrentTurnPlayerPieceAt(new Position(6, 0)).changePosition(new Position(6, 1));
        Position startPositionOfCannon = new Position(7, 1);
        Position destination = new Position(2, 1);

        // When & Then
        assertThatThrownBy(() -> janggiProcess.movePiece(startPositionOfCannon, destination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치로 움직일 수 없는 상태입니다.");
    }

    @Test
    void 상대_팀의_궁을_죽이면_승리한다() {
        // Given
        Piece greenCannon = janggiProcess.findCurrentTurnPlayerPieceAt(new Position(7, 1));
        Position startPositionOfCannon = new Position(4, 4);
        greenCannon.changePosition(startPositionOfCannon);
        Position destination = new Position(1, 4);

        // When
        janggiProcess.movePiece(startPositionOfCannon, destination);

        // Then
        assertThat(janggiProcess.getWinner().getTeam()).isEqualTo(Team.GREEN);
    }

    @Test
    void 두_플레이어가_모두_살아있다면_승자를_결정할_수_없다() {
        // Given
        // When
        // Then
        assertThatThrownBy(() -> janggiProcess.getWinner())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("아직 승자가 결정되지 않았습니다.");
    }

    @Test
    void 턴을_바꾼다() {
        // Given
        // When
        janggiProcess.changeTurn();
        // Then
        assertThat(janggiProcess.getCurrentTurnTeam()).isEqualTo(Team.RED);
    }
}
