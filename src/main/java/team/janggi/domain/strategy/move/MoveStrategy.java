package team.janggi.domain.strategy.move;

import team.janggi.domain.Position;
import team.janggi.domain.status.BoardStateReader;

public interface MoveStrategy {

    boolean calculateMove(Position from,
                          Position to,
                          BoardStateReader statusView);
}
