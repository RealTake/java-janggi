package team.janggi.domain.status;

import team.janggi.domain.Position;
import team.janggi.domain.piece.Piece;

public interface BoardStateReader {

    Piece get(Position position);

    int size();

}
