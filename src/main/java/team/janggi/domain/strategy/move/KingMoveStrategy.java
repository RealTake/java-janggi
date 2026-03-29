package team.janggi.domain.strategy.move;

import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.status.BoardStateReader;

public class KingMoveStrategy implements MoveStrategy {
    private static final int MAX_MOVE_DISTANCE = 1;

    public static final KingMoveStrategy instance = new KingMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader statusView) {
        return validateDirection(from, to) && !validateObstacle(from, to, statusView);
    }

    private boolean validateDirection(Position from, Position to) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        return (dx + dy) == MAX_MOVE_DISTANCE || (dx == MAX_MOVE_DISTANCE) && (dy == MAX_MOVE_DISTANCE);
    }

    private boolean validateObstacle(Position from, Position to, BoardStateReader statusView) {
        Piece toPiece = statusView.get(to);
        Piece fromPiece = statusView.get(from);
        return toPiece.isSameTeam(fromPiece);
    }

}
