package team.janggi.domain.piece.strategy;

import team.janggi.domain.Position;
import team.janggi.domain.board.BoardStateReader;

public class EmptyMoveStrategy implements MoveStrategy {
    public static final EmptyMoveStrategy instance = new EmptyMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader stateReader) {
        return false;
    }
}
