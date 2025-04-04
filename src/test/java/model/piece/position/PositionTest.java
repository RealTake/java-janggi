package model.piece.position;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PositionTest {

    @Test
    void 행이_9를_초과하면_예외를_던진다() {
        // When & Then
        assertThatThrownBy(() -> new Position(10, 0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("10x9 범위를 벗어났습니다.");
    }

    @Test
    void 열이_8을_초과하면_예외를_던진다() {
        // When & Then
        assertThatThrownBy(() -> new Position(0, 9))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("10x9 범위를 벗어났습니다.");
    }

    @Test
    void 행과_열을_이동시킨다() {
        // Given
        Position position = new Position(5, 5);

        // When & Then
        assertThat(position.move(2, 1)).isEqualTo(new Position(7, 6));
    }

    @Test
    void 두_Position의_행_차이를_계산한다() {
        // Given
        Position position1 = new Position(5, 3);
        Position position2 = new Position(2, 1);

        // When & Then
        assertThat(position1.calculateRowDifference(position2)).isEqualTo(3);
    }

    @Test
    void 두_Position의_열_차이를_계산한다() {
        // Given
        Position position1 = new Position(5, 3);
        Position position2 = new Position(2, 1);

        // When & Then
        assertThat(position1.calculateColumnDifference(position2)).isEqualTo(2);
    }

    @Test
    void 해당_step만큼_움직일_수_있다면_움직인_Position을_반환한다() {
        // Given
        Position position = new Position(5, 5);

        // When & Then
        assertThat(position.moveIfDestinationIsValid(3, -3))
                .isEqualTo(new Position(8, 2));
    }

    @CsvSource({
            "5, 0",
            "0, 4",
            "5, 4"
    })
    @ParameterizedTest
    void 해당_step만큼_움직일_수_없다면_예외를_발생시킨다(int rowStep, int columnStep) {
        // Given
        Position position = new Position(5, 5);

        // When & Then
        assertThatThrownBy(() -> position.moveIfDestinationIsValid(rowStep, columnStep))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("10x9 범위를 벗어났습니다.");
    }

    @CsvSource({
            "0, 3, true",
            "0, 5, true",
            "2, 3, true",
            "2, 5, true",
            "7, 3, true",
            "7, 5, true",
            "9, 3, true",
            "9, 5, true",
            "3, 5, false",
            "6, 5, false",
            "2, 2, false",
            "2, 6, false"
    })
    @ParameterizedTest
    void 주어진_Position이_궁성_내부인지_확인한다(int row, int column, boolean expected) {
        // Given
        Position position = new Position(row, column);

        // When & Then
        assertThat(position.isInPalace()).isEqualTo(expected);
    }
}
