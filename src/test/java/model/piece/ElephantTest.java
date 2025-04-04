package model.piece;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ElephantTest {

    private Piece elephant;

    @BeforeEach
    void init() {
        elephant = new Piece(PieceProfile.generateFromPieceType(PieceType.ELEPHANT), new Position(5, 5));
    }

    @Test
    void 상_기물의_PieceType을_반환한다() {
        // Given
        // When & Then
        assertThat(elephant.getPieceType()).isEqualTo(PieceType.ELEPHANT);
    }

    @Test
    void 상_기물이_움직일_수_있는_위치로_가는_경로를_계산한다() {
        // Given
        Position destination = new Position(2, 3);
        Position destination2 = new Position(2, 7);
        Position destination3 = new Position(3, 2);
        Position destination4 = new Position(7, 2);
        Position destination5 = new Position(3, 8);
        Position destination6 = new Position(7, 8);
        Position destination7 = new Position(8, 3);
        Position destination8 = new Position(8, 7);

        // When & Then
        assertThat(elephant.calculateRouteToDestination(destination))
                .containsExactlyInAnyOrder(new Position(2, 3), new Position(3, 4), new Position(4, 5));
        assertThat(elephant.calculateRouteToDestination(destination2))
                .containsExactlyInAnyOrder(new Position(2, 7), new Position(3, 6), new Position(4, 5));
        assertThat(elephant.calculateRouteToDestination(destination3))
                .containsExactlyInAnyOrder(new Position(3, 2), new Position(4, 3), new Position(5, 4));
        assertThat(elephant.calculateRouteToDestination(destination4))
                .containsExactlyInAnyOrder(new Position(7, 2), new Position(6, 3), new Position(5, 4));
        assertThat(elephant.calculateRouteToDestination(destination5))
                .containsExactlyInAnyOrder(new Position(3, 8), new Position(4, 7), new Position(5, 6));
        assertThat(elephant.calculateRouteToDestination(destination6))
                .containsExactlyInAnyOrder(new Position(7, 8), new Position(6, 7), new Position(5, 6));
        assertThat(elephant.calculateRouteToDestination(destination7))
                .containsExactlyInAnyOrder(new Position(8, 3), new Position(7, 4), new Position(6, 5));
        assertThat(elephant.calculateRouteToDestination(destination8))
                .containsExactlyInAnyOrder(new Position(8, 7), new Position(7, 6), new Position(6, 5));
    }

    @Test
    void 상_기물이_이동할_수_없는_위치로는_경로를_계산할_수_없다() {
        // Given
        Position invalidDestination = new Position(6, 6);

        // When & Then
        assertThatThrownBy(() -> elephant.calculateRouteToDestination(invalidDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }
}
