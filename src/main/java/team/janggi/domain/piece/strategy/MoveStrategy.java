package team.janggi.domain.piece.strategy;

import team.janggi.domain.Position;
import team.janggi.domain.board.BoardStateReader;

public interface MoveStrategy {

    boolean calculateMove(Position from,
                          Position to,
                          BoardStateReader stateReader);
}
