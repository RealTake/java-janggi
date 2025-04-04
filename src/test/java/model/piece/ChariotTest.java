package model.piece;

import model.piece.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChariotTest {

    private Piece chariot;

    @BeforeEach
    void init() {
        chariot = new Piece(PieceProfile.generateFromPieceType(PieceType.CHARIOT), new Position(5, 5));
    }

    @Test
    void 차_기물의_PieceType을_반환한다() {
        // Given
        // When & Then
        assertThat(chariot.getPieceType()).isEqualTo(PieceType.CHARIOT);
    }

    @Test
    void 차_기물이_수평_방향으로_움직일_수_있는_위치로_가는_경로를_계산한다() {
        // Given
        Position destination = new Position(5, 0);
        Position destination2 = new Position(5, 1);
        Position destination3 = new Position(5, 2);
        Position destination4 = new Position(5, 3);
        Position destination5 = new Position(5, 4);
        Position destination6 = new Position(5, 6);
        Position destination7 = new Position(5, 7);
        Position destination8 = new Position(5, 8);

        // When & Then
        assertThat(chariot.calculateRouteToDestination(destination))
                .containsExactlyInAnyOrder(new Position(5, 0), new Position(5, 1), new Position(5, 2), new Position(5, 3), new Position(5, 4));
        assertThat(chariot.calculateRouteToDestination(destination2))
                .containsExactlyInAnyOrder(new Position(5, 1), new Position(5, 2), new Position(5, 3), new Position(5, 4));
        assertThat(chariot.calculateRouteToDestination(destination3))
                .containsExactlyInAnyOrder(new Position(5, 2), new Position(5, 3), new Position(5, 4));
        assertThat(chariot.calculateRouteToDestination(destination4))
                .containsExactlyInAnyOrder(new Position(5, 3), new Position(5, 4));
        assertThat(chariot.calculateRouteToDestination(destination5))
                .containsExactlyInAnyOrder(new Position(5, 4));
        assertThat(chariot.calculateRouteToDestination(destination6))
                .containsExactlyInAnyOrder(new Position(5, 6));
        assertThat(chariot.calculateRouteToDestination(destination7))
                .containsExactlyInAnyOrder(new Position(5, 6), new Position(5, 7));
        assertThat(chariot.calculateRouteToDestination(destination8))
                .containsExactlyInAnyOrder(new Position(5, 6), new Position(5, 7), new Position(5, 8));
    }

    @Test
    void 차_기물이_수직_방향으로_움직일_수_있는_위치로_가는_경로를_계산한다() {
        // Given
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
        assertThat(chariot.calculateRouteToDestination(destination))
                .containsExactlyInAnyOrder(new Position(0, 5), new Position(1, 5), new Position(2, 5), new Position(3, 5), new Position(4, 5));
        assertThat(chariot.calculateRouteToDestination(destination2))
                .containsExactlyInAnyOrder(new Position(1, 5), new Position(2, 5), new Position(3, 5), new Position(4, 5));
        assertThat(chariot.calculateRouteToDestination(destination3))
                .containsExactlyInAnyOrder(new Position(2, 5), new Position(3, 5), new Position(4, 5));
        assertThat(chariot.calculateRouteToDestination(destination4))
                .containsExactlyInAnyOrder(new Position(3, 5), new Position(4, 5));
        assertThat(chariot.calculateRouteToDestination(destination5))
                .containsExactlyInAnyOrder(new Position(4, 5));
        assertThat(chariot.calculateRouteToDestination(destination6))
                .containsExactlyInAnyOrder(new Position(6, 5));
        assertThat(chariot.calculateRouteToDestination(destination7))
                .containsExactlyInAnyOrder(new Position(6, 5), new Position(7, 5));
        assertThat(chariot.calculateRouteToDestination(destination8))
                .containsExactlyInAnyOrder(new Position(6, 5), new Position(7, 5), new Position(8, 5));
        assertThat(chariot.calculateRouteToDestination(destination9))
                .containsExactlyInAnyOrder(new Position(6, 5), new Position(7, 5), new Position(8, 5), new Position(9, 5));
    }

    @Test
    void 차_기물이_이동할_수_없는_위치로는_경로를_계산할_수_없다() {
        // Given
        Position invalidDestination = new Position(6, 6);

        // When & Then
        assertThatThrownBy(() -> chariot.calculateRouteToDestination(invalidDestination))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("현재 기물이 이동할 수 없는 위치입니다.");
    }
}
