package team.janggi.domain.strategy.layout.normal;

import java.util.List;
import team.janggi.domain.BoardStatus;
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
import team.janggi.domain.strategy.layout.LayoutStrategy;

public class NormalLayoutStrategy implements LayoutStrategy {

    private final NormalSetup choSetup;
    private final NormalSetup hanSetup;

    // 초(CHO)는 이제 아래쪽(Y 번호가 큰 쪽)에 위치합니다.
    private static final int CHO_BACK_RANK_Y = 9;    // 맨 아래
    private static final int CHO_KING_RANK_Y = 8;    // 궁성 안
    private static final int CHO_CANNON_RANK_Y = 7;  // 포 라인
    private static final int CHO_SOLDIER_RANK_Y = 6; // 졸 라인

    // 한(HAN)은 이제 위쪽(Y 번호가 작은 쪽)에 위치합니다.
    private static final int HAN_BACK_RANK_Y = 0;    // 맨 위
    private static final int HAN_KING_RANK_Y = 1;    // 궁성 안
    private static final int HAN_CANNON_RANK_Y = 2;  // 포 라인
    private static final int HAN_SOLDIER_RANK_Y = 3; // 병 라인

    private static final List<Integer> SOLDIER_X_POSITIONS = List.of(0, 2, 4, 6, 8);

    public NormalLayoutStrategy(NormalSetup choSetup, NormalSetup hanSetup) {
        this.choSetup = choSetup;
        this.hanSetup = hanSetup;
    }

    @Override
    public void init(BoardStatus boardStatus) {
        initChoBatch(boardStatus);
        initHanBatch(boardStatus);
    }

    private void initChoBatch(BoardStatus boardStatus) {
        // 차
        boardStatus.setPiece(new Position(0, CHO_BACK_RANK_Y), new Chariot(Team.CHO));
        boardStatus.setPiece(new Position(8, CHO_BACK_RANK_Y), new Chariot(Team.CHO));

        // 포
        boardStatus.setPiece(new Position(1, CHO_CANNON_RANK_Y), new Cannon(Team.CHO));
        boardStatus.setPiece(new Position(7, CHO_CANNON_RANK_Y), new Cannon(Team.CHO));

        // 졸
        for (int x : SOLDIER_X_POSITIONS) {
            boardStatus.setPiece(new Position(x, CHO_SOLDIER_RANK_Y), new Soldier(Team.CHO));
        }

        // 왕
        boardStatus.setPiece(new Position(4, CHO_KING_RANK_Y), new King(Team.CHO));

        // 사
        boardStatus.setPiece(new Position(3, CHO_BACK_RANK_Y), new Guard(Team.CHO));
        boardStatus.setPiece(new Position(5, CHO_BACK_RANK_Y), new Guard(Team.CHO));

        // 상/마 배치
        setup(boardStatus, choSetup, Team.CHO, CHO_BACK_RANK_Y);
    }

    private void initHanBatch(BoardStatus boardStatus) {
        // 차
        boardStatus.setPiece(new Position(0, HAN_BACK_RANK_Y), new Chariot(Team.HAN));
        boardStatus.setPiece(new Position(8, HAN_BACK_RANK_Y), new Chariot(Team.HAN));

        // 포
        boardStatus.setPiece(new Position(1, HAN_CANNON_RANK_Y), new Cannon(Team.HAN));
        boardStatus.setPiece(new Position(7, HAN_CANNON_RANK_Y), new Cannon(Team.HAN));

        // 병
        for (int x : SOLDIER_X_POSITIONS) {
            boardStatus.setPiece(new Position(x, HAN_SOLDIER_RANK_Y), new Soldier(Team.HAN));
        }

        // 왕
        boardStatus.setPiece(new Position(4, HAN_KING_RANK_Y), new King(Team.HAN));

        // 사
        boardStatus.setPiece(new Position(3, HAN_BACK_RANK_Y), new Guard(Team.HAN));
        boardStatus.setPiece(new Position(5, HAN_BACK_RANK_Y), new Guard(Team.HAN));

        // 상/마 배치
        setup(boardStatus, hanSetup, Team.HAN, HAN_BACK_RANK_Y);
    }

    private void setup(BoardStatus boardStatus, NormalSetup setup, Team team, int y) {
        if (setup == NormalSetup.왼상차림) {
            setupPiece(boardStatus, new Elephant(team), team, 1, y);
            setupPiece(boardStatus, new Horse(team), team, 2, y);
            setupPiece(boardStatus, new Elephant(team), team, 6, y);
            setupPiece(boardStatus, new Horse(team), team, 7, y);
            return;
        }

        if (setup == NormalSetup.오른상차림) {
            setupPiece(boardStatus, new Horse(team), team, 1, y);
            setupPiece(boardStatus, new Elephant(team), team, 2, y);
            setupPiece(boardStatus, new Horse(team), team, 6, y);
            setupPiece(boardStatus, new Elephant(team), team, 7, y);
            return;
        }

        if (setup == NormalSetup.안상차림) {
            setupPiece(boardStatus, new Horse(team), team, 1, y);
            setupPiece(boardStatus, new Elephant(team), team, 2, y);
            setupPiece(boardStatus, new Elephant(team), team, 6, y);
            setupPiece(boardStatus, new Horse(team), team, 7, y);
            return;
        }

        if (setup == NormalSetup.바깥상차림) {
            setupPiece(boardStatus, new Elephant(team), team, 1, y);
            setupPiece(boardStatus, new Horse(team), team, 2, y);
            setupPiece(boardStatus, new Horse(team), team, 6, y);
            setupPiece(boardStatus, new Elephant(team), team, 7, y);
        }
    }

    private void setupPiece(BoardStatus boardStatus, Piece piece, Team team, int x, int y) {
        int actualX = x;

        if (team == Team.HAN) {
            actualX = 8 - x;
        }

        boardStatus.setPiece(new Position(actualX, y), piece);
    }
}