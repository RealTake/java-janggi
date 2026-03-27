package team.janggi.domain.piece;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import team.janggi.domain.BoardStatus;
import team.janggi.domain.EmptyLayoutStrategy;
import team.janggi.domain.LocalMemoryBoardStatus;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.strategy.boardstruct.NormalBoardStrategy;

public class ChariotTest {

    private final BoardStatus boardStatus = new LocalMemoryBoardStatus();

    @BeforeEach
    void setUp() {
        new NormalBoardStrategy(EmptyLayoutStrategy.instance).initMapStatus(boardStatus);
    }

    @ParameterizedTest
    @CsvSource({
            "5, 5, 5, 4, true", // 상 1칸 이동
            "5, 5, 5, 6, true", // 하 1칸 이동
            "5, 5, 4, 5, true", // 좌 1칸 이동
            "5, 5, 6, 5, true", // 우 1칸 이동
            "5, 5, 5, 2, true", // 상 3칸 이동
            "5, 5, 5, 8, true", // 하 3칸 이동
            "5, 5, 2, 5, true", // 좌 3칸 이동
            "5, 5, 8, 5, true", // 우 3칸 이동
            "5, 5, 6, 6, false", // 우하 대각선
            "5, 5, 6, 4, false", // 우상 대각선
            "5, 5, 4, 4, false", // 좌상 대각선
            "5, 5, 4, 6, false"  // 좌하 대각선
    })
    void 차는_상하좌우로만_이동할수_있다(int startX, int startY, int endX, int endY, boolean expected) {
        // given
        Piece me = new Chariot(Team.CHO);
        Position from = new Position(startX, startY);
        Position to = new Position(endX, endY);

        // 피스 세팅
        boardStatus.setPiece(from, me);

        // when
        boolean canMove = me.canMove(from, to, boardStatus.getBoardStatus());

        // when & then
        Assertions.assertEquals(expected, canMove);
    }
}
