package team.janggi.domain.strategy.move;

import team.janggi.domain.Position;
import team.janggi.domain.status.BoardStateReader;

public class EmptyMoveStrategy implements MoveStrategy {
    public static final EmptyMoveStrategy instance = new EmptyMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader statusView) {
        return false;
    }
}
