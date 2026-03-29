package team.janggi.domain;

import java.util.HashMap;
import java.util.Map;
import team.janggi.domain.piece.Empty;
import team.janggi.domain.piece.Piece;

public class LocalMemoryBoardStatus implements BoardStatus {
    private final Map<Position, Piece> map;

    public LocalMemoryBoardStatus() {
        this.map = new HashMap<>();
    }

    @Override
    public Piece getPiece(Position position) {
        return map.get(position);
    }

    @Override
    public void movePiece(Team team, Position from, Position to) {
        final Piece piece = getPiece(from);

            if (!piece.canMove(from, to, getBoardStatus())) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }

            map.put(to, piece);
            map.put(from, Empty.instance);
    }

    @Override
    public void setPiece(Position position, Piece piece) {
        map.put(position, piece);
    }

    @Override
    public Map<Position, Piece> getBoardStatus() {
            return new HashMap<>(map);
    }

    @Override
    public boolean isOutOfBounds(Position position) {
        return !map.containsKey(position);
    }
}
