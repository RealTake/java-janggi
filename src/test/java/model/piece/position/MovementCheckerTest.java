package model.piece.position;

import model.piece.position.MovementChecker;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class MovementCheckerTest {

    @CsvSource({
            "-1, 0, true",
            "1, 0, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위로_한_칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUp(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "0, -1, true",
            "1, 0, false"
    })
    @ParameterizedTest
    void 주어진_이동이_왼쪽으로_한_칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isLeft(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "0, 1, true",
            "1, 0, false"
    })
    @ParameterizedTest
    void 주어진_이동이_오른쪽으로_한_칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isRight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-1, -1, true",
            "1, 0, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽_왼쪽으로_한_칸씩_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpLeft(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-1, 1, true",
            "1, 0, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽_오른쪽으로_한_칸씩_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpRight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "1, -1, true",
            "1, 0, false"
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽_왼쪽으로_한_칸씩_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownLeft(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "1, 1, true",
            "1, 0, false"
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽_오른쪽으로_한_칸씩_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownRight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-1, -1, true",
            "-2, -2, true",
            "-3, -3, true",
            "-4, -4, true",
            "-5, -5, true",
            "-6, -6, true",
            "1, 0, false",
            "0, -1, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽과_왼쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpLeftStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-1, 1, true",
            "-2, 2, true",
            "-3, 3, true",
            "-4, 4, true",
            "-5, 5, true",
            "-6, 6, true",
            "1, 0, false",
            "0, -1, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽과_오른쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpRightStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "1, -1, true",
            "2, -2, true",
            "3, -3, true",
            "4, -4, true",
            "5, -5, true",
            "6, -6, true",
            "1, 0, false",
            "0, -1, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽과_왼쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownLeftStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "1, 1, true",
            "2, 2, true",
            "3, 3, true",
            "4, 4, true",
            "5, 5, true",
            "6, 6, true",
            "1, 0, false",
            "0, -1, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽과_오른쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownRightStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-1, 0, true",
            "-2, 0, true",
            "-3, 0, true",
            "-4, 0, true",
            "-5, 0, true",
            "-6, 0, true",
            "1, 0, false",
            "0, -1, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "0, -1, true",
            "0, -2, true",
            "0, -3, true",
            "0, -4, true",
            "0, -5, true",
            "0, -6, true",
            "-1, 0, false",
            "1, 0, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_왼쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isLeftStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "0, 1, true",
            "0, 2, true",
            "0, 3, true",
            "0, 4, true",
            "0, 5, true",
            "0, 6, true",
            "-1, 0, false",
            "1, 0, false",
            "0, -1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_오른쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isRightStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "1, 0, true",
            "2, 0, true",
            "3, 0, true",
            "4, 0, true",
            "5, 0, true",
            "6, 0, true",
            "-1, 0, false",
            "0, -1, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽으로_쭉_향하는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownStraight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-2, -1, true",
            "-2, 0, false",
            "0, -1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽_두칸_왼쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpUpLeft(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-2, 1, true",
            "-2, 0, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽_두칸_오른쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpUpRight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-1, -2, true",
            "-1, 0, false",
            "0, -2, false"
    })
    @ParameterizedTest
    void 주어진_이동이_왼쪽_두칸_위쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isLeftLeftUp(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "1, -2, true",
            "1, 0, false",
            "0, -2, false"
    })
    @ParameterizedTest
    void 주어진_이동이_왼쪽_두칸_아래쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isLeftLeftDown(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-1, 2, true",
            "-1, 0, false",
            "0, 2, false"
    })
    @ParameterizedTest
    void 주어진_이동이_오른쪽_두칸_위쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isRightRightUp(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "1, 2, true",
            "1, 0, false",
            "0, 2, false"
    })
    @ParameterizedTest
    void 주어진_이동이_오른쪽_두칸_아래쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isRightRightDown(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "2, -1, true",
            "2, 0, false",
            "0, -1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽_두칸_왼쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownDownLeft(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "2, 1, true",
            "2, 0, false",
            "0, 1, false"
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽_두칸_오른쪽_한칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownDownRight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-3, -2, true",
            "-2, -1, false",
            "-3, 0, false",
            "0, -2, false",
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽_세칸_왼쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpUpUpLeftLeft(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-3, 2, true",
            "-2, 1, false",
            "-3, 0, false",
            "0, 2, false",
    })
    @ParameterizedTest
    void 주어진_이동이_위쪽_세칸_오른쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isUpUpUpRightRight(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-2, -3, true",
            "-1, -2, false",
            "-2, 0, false",
            "0, -3, false",
    })
    @ParameterizedTest
    void 주어진_이동이_왼쪽_세칸_위쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isLeftLeftLeftUpUp(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "2, -3, true",
            "1, -2, false",
            "2, 0, false",
            "0, -3, false",
    })
    @ParameterizedTest
    void 주어진_이동이_왼쪽_세칸_아래쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isLeftLeftLeftDownDown(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "-2, 3, true",
            "-1, 2, false",
            "-2, 0, false",
            "0, 3, false",
    })
    @ParameterizedTest
    void 주어진_이동이_오른쪽_세칸_위쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isRightRightRightUpUp(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "2, 3, true",
            "1, 2, false",
            "2, 0, false",
            "0, 3, false",
    })
    @ParameterizedTest
    void 주어진_이동이_오른쪽_세칸_아래쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isRightRightRightDownDown(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "3, -2, true",
            "2, -1, false",
            "3, 0, false",
            "0, -2, false",
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽_세칸_왼쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownDownDownLeftLeft(rowStep, columnStep)).isEqualTo(expected);
    }

    @CsvSource({
            "3, 2, true",
            "2, 1, false",
            "3, 0, false",
            "0, 2, false",
    })
    @ParameterizedTest
    void 주어진_이동이_아래쪽_세칸_오른쪽_두칸_가는_방향인지_검사한다(int rowStep, int columnStep, boolean expected) {
        // Given
        // When & Then
        assertThat(MovementChecker.isDownDownDownRightRight(rowStep, columnStep)).isEqualTo(expected);
    }
}
