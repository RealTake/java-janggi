package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.HorseMoveStrategy;

/**
 * 마
 */
public class Horse extends Piece {

    public Horse(Team team) {
        super(team, PieceType.HORSE, HorseMoveStrategy.instance);
    }
}
