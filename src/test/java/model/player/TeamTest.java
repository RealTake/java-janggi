package model.player;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {

    @Test
    void 주어진_팀과_다른_팀을_가져온다() {
        // Given
        Team green = Team.GREEN;
        Team red = Team.RED;

        // When & Then
        assertThat(green.getOtherTeam()).isEqualTo(Team.RED);
        assertThat(red.getOtherTeam()).isEqualTo(Team.GREEN);
    }
}
