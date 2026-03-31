package team.janggi.domain;

import team.janggi.domain.piece.Piece;
import team.janggi.domain.piece.PieceType;
import team.janggi.domain.status.BoardStateReader;
import team.janggi.domain.status.BoardStatus;
import team.janggi.domain.status.LocalMemoryBoardStatus;
import team.janggi.domain.strategy.BoardInitializer;

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
        return boardStatus.getBoardStatus();
    }

    public void move(Team team, Position from, Position to) {
        validate(team, from, to);
        boardStatus.movePiece(team, from, to);
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

    public boolean isOutOfBounds(Position position) {
        return boardStatus.isOutOfBounds(position);
    }
}
