package team.janggi.domain.board;

import java.util.Collection;
import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public interface BoardStatus {

    void movePiece(Position source, Position destination);

    void setPiece(Position position, Piece piece);

    BoardStateReader getBoardStateReader();

    Piece getPiece(Position position);

    boolean isOutOfBounds(Position position);

    Collection<Piece> getAllPiece();
}
