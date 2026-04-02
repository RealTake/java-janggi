package team.janggi.domain.piece.strategy;

import java.util.List;
import team.janggi.domain.Palace;
import team.janggi.domain.Position;
import team.janggi.domain.board.BoardStateReader;
import team.janggi.domain.piece.Piece;

public class ChoPalacelMoveStrategy implements MoveStrategy {
    private static final int MAX_MOVE_DISTANCE = 1;
    public static final ChoPalacelMoveStrategy instance = new ChoPalacelMoveStrategy();

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
