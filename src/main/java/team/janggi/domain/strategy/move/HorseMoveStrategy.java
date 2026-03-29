package team.janggi.domain.strategy.move;

import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;
import team.janggi.domain.status.BoardStateReader;

public class HorseMoveStrategy implements MoveStrategy {
    public static final HorseMoveStrategy instance = new HorseMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader statusView) {
        return validateDirection(from, to) && isPathBlock(from, to, statusView) && canKill(from, to, statusView);
    }

    private boolean validateDirection(Position from, Position to) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        return (dx + dy) == 3;
    }

    private boolean isPathBlock(Position from, Position to, BoardStateReader statusView) {
        int dx = Math.abs(from.x() - to.x());
        int dy = Math.abs(from.y() - to.y());

        int fromX = from.x();
        int fromY = from.y();
        if (dx > dy) {
            fromX += (to.x() - from.x()) / 2;
        }
        if (dy > dx) {
            fromY += (to.y() - from.y()) / 2;
        }

        Position obstaclePosition = new Position(fromX, fromY);
        Piece obstacle = statusView.get(obstaclePosition);
        return obstacle.isSamePieceType(PieceType.EMPTY);
    }

    private boolean canKill(Position from, Position to, BoardStateReader statusView) {
        Piece currentPiece = statusView.get(from);
        Piece definationPiece = statusView.get(to);
        return !currentPiece.isSameTeam(definationPiece);
    }
}
