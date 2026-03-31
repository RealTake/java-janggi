package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.piece.strategy.ChariotMoveStrategy;

/**
 * 차
 */
public class Chariot extends Piece {

    public Chariot(Team team) {
        super(team, PieceType.CHARIOT, ChariotMoveStrategy.instance);
    }

}
