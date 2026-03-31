package team.janggi.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PositionTest {

    @Test
    public void 좌표_값은_양수를_허용한다() {
        // given
        int x = 1;
        int y = 1;

        // when & then
        Assertions.assertDoesNotThrow(() -> new Position(x, y));
    }

    @Test
    public void 좌표_값은_음수를_허용하지않는다() {
        // given
        int x = -1;
        int y = -1;

        // when & then
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Position(x, y));
    }

}
