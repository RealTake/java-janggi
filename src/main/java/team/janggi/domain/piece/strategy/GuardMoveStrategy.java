package team.janggi.domain.piece.strategy;

import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.board.BoardStateReader;

public class GuardMoveStrategy implements MoveStrategy {
    private static final int MAX_MOVE_DISTANCE = 1;

    public static final GuardMoveStrategy instance = new GuardMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader stateReader) {
        return validateDirection(from, to) && !validateObstacle(from, to, stateReader);
    }

    private boolean validateDirection(Position from, Position to) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        return (dx + dy) == MAX_MOVE_DISTANCE || (dx == MAX_MOVE_DISTANCE) && (dy == MAX_MOVE_DISTANCE);
    }

    private boolean validateObstacle(Position from, Position to, BoardStateReader stateReader) {
        Piece toPiece = stateReader.get(to);
        Piece fromPiece = stateReader.get(from);
        return toPiece.isSameTeam(fromPiece);
    }
}
