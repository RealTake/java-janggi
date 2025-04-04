package model.piece.moveRule;

import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GuardMoveRuleTest {

    private MoveRule guardMoveRule;

    @BeforeEach
    void init() {
        guardMoveRule = new GuardMoveRule();
    }

    @Test
    void 수평_방향으로의_사의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(1, 4);
        Position destination = new Position(1, 3);
        Position destination2 = new Position(1, 5);

        // When & Then
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(1, 3));
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination2))
                .containsExactlyInAnyOrder(new Position(1, 5));
    }

    @Test
    void 수직_방향으로의_사의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(1, 4);
        Position destination = new Position(0, 4);
        Position destination2 = new Position(2, 4);

        // When & Then
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(0, 4));
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination2))
                .containsExactlyInAnyOrder(new Position(2, 4));
    }

    @Test
    void 대각선_방향으로의_사의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(1, 4);
        Position destination = new Position(0, 3);
        Position destination2 = new Position(0, 5);
        Position destination3 = new Position(1, 3);
        Position destination4 = new Position(1, 5);

        // When & Then
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(0, 3));
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination2))
                .containsExactlyInAnyOrder(new Position(0, 5));
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination3))
                .containsExactlyInAnyOrder(new Position(1, 3));
        assertThat(guardMoveRule.calculateRouteToDestination(startPosition, destination4))
                .containsExactlyInAnyOrder(new Position(1, 5));
    }

    @Test
    void 사_기물이_이동할_수_없는_위치로는_경로를_계산할_수_없다() {
        // Given
        Position startPosition = new Position(2, 5);
        Position invalidDestination = new Position(3, 5);

        // When & Then
        assertThatThrownBy(() -> guardMoveRule.calculateRouteToDestination(startPosition, invalidDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }
}
