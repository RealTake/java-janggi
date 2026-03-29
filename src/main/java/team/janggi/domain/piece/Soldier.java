package team.janggi.domain.piece;

import team.janggi.domain.Team;
import team.janggi.domain.strategy.move.ChoSoldierMoveStrategy;
import team.janggi.domain.strategy.move.HanSoldierMoveStrategy;
import team.janggi.domain.strategy.move.MoveStrategy;

/**
 * 병
 */
public class Soldier extends Piece {

    public Soldier(Team team) {
        super(team, PieceType.SOLDIER, moveStrategyOf(team));
    }

    private static MoveStrategy moveStrategyOf(Team team) {
        if (team == Team.CHO) {
            return ChoSoldierMoveStrategy.instance;
        }
        if (team == Team.HAN) {
            return HanSoldierMoveStrategy.instance;
        }

        throw new IllegalArgumentException("병은 조 또는 한 팀에 속해야 합니다.");
    }
}
