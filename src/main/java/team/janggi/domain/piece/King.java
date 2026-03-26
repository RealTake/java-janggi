package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.KingMoveStrategy;

/**
 * 장군(왕)
 */
public class King extends Piece {

    public King(Team team) {
        super(team, PieceType.KING, KingMoveStrategy.instance);
    }
}
