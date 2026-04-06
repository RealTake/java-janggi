package team.janggi.domain.board;

import team.janggi.domain.Position;
import team.janggi.domain.Team;
import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;

public class Board {
    private final BoardStatus boardStatus;
    private final BoardInitializer initializer;

    public Board(BoardInitializer initializer) {
        this(new LocalMemoryBoardStatus(), initializer);
    }

    public Board(BoardStatus boardStatus, BoardInitializer initializer) {
        this.boardStatus = boardStatus;
        this.initializer = initializer;
    }

    public void init() {
        initializer.initBoardStatus(boardStatus);
    }

    public BoardStateReader getStatus() {
        return boardStatus.getBoardStateReader();
    }

    public void move(Team team, Position from, Position to) {
        validate(team, from, to);

        final Piece piece = boardStatus.getPiece(from);
        if (!piece.canMove(from, to, boardStatus.getBoardStateReader())) {
            throw new IllegalArgumentException("해당 위치로 이동할 수 없습니다.");
        }

        boardStatus.movePiece(from, to);
    }

    public double getScore(Team team) {
        final int score = boardStatus.getAllPiece().stream()
                .filter(piece -> piece.isSameTeam(team))
                .mapToInt(Piece::getScore)
                .sum();

        if (team == Team.HAN) {
            return score + 1.5;
        }

        return score;
    }

    private void validate(Team team, Position from, Position to) {
        validateTeam(team, from);
        validatePosition(from, to);
    }

    private void validateTeam(Team team, Position from) {
        final Piece piece = boardStatus.getPiece(from);

        if (!piece.isSameTeam(team)) {
            throw new IllegalArgumentException("자신의 기물만 이동할 수 있습니다.");
        }
    }

    private void validatePosition(Position from, Position to) {
        if (isOutOfBounds(from) || isOutOfBounds(to)) {
            throw new IllegalArgumentException("보드의 범위를 벗어난 위치입니다.");
        }

        if (isEmptySpace(from)) {
            throw new IllegalArgumentException("선택하신 위치에 기물이 없습니다.");
        }
    }

    private boolean isEmptySpace(Position from) {
        return boardStatus.getPiece(from).isSamePieceType(PieceType.EMPTY);
    }

    private boolean isOutOfBounds(Position position) {
        return boardStatus.isOutOfBounds(position);
    }
}
