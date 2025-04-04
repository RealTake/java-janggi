package model.piece;

import model.piece.position.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;


import static model.piece.position.DefaultPiecePositions.BYEONG_FIFTH_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_FIRST_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_FOURTH_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_SECOND_RED;
import static model.piece.position.DefaultPiecePositions.BYEONG_THIRD_RED;
import static model.piece.position.DefaultPiecePositions.CANNON_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.CANNON_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.CANNON_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.CANNON_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.CHARIOT_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.CHARIOT_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.CHARIOT_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.CHARIOT_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.ELEPHANT_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.GENERAL_GREEN;
import static model.piece.position.DefaultPiecePositions.GENERAL_RED;
import static model.piece.position.DefaultPiecePositions.GUARD_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.GUARD_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.GUARD_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.GUARD_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.HORSE_LEFT_GREEN;
import static model.piece.position.DefaultPiecePositions.HORSE_LEFT_RED;
import static model.piece.position.DefaultPiecePositions.HORSE_RIGHT_GREEN;
import static model.piece.position.DefaultPiecePositions.HORSE_RIGHT_RED;
import static model.piece.position.DefaultPiecePositions.JOL_FIFTH_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_FIRST_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_FOURTH_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_SECOND_GREEN;
import static model.piece.position.DefaultPiecePositions.JOL_THIRD_GREEN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PiecesTest {

    @Test
    void RED_팀의_기물들을_생성한다() {
        // Given
        Pieces redPieces = Pieces.initializeRedTeamPieces();

        // When & Then
        assertThat(redPieces.getPieces()).containsExactlyInAnyOrder(
                new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), GENERAL_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FIRST_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_SECOND_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_THIRD_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FOURTH_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FIFTH_RED.getPosition())
        );
    }

    @Test
    void GREEN_팀의_기물들을_생성한다() {
        // Given
        Pieces greenPieces = Pieces.initializeGreenTeamPieces();

        // When & Then
        assertThat(greenPieces.getPieces()).containsExactlyInAnyOrder(
                new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), GENERAL_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_LEFT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_RIGHT_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_FIRST_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_SECOND_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_THIRD_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_FOURTH_GREEN.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.JOL), JOL_FIFTH_GREEN.getPosition())
        );
    }

    @Test
    void 기존의_Piece들로_Pieces를_생성한다() {
        // Given
        List<Piece> pieces = List.of(
                new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), new Position(1, 4)),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), new Position(0, 3)),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), new Position(3, 3))
        );

        // When
        Pieces result = Pieces.continuePiecesFrom(pieces);

        // When & Then
        assertThat(result.getPieces()).containsExactlyInAnyOrder(
                new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), new Position(1, 4)),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), new Position(0, 3)),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), new Position(3, 3))
        );
    }

    @Test
    void 해당_위치에_기물이_있다면_찾는다() {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();

        // When & Then
        assertThat(pieces.findPieceAt(new Position(1, 4)))
                .isEqualTo(new Piece(PieceProfile.generateFromPieceType(PieceType.GENERAL), new Position(1, 4)));
    }

    @Test
    void 기물이_존재하지_않는_위치로_기물을_찾으면_예외가_발생한다() {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();

        // When & Then
        assertThatThrownBy(() -> pieces.findPieceAt(new Position(2, 4)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 위치에 기물이 존재하지 않습니다.");
    }

    @CsvSource({
            "1, 4, true",
            "2, 4, false"
    })
    @ParameterizedTest
    void 기물이_존재하는지_확인한다(int row, int column, boolean expected) {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();
        Position position = new Position(row, column);

        // When & Then
        assertThat(pieces.isPieceExistAt(position)).isEqualTo(expected);
    }

    @Test
    void 해당_경로에_기물이_존재하는지_확인한다() {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();
        List<Position> route = List.of(
                new Position(0, 4),
                new Position(1, 4),
                new Position(2, 4)
        );

        // When & Then
        assertThat(pieces.isPieceExistAtRoute(route)).isEqualTo(true);
    }

    @Test
    void 해당_경로에_기물이_존재하지_않는_경우() {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();
        List<Position> route = List.of(
                new Position(4, 0),
                new Position(4, 1),
                new Position(4, 2),
                new Position(4, 3),
                new Position(4, 4)
        );

        // When & Then
        assertThat(pieces.isPieceExistAtRoute(route)).isEqualTo(false);
    }

    @Test
    void 주어진_위치의_기물을_삭제한다() {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();

        // When
        pieces.removePieceAt(new Position(1, 4));

        // Then
        assertThat(pieces.getPieces()).containsExactlyInAnyOrder(
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), GUARD_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), ELEPHANT_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.HORSE), HORSE_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), CHARIOT_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_LEFT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.CANNON), CANNON_RIGHT_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FIRST_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_SECOND_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_THIRD_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FOURTH_RED.getPosition()),
                new Piece(PieceProfile.generateFromPieceType(PieceType.BYEONG), BYEONG_FIFTH_RED.getPosition())
        );
    }

    @Test
    void 해당_경로_내에_있는_기물의_수를_계산한다() {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();

        // When
        int countOfPiecesAtRoute = pieces.countPiecesAtRoute(List.of(
                new Position(0, 0),
                new Position(0, 1),
                new Position(0, 2),
                new Position(0, 3),
                new Position(0, 4),
                new Position(0, 5)
        ));

        // Then
        assertThat(countOfPiecesAtRoute).isEqualTo(5);
    }

    @Test
    void 해당_경로에_포가_있는지_검사한다() {
        // Given
        Pieces pieces = Pieces.initializeRedTeamPieces();
        List<Position> routeIncludeCannon = List.of(
                new Position(1, 1),
                new Position(2, 1),
                new Position(3, 1),
                new Position(4, 1)
        );
        List<Position> routeExcludeCannon = List.of(
                new Position(3, 1),
                new Position(4, 1),
                new Position(5, 1),
                new Position(6, 1)
        );

        // When & Then
        assertThat(pieces.isCannonExistAtRoute(routeIncludeCannon)).isTrue();
        assertThat(pieces.isCannonExistAtRoute(routeExcludeCannon)).isFalse();
    }

    @CsvSource({
            "1, 4, GENERAL, true",
            "1, 3, GENERAL, false",
            "0, 3, GUARD, true",
            "0, 5, GUARD, true",
            "0, 4, GUARD, false",
            "0, 2, ELEPHANT, true",
            "0, 6, ELEPHANT, true",
            "0, 4, ELEPHANT, false",
            "0, 1, HORSE, true",
            "0, 7, HORSE, true",
            "0, 4, HORSE, false",
            "0, 0, CHARIOT, true",
            "0, 8, CHARIOT, true",
            "1, 3, CHARIOT, false",
            "2, 1, CANNON, true",
            "2, 7, CANNON, true",
            "0, 4, CANNON, false",
            "3, 0, BYEONG, true",
            "0, 4, BYEONG, false",
    })
    @ParameterizedTest
    void 해당_기물_타입이_주어진_위치에_존재하는지_확인한다(int row, int column, PieceType pieceType, boolean expected) {
        // Given
        Pieces redPieces = Pieces.initializeRedTeamPieces();
        Position position = new Position(row, column);

        // When & Then
        assertThat(redPieces.isPieceTypeExistAt(position, pieceType)).isEqualTo(expected);
    }

    @Test
    void 남아있는_기물들의_총_점수를_계산한다() {
        // Given
        Pieces redPieces = Pieces.initializeRedTeamPieces();

        // When & Then
        assertThat(redPieces.calculatePoints()).isEqualTo(172);
    }
}
