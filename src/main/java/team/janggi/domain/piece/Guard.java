package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.GuardMoveStrategy;

/**
 * 사
 */
public class Guard extends Piece {

    public Guard(Team team) {
        super(team, PieceType.GUARD, GuardMoveStrategy.instance);
    }
}
