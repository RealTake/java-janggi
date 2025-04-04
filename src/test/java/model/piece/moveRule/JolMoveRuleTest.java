package model.piece.moveRule;

import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class JolMoveRuleTest {

    private MoveRule jolMoveRule;

    @BeforeEach
    void init() {
        jolMoveRule = new JolMoveRule();
    }

    @Test
    void 수평_방향으로의_졸의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position destination = new Position(5, 4);
        Position destination2 = new Position(5, 6);

        // When & Then
        assertThat(jolMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(5, 4));
        assertThat(jolMoveRule.calculateRouteToDestination(startPosition, destination2))
                .containsExactlyInAnyOrder(new Position(5, 6));
    }

    @Test
    void 수직_방향으로의_졸의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position destination = new Position(4, 5);

        // When & Then
        assertThat(jolMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(4, 5));
    }

    @Test
    void 졸_기물이_이동할_수_없는_위치로는_경로를_계산할_수_없다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position invalidDestination = new Position(7, 7);

        // When & Then
        assertThatThrownBy(() -> jolMoveRule.calculateRouteToDestination(startPosition, invalidDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    void 궁성_내부에서는_왼쪽위로_이동할_수_있다() {
        // Given
        Position startPosition = new Position(8, 4);
        Position destination = new Position(7, 3);

        // When & Then
        assertThat(jolMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(7, 3));
    }

    @Test
    void 궁성_내부에서는_오른쪽위로_이동할_수_있다() {
        // Given
        Position startPosition = new Position(8, 4);
        Position destination = new Position(7, 5);

        // When & Then
        assertThat(jolMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(7, 5));
    }
}
