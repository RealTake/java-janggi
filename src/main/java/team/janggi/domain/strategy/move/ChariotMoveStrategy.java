package team.janggi.domain.strategy.move;

import java.util.ArrayList;
import java.util.List;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;
import team.janggi.domain.status.BoardStateReader;

public class ChariotMoveStrategy implements MoveStrategy {

    public static final ChariotMoveStrategy instance = new ChariotMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader stateReader) {
        if (!isAllowDirection(from, to)) {
            return false;
        }

        final List<Piece> paths = getPaths(from, to, stateReader);

        if (!isValidPaths(paths)) {
            return false;
        }

        final Piece me = stateReader.get(from);
        final Piece lastPiece = stateReader.get(to);
        return canKill(lastPiece, me);
    }

    private boolean canKill(Piece target, Piece me) {
        return !target.isSameTeam(me);
    }

    private boolean isValidPaths(List<Piece> paths) {
        if (paths.isEmpty()) {
            return true;
        }

        return paths.stream().allMatch(piece -> piece.isSamePieceType(PieceType.EMPTY));
    }

    private List<Piece> getPaths(Position from, Position to, BoardStateReader stateReader) {
        List<Piece> paths = new ArrayList<>();

        int dx = Integer.signum(to.x() - from.x());
        int dy = Integer.signum(to.y() - from.y());

        int currentX = from.x() + dx;
        int currentY = from.y() + dy;

        while (currentX != to.x() || currentY != to.y()) {
            paths.add(stateReader.get(new Position(currentX, currentY)));
            currentX += dx;
            currentY += dy;
        }

        return paths;
    }

    public boolean isAllowDirection(Position from, Position to) {
        int dx = to.x() - from.x();
        int dy = to.y() - from.y();

        return (dx == 0 && dy > 0) || (dx == 0 && dy < 0) || (dx > 0 && dy == 0) || (dx < 0 && dy == 0);
    }
}
