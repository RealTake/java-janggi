package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.MoveStrategy;

/**
 * 포
 */
public class Cannon extends Piece {

    public Cannon(Team team) {
        super(team, PieceType.CANNON, MoveStrategy.CannonMoveStrategy);
    }
}
