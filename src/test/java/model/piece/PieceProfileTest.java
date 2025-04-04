package model.piece;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PieceProfileTest {

    @CsvSource({
            "GENERAL",
            "GUARD",
            "JOL",
            "BYEONG",
            "CHARIOT",
            "CANNON",
            "HORSE",
            "ELEPHANT"
    })
    @ParameterizedTest
    void 기물의_PieceProfile을_생성한다(PieceType pieceType) {
        // Given
        // When
        // Then
        assertThatCode(() -> PieceProfile.generateFromPieceType(pieceType))
                .doesNotThrowAnyException();
    }
}
