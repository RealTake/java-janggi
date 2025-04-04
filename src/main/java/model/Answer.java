package model;

import java.util.Arrays;

public enum Answer {

    YES("y"),
    NO("n");

    private final String input;

    Answer(final String input) {
        this.input = input;
    }

    public static Answer of(final String input) {
        return Arrays.stream(values())
                .filter(answer -> answer.input.equalsIgnoreCase(input))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다."));
    }

    public boolean isYes() {
        return this == YES;
    }
}
