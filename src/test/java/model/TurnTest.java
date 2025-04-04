package model;

import model.player.Team;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TurnTest {

    @Test
    void 현재_턴의_팀을_가져온다() {
        // Given
        Turn turn = new Turn(Team.GREEN);

        // When & Then
        assertThat(turn.getCurrentTurnTeam()).isEqualTo(Team.GREEN);
    }

    @Test
    void 턴을_바꾼다() {
        // Given
        Turn turn = new Turn(Team.GREEN);

        // When
        turn.changeTurn();

        // Then
        assertThat(turn.getCurrentTurnTeam()).isEqualTo(Team.RED);
    }
}
