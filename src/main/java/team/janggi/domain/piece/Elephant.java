package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.piece.strategy.ElephantMoveStrategy;

/**
 * 상
 */
public class Elephant extends Piece {

    public Elephant(Team team) {
        super(team, PieceType.ELEPHANT, ElephantMoveStrategy.instance);
    }
}
