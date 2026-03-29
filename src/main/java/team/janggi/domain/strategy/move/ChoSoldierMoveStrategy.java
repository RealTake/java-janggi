package team.janggi.domain.strategy.move;

import team.janggi.domain.Position;
import team.janggi.domain.status.BoardStateReader;

public class ChoSoldierMoveStrategy implements MoveStrategy {
    public static final ChoSoldierMoveStrategy instance = new ChoSoldierMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader stateReader) {
        return isForward(from, to) || isSideMove(from, to);
    }

    private boolean isForward(Position from, Position to) {
        return from.y() - to.y() == 1 && from.x() == to.x();
    }

    private boolean isSideMove(Position from, Position to) {
        return from.y() == to.y() && Math.abs(from.x() - to.x()) == 1;
    }
}
