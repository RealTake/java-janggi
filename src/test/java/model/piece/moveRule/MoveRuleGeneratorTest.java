package model.piece.moveRule;

import model.piece.PieceType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoveRuleGeneratorTest {

    @Test
    void 궁_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.GENERAL))
                .isInstanceOf(GeneralMoveRule.class);
    }

    @Test
    void 사_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.GUARD))
                .isInstanceOf(GuardMoveRule.class);
    }

    @Test
    void 졸_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.JOL))
                .isInstanceOf(JolMoveRule.class);
    }

    @Test
    void 병_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.BYEONG))
                .isInstanceOf(ByeongMoveRule.class);
    }

    @Test
    void 마_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.HORSE))
                .isInstanceOf(HorseMoveRule.class);
    }

    @Test
    void 상_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.ELEPHANT))
                .isInstanceOf(ElephantMoveRule.class);
    }

    @Test
    void 차_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.CHARIOT))
                .isInstanceOf(ChariotMoveRule.class);
    }

    @Test
    void 포_기물의_MoveRule을_생성한다() {
        // Given
        // When
        // Then
        assertThat(MoveRuleGenerator.generateFromPieceType(PieceType.CANNON))
                .isInstanceOf(CannonMoveRule.class);
    }
}
