package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.ElephantMoveStrategy;
import team.janggi.domain.strategy.move.MoveStrategy;

/**
 * 상
 */
public class Elephant extends Piece {

    public Elephant(Team team) {
        super(team, PieceType.ELEPHANT, ElephantMoveStrategy.instance);
    }
}
