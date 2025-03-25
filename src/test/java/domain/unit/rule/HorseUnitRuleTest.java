package domain.unit.rule;


import static org.assertj.core.api.Assertions.assertThat;

import domain.position.Position;
import domain.position.Route;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HorseUnitRuleTest {

    @Test
    @DisplayName("말")
    void test1() {
        // given
        HorseUnitRule horseUnitRule = new HorseUnitRule();

        // when
        List<Route> routes = horseUnitRule.calculateAllRoute(Position.of(2, 9));

        // then
        assertThat(routes).hasSize(4);
    }
}
