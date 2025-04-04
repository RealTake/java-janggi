package model.piece.moveRule;

import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HorseMoveRuleTest {

    private MoveRule horseMoveRule;

    @BeforeEach
    void init() {
        horseMoveRule = new HorseMoveRule();
    }

    @Test
    void 마가_가능한_모든_방향의_움직임을_검사한다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position destination = new Position(3, 4);
        Position destination2 = new Position(3, 6);
        Position destination3 = new Position(4, 3);
        Position destination4 = new Position(6, 3);
        Position destination5 = new Position(4, 7);
        Position destination6 = new Position(6, 7);
        Position destination7 = new Position(7, 4);
        Position destination8 = new Position(7, 6);

        // When & Then
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination))
                .containsExactlyInAnyOrder(new Position(3, 4), new Position(4, 5));
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination2))
                .containsExactlyInAnyOrder(new Position(3, 6), new Position(4, 5));
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination3))
                .containsExactlyInAnyOrder(new Position(4, 3), new Position(5, 4));
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination4))
                .containsExactlyInAnyOrder(new Position(6, 3), new Position(5, 4));
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination5))
                .containsExactlyInAnyOrder(new Position(4, 7), new Position(5, 6));
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination6))
                .containsExactlyInAnyOrder(new Position(6, 7), new Position(5, 6));
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination7))
                .containsExactlyInAnyOrder(new Position(7, 4), new Position(6, 5));
        assertThat(horseMoveRule.calculateRouteToDestination(startPosition, destination8))
                .containsExactlyInAnyOrder(new Position(7, 6), new Position(6, 5));
    }

    @Test
    void 마_기물이_이동할_수_없는_위치로는_경로를_계산할_수_없다() {
        // Given
        Position startPosition = new Position(5, 5);
        Position invalidDestination = new Position(6, 6);

        // When & Then
        assertThatThrownBy(() -> horseMoveRule.calculateRouteToDestination(startPosition, invalidDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }
}
