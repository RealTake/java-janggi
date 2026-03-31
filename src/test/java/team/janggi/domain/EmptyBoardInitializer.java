package team.janggi.domain;

import team.janggi.domain.board.BoardInitializer;
import team.janggi.domain.board.BoardStatus;
import team.janggi.domain.board.NormalSetup;
import team.janggi.domain.piece.Piece;

public class EmptyBoardInitializer extends BoardInitializer {
    private static final int NORMAL_BOARD_Y_SIZE = 10;
    private static final int NORMAL_BOARD_X_SIZE = 9;

    public EmptyBoardInitializer() {
        super(NormalSetup.바깥상차림, NormalSetup.바깥상차림);
    }

    @Override
    public void initBoardStatus(BoardStatus status) {
        initMapByEmpty(status);
    }

    private void initMapByEmpty( BoardStatus status) {
        for (int y = 0; y < NORMAL_BOARD_Y_SIZE; y++) {
            initMapRowByEmpty(status, y);
        }
    }

    private void initMapRowByEmpty(BoardStatus status, int y) {
        for (int x = 0; x < NORMAL_BOARD_X_SIZE; x++) {
            status.setPiece(new Position(x, y), Piece.EMPTY_PIECE);
        }
    }
}
