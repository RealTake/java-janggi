package model.piece;

import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GuardTest {

    private Piece guard;

    @BeforeEach
    void init() {
        guard = new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), new Position(1, 4));
    }

    @Test
    void 사_기물의_PieceType을_반환한다() {
        // Given
        // When & Then
        assertThat(guard.getPieceType()).isEqualTo(PieceType.GUARD);
    }

    @Test
    void 사_기물이_수평_방향으로_움직일_수_있는_위치로_가는_경로를_계산한다() {
        // Given
        Position destination = new Position(1, 3);
        Position destination2 = new Position(1, 5);

        // When & Then
        assertThat(guard.calculateRouteToDestination(destination))
                .containsExactlyInAnyOrder(new Position(1, 3));
        assertThat(guard.calculateRouteToDestination(destination2))
                .containsExactlyInAnyOrder(new Position(1, 5));
    }

    @Test
    void 사_기물이_대각선_방향으로_움직일_수_있는_위치로_가는_경로를_계산한다() {
        // Given
        Position destination = new Position(0, 3);
        Position destination2 = new Position(0, 5);
        Position destination3 = new Position(1, 3);
        Position destination4 = new Position(1, 5);

        // When & Then
        assertThat(guard.calculateRouteToDestination(destination))
                .containsExactlyInAnyOrder(new Position(0, 3));
        assertThat(guard.calculateRouteToDestination(destination2))
                .containsExactlyInAnyOrder(new Position(0, 5));
        assertThat(guard.calculateRouteToDestination(destination3))
                .containsExactlyInAnyOrder(new Position(1, 3));
        assertThat(guard.calculateRouteToDestination(destination4))
                .containsExactlyInAnyOrder(new Position(1, 5));
    }

    @Test
    void 사_기물이_수직_방향으로_움직일_수_있는_위치로_가는_경로를_계산한다() {
        // Given
        Position destination = new Position(0, 4);
        Position destination2 = new Position(2, 4);

        // When & Then
        assertThat(guard.calculateRouteToDestination(destination))
                .containsExactlyInAnyOrder(new Position(0, 4));
        assertThat(guard.calculateRouteToDestination(destination2))
                .containsExactlyInAnyOrder(new Position(2, 4));
    }

    @Test
    void 사_기물이_이동할_수_없는_위치로는_경로를_계산할_수_없다() {
        // Given
        Piece guard = new Piece(PieceProfile.generateFromPieceType(PieceType.GUARD), new Position(2, 5));
        Position invalidDestination = new Position(3, 5);

        // When & Then
        assertThatThrownBy(() -> guard.calculateRouteToDestination(invalidDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }
}
