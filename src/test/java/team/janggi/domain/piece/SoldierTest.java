package team.janggi.domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import team.janggi.domain.EmptyBoardInitializer;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.board.BoardStatus;
import team.janggi.domain.board.LocalMemoryBoardStatus;

public class SoldierTest {

    private BoardStatus boardStatus = new LocalMemoryBoardStatus();

    @BeforeEach
    void setUp() {
        new EmptyBoardInitializer().initBoardStatus(boardStatus);
    }

    @Test
    void 초의_졸은_전진_할_수_있다() {
        // given
        Piece soldier = Piece.of(PieceType.SOLDIER, Team.CHO);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position destinationPosition = new Position(5, 4);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));
    }


    @Test
    void 한의_졸은_전진_할_수_있다() {
        // given
        Piece soldier = Piece.of(PieceType.SOLDIER, Team.HAN);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position destinationPosition = new Position(5, 6);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));
    }


    @Test
    void 초의_졸은_후퇴_할_수_없다() {
        // given
        Piece soldier = Piece.of(PieceType.SOLDIER, Team.CHO);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position destinationPosition = new Position(5, 6);

        // when & then
        Assertions.assertFalse(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));

    }

    @Test
    void 한의_졸은_후퇴_할_수_없다() {
        // given
        Piece soldier = Piece.of(PieceType.SOLDIER, Team.HAN);
        Position currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        Position destinationPosition = new Position(5, 4);

        // when & then
        Assertions.assertFalse(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));

    }

    @Test
    void 초의_졸은_좌으로_이동할_수_있다() {
        var soldier = Piece.of(PieceType.SOLDIER, Team.CHO);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var destinationPosition = new Position(4, 5);
        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));
    }

    @Test
    void 한의_졸은_좌으로_이동할_수_있다() {
        var soldier = Piece.of(PieceType.SOLDIER, Team.HAN);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var destinationPosition = new Position(6, 5);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));
    }

    @Test
    void 초의_졸은_우로_이동할_수_있다() {
        var soldier = Piece.of(PieceType.SOLDIER, Team.CHO);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var destinationPosition = new Position(6, 5);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));
    }

    @Test
    void 한의_졸은_우로_이동할_수_있다() {
        var soldier = Piece.of(PieceType.SOLDIER, Team.HAN);
        var currentPosition = new Position(5, 5);

        boardStatus.setPiece(currentPosition, soldier);

        var destinationPosition = new Position(4, 5);

        // when & then
        Assertions.assertTrue(soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));
    }


    @ParameterizedTest
    @CsvSource({
            "3,7,   4,8,    false",
            "5,7,   4,8,    false",
            "3,9,   4,8,    true",
            "5,9,   4,8,    true",
    })
    void 졸은_궁성에서_전진에_기반한_대각선_이동이_가능하다(
            int startX, int startY,
            int destinationX, int destinationY,
            boolean expected
    ) {
        // given
        Piece soldier = Piece.of(PieceType.SOLDIER, Team.CHO);
        Position currentPosition = new Position(startX, startY);
        Position destinationPosition = new Position(destinationX, destinationY);

        // when
        boardStatus.setPiece(currentPosition, soldier);

        // then
        Assertions.assertEquals(expected, soldier.canMove(currentPosition, destinationPosition, boardStatus.getBoardStatus()));
    }
}
