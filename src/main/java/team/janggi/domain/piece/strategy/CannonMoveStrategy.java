package team.janggi.domain.piece.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;
import team.janggi.domain.board.BoardStateReader;

public class CannonMoveStrategy implements MoveStrategy {
    public static final CannonMoveStrategy instance = new CannonMoveStrategy();

    @Override
    public boolean calculateMove(Position from, Position to, BoardStateReader stateReader) {
        if (!isAllowDirection(from, to)) {
            return false;
        }

        final List<Piece> paths = getPaths(from, to, stateReader);

        if (!isPathsValid(paths)) {
            return false;
        }

        final Piece me = stateReader.getPiece(from);
        final Piece lastPiece = stateReader.getPiece(to);
        return canKill(lastPiece, me);
    }

    private boolean canKill(Piece target, Piece me) {
        return !target.isSameTeam(me);
    }

    private boolean isPathsValid(List<Piece> paths) {
        if (paths.isEmpty()) {
            return false;
        }

        return paths.stream()
                .filter(this::isJumpAblePiece)
                .count() == 1;
    }

    private boolean isJumpAblePiece(Piece piece) {
        return Arrays.stream(PieceType.values())
                .filter(pieceType -> pieceType != PieceType.EMPTY)
                .filter(pieceType -> pieceType != PieceType.CANNON)
                .anyMatch(piece::isSamePieceType);
    }

    private List<Piece> getPaths(Position from, Position to, BoardStateReader stateReader) {
        List<Piece> paths = new ArrayList<>();

        int dx = Integer.signum(to.x() - from.x());
        int dy = Integer.signum(to.y() - from.y());

        int currentX = from.x() + dx;
        int currentY = from.y() + dy;

        while (currentX != to.x() || currentY != to.y()) {
            paths.add(stateReader.getPiece(new Position(currentX, currentY)));
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
