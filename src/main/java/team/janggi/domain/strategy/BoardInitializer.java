package team.janggi.domain.strategy;

import team.janggi.domain.status.BoardStatus;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Empty;
import team.janggi.domain.strategy.layout.normal.NormalLayoutStrategy;

public class BoardInitializer implements NormalBoardStructStrategy {
    private static final int NORMAL_BOARD_Y_SIZE = 10;
    private static final int NORMAL_BOARD_X_SIZE = 9;

    private final NormalLayoutStrategy layout;

    public BoardInitializer(NormalLayoutStrategy layout) {
        this.layout = layout;
    }

    @Override
    public void initBoardStatus(BoardStatus status) {
        initMapByEmpty(status);

        layout.init(status);
    }

    private void initMapByEmpty( BoardStatus status) {
        for (int y = 0; y < NORMAL_BOARD_Y_SIZE; y++) {
            initMapRowByEmpty(status, y);
        }
    }

    private void initMapRowByEmpty(BoardStatus status, int y) {
        for (int x = 0; x < NORMAL_BOARD_X_SIZE; x++) {
            status.setPiece(new Position(x, y), Empty.instance);
        }
    }
}
