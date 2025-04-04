package model.piece.moveRule;

import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChariotMoveRuleTest {

    private MoveRule chariotMoveRule;

    @BeforeEach
    void init() {
        chariotMoveRule = new ChariotMoveRule();
    }

    @Test
    void 수평_방향으로의_차의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position destination = new Position(5, 0);
        Position destination2 = new Position(5, 1);
        Position destination3 = new Position(5, 2);
        Position destination4 = new Position(5, 3);
        Position destination5 = new Position(5, 4);
        Position destination6 = new Position(5, 6);
        Position destination7 = new Position(5, 7);
        Position destination8 = new Position(5, 8);

        // When & Then
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(5, 0), new Position(5, 1), new Position(5, 2), new Position(5, 3), new Position(5, 4));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination2))
                .containsExactlyInAnyOrder(new Position(5, 1), new Position(5, 2), new Position(5, 3), new Position(5, 4));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination3))
                .containsExactlyInAnyOrder(new Position(5, 2), new Position(5, 3), new Position(5, 4));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination4))
                .containsExactlyInAnyOrder(new Position(5, 3), new Position(5, 4));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination5))
                .containsExactlyInAnyOrder(new Position(5, 4));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination6))
                .containsExactlyInAnyOrder(new Position(5, 6));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination7))
                .containsExactlyInAnyOrder(new Position(5, 6), new Position(5, 7));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination8))
                .containsExactlyInAnyOrder(new Position(5, 6), new Position(5, 7), new Position(5, 8));
    }

    @Test
    void 수직_방향으로의_차의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position destination = new Position(0, 5);
        Position destination2 = new Position(1, 5);
        Position destination3 = new Position(2, 5);
        Position destination4 = new Position(3, 5);
        Position destination5 = new Position(4, 5);
        Position destination6 = new Position(6, 5);
        Position destination7 = new Position(7, 5);
        Position destination8 = new Position(8, 5);
        Position destination9 = new Position(9, 5);

        // When & Then
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(0, 5), new Position(1, 5), new Position(2, 5), new Position(3, 5), new Position(4, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination2))
                .containsExactlyInAnyOrder(new Position(1, 5), new Position(2, 5), new Position(3, 5), new Position(4, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination3))
                .containsExactlyInAnyOrder(new Position(2, 5), new Position(3, 5), new Position(4, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination4))
                .containsExactlyInAnyOrder(new Position(3, 5), new Position(4, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination5))
                .containsExactlyInAnyOrder(new Position(4, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination6))
                .containsExactlyInAnyOrder(new Position(6, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination7))
                .containsExactlyInAnyOrder(new Position(6, 5), new Position(7, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination8))
                .containsExactlyInAnyOrder(new Position(6, 5), new Position(7, 5), new Position(8, 5));
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination9))
                .containsExactlyInAnyOrder(new Position(6, 5), new Position(7, 5), new Position(8, 5), new Position(9, 5));
    }

    @Test
    void 차_기물이_이동할_수_없는_위치로는_경로를_계산할_수_없다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position invalidDestination = new Position(6, 6);

        // When & Then
        assertThatThrownBy(() -> chariotMoveRule.calculateRouteToDestination(startPosition, invalidDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }

    @Test
    void 궁성_내부에서는_왼쪽위로_이동할_수_있다() {
        // Given
        Position startPosition = new Position(9, 5);
        Position destination = new Position(7, 3);

        // When & Then
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(
                        new Position(7, 3),
                        new Position(8, 4));
    }

    @Test
    void 궁성_내부에서는_오른쪽위로_이동할_수_있다() {
        // Given
        Position startPosition = new Position(9, 3);
        Position destination = new Position(7, 5);

        // When & Then
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(
                        new Position(7, 5),
                        new Position(8, 4));
    }

    @Test
    void 궁성_내부에서는_왼쪽아래로_이동할_수_있다() {
        // Given
        Position startPosition = new Position(7, 5);
        Position destination = new Position(9, 3);

        // When & Then
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(
                        new Position(9, 3),
                        new Position(8, 4));
    }

    @Test
    void 궁성_내부에서는_오른쪽아래로_이동할_수_있다() {
        // Given
        Position startPosition = new Position(7, 3);
        Position destination = new Position(9, 5);

        // When & Then
        assertThat(chariotMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(
                        new Position(9, 5),
                        new Position(8, 4));
    }
}
