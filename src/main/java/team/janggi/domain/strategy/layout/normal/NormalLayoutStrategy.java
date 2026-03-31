package team.janggi.domain.strategy.layout.normal;

import java.util.List;
import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Cannon;
import team.janggi.domain.piece.Chariot;
import team.janggi.domain.piece.Elephant;
import team.janggi.domain.piece.Guard;
import team.janggi.domain.piece.Horse;
import team.janggi.domain.piece.King;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.Soldier;
import team.janggi.domain.status.BoardStatus;
import team.janggi.domain.strategy.layout.LayoutStrategy;

public class NormalLayoutStrategy implements LayoutStrategy {

    private final NormalSetup choSetup;
    private final NormalSetup hanSetup;

    // 초(CHO)는 이제 아래쪽(Y 번호가 큰 쪽)에 위치합니다.
    private static final int CHO_BACK_RANK_Y = 9;    // 맨 아래
    private static final int CHO_KING_RANK_Y = 8;    // 궁성 안
    private static final int CHO_CANNON_RANK_Y = 7;  // 포 라인
    private static final int CHO_SOLDIER_RANK_Y = 6; // 졸 라인

    private static final int HAN_OFFSET_Y = 9; // 한나라(HAN)는 초나라(CHO)에서 Y 좌표를 9에서 빼서 배치합니다.
    private static final int HAN_OFFSET_X = 8; // 한나라(HAN)는 초나라(CHO)에서 X 좌표를 8에서 빼서 배치합니다.

    private static final List<Integer> SOLDIER_X_POSITIONS = List.of(0, 2, 4, 6, 8);

    public NormalLayoutStrategy(NormalSetup choSetup, NormalSetup hanSetup) {
        this.choSetup = choSetup;
        this.hanSetup = hanSetup;
    }

    @Override
    public void init(BoardStatus boardStatus) {
        initLayoutByTeam(boardStatus, Team.CHO, choSetup);
        initLayoutByTeam(boardStatus, Team.HAN, hanSetup);
    }

    private void initLayoutByTeam(BoardStatus boardStatus, Team team, NormalSetup setup) {
        // 차
        setPieceYPositionReverseByTeam(boardStatus, new Chariot(team), team, 0, CHO_BACK_RANK_Y);
        setPieceYPositionReverseByTeam(boardStatus, new Chariot(team), team, 8, CHO_BACK_RANK_Y);

        // 포
        setPieceYPositionReverseByTeam(boardStatus, new Cannon(team), team, 1, CHO_CANNON_RANK_Y);
        setPieceYPositionReverseByTeam(boardStatus, new Cannon(team), team, 7, CHO_CANNON_RANK_Y);

        // 졸
        for (int x : SOLDIER_X_POSITIONS) {
            setPieceYPositionReverseByTeam(boardStatus, new Soldier(team), team, x, CHO_SOLDIER_RANK_Y);
        }

        // 왕
        setPieceYPositionReverseByTeam(boardStatus, new King(team), team, 4, CHO_KING_RANK_Y);

        // 사
        setPieceYPositionReverseByTeam(boardStatus, new Guard(team), team, 3, CHO_BACK_RANK_Y);
        setPieceYPositionReverseByTeam(boardStatus, new Guard(team), team, 5, CHO_BACK_RANK_Y);

        // 상치림
        setup(boardStatus, setup, team, CHO_BACK_RANK_Y);
    }

    private void setup(BoardStatus boardStatus, NormalSetup setup, Team team, int y) {
        if (setup == NormalSetup.왼상차림) {
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 1, y);
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 2, y);
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 6, y);
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 7, y);
            return;
        }

        if (setup == NormalSetup.오른상차림) {
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 1, y);
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 2, y);
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 6, y);
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 7, y);
            return;
        }

        if (setup == NormalSetup.안상차림) {
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 1, y);
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 2, y);
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 6, y);
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 7, y);
            return;
        }

        if (setup == NormalSetup.바깥상차림) {
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 1, y);
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 2, y);
            setPieceXPositionReverseByTeam(boardStatus, new Horse(team), team, 6, y);
            setPieceXPositionReverseByTeam(boardStatus, new Elephant(team), team, 7, y);
        }
    }

    private void setPieceXPositionReverseByTeam(BoardStatus boardStatus, Piece piece, Team team, int x, int y) {
        int actualX = x;

        if (team == Team.HAN) {
            actualX = HAN_OFFSET_X - x;
        }

        boardStatus.setPiece(new Position(actualX, y), piece);
    }

    private void setPieceYPositionReverseByTeam(BoardStatus boardStatus, Piece piece, Team team, int x, int y) {
        int actualY = y;

        if (team == Team.HAN) {
            actualY = HAN_OFFSET_Y - y;
        }

        boardStatus.setPiece(new Position(x, actualY), piece);
    }
}