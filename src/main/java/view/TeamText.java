package view;

import model.player.Team;

import java.util.Arrays;

public enum TeamText {

    RED(Team.RED, "한 나라"),
    GREEN(Team.GREEN, "초 나라");

    private final Team team;
    private final String text;

    TeamText(final Team team, final String text) {
        this.team = team;
        this.text = text;
    }

    static String textOf(final Team team) {
        TeamText teamText = Arrays.stream(values())
                .filter(value -> value.team == team)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 팀입니다."));
        return teamText.text;
    }
}
