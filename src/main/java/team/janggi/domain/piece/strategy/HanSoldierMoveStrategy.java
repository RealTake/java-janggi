package team.janggi.domain.piece.strategy;

import team.janggi.domain.Position;
import team.janggi.domain.board.BoardStateReader;

public class HanSoldierMoveStrategy implements MoveStrategy {
    public static final HanSoldierMoveStrategy instance = new HanSoldierMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader stateReader) {
        return isForward(from, to) || isSideMove(from, to);
    }

    private boolean isForward(Position from, Position to) {
        return to.y() - from.y() == 1 && from.x() == to.x();
    }

    private boolean isSideMove(Position from, Position to) {
        return from.y() == to.y() && Math.abs(from.x() - to.x()) == 1;
    }
}
