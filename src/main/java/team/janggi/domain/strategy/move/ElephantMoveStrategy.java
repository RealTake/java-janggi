package team.janggi.domain.strategy.move;

import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;
import team.janggi.domain.status.BoardStateReader;

public class ElephantMoveStrategy implements MoveStrategy {

    public static final ElephantMoveStrategy instance = new ElephantMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader statusView) {
        return validateDirection(from, to) && isPathBlock(from, to, statusView) && canKill(from, to, statusView);
    }

    private boolean validateDirection(Position from, Position to) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        return (dx + dy) == 5;
    }

    private boolean isPathBlock(Position from, Position to, BoardStateReader statusView) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        int fromX = from.x();
        int fromY = from.y();
        if (dx > dy) {
            fromX += (to.x() - from.x()) / 3;
        }
        if (dy > dx) {
            fromY += (to.y() - from.y()) / 3;
        }

        Position obstaclePosition = new Position(fromX, fromY);
        Piece obstacle = statusView.get(obstaclePosition);
        if (!obstacle.isSamePieceType(PieceType.EMPTY)){
            return false;
        }

        int x2 = (to.x() - from.x()) / Math.abs(to.x() - from.x());
        int y2 = (to.y() - from.y()) / Math.abs(to.y() - from.y());

        Position obstaclePosition2 = new Position(fromX+x2, fromY+y2);
        Piece obastacle2 = statusView.get(obstaclePosition2);
        if (!obastacle2.isSamePieceType(PieceType.EMPTY)) {
            return false;
        }
        return true;
    }

    private boolean canKill(Position from, Position to, BoardStateReader statusView) {
        Piece currentPiece = statusView.get(from);
        Piece definationPiece = statusView.get(to);
        return !currentPiece.isSameTeam(definationPiece);
    }

}
