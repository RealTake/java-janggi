package team.janggi.domain.board;

import java.util.HashMap;
import java.util.Map;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Empty;
import team.janggi.domain.piece.Piece;

public class LocalMemoryBoardStatus implements BoardStatus, BoardStateReader {
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

        map.put(to, piece);
        map.put(from, Empty.instance);
    }

    @Override
    public void setPiece(Position position, Piece piece) {
        map.put(position, piece);
    }

    @Override
    public BoardStateReader getBoardStatus() {
        return this;
    }

    @Override
    public boolean isOutOfBounds(Position position) {
        return !map.containsKey(position);
    }

    @Override
    public Piece get(Position position) {
        return getPiece(position);
    }

    @Override
    public int size() {
        return map.size();
    }
}
