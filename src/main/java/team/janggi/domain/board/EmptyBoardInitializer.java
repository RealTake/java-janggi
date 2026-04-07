package team.janggi.domain.board;

import team.janggi.domain.BoardSize;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public class EmptyBoardInitializer implements BoardInitializer {
    public static final EmptyBoardInitializer instance = new EmptyBoardInitializer();

    @Override
    public void initBoardStatus(BoardStatus status) {
        initMapByEmpty(status);
    }

    private void initMapByEmpty( BoardStatus status) {
        for (int y = 0; y < BoardSize.Y; y++) {
            initMapRowByEmpty(status, y);
        }
    }

    private void initMapRowByEmpty(BoardStatus status, int y) {
        for (int x = 0; x < BoardSize.X; x++) {
            status.setPiece(new Position(x, y), Piece.EMPTY_PIECE);
        }
    }
}
