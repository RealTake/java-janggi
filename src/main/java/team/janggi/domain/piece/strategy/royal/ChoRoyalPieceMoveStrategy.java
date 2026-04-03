package team.janggi.domain.piece.strategy.royal;

import java.util.List;
import team.janggi.domain.Palace;
import team.janggi.domain.Position;
import team.janggi.domain.board.BoardStateReader;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.strategy.MoveStrategy;

public class ChoRoyalPieceMoveStrategy implements MoveStrategy {
    private static final int MAX_MOVE_DISTANCE = 1;
    public static final ChoRoyalPieceMoveStrategy instance = new ChoRoyalPieceMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader stateReader) {
       return (isValidDirection(from, to)
               || Palace.isChoDiagonalPath(List.of(from, to)))
               && isValidObstacle(from, to, stateReader);
    }

    private boolean isValidDirection(Position from, Position to) {
        if (!Palace.isInChoPalace(from) || !Palace.isInChoPalace(to)) {
            return false;
        }

        final int dx = Math.abs(from.x() - to.x());
        final int dy = Math.abs(from.y() - to.y());

        return (dx + dy) == MAX_MOVE_DISTANCE;
    }

    private boolean isValidObstacle(Position from, Position to, BoardStateReader stateReader) {
        Piece toPiece = stateReader.getPiece(to);
        Piece fromPiece = stateReader.getPiece(from);
        return !toPiece.isSameTeam(fromPiece);
    }

}
