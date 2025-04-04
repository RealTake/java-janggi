package model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    @CsvSource({
            "Y, YES",
            "y, YES",
            "N, NO",
            "n, NO"
    })
    @ParameterizedTest
    void 주어진_입력값으로부터_Answer_값을_찾는다(String text, Answer expected) {
        // Given
        // When
        // Then
        assertThat(Answer.of(text)).isEqualTo(expected);
    }

    @Test
    void 잘못된_입력값이_입력된다면_예외를_발생시킨다() {
        // Given
        String invalidText = "a";

        // When & Then
        assertThatThrownBy(() -> Answer.of(invalidText))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 입력입니다.");
    }

    @Test
    void 주어진_Answer이_YES인지_확인한다() {
        // Given
        // When
        // Then
        assertThat(Answer.YES.isYes()).isTrue();
        assertThat(Answer.NO.isYes()).isFalse();
    }
}
