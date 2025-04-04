package model.player;

import model.piece.Piece;
import model.piece.PieceType;
import model.piece.Pieces;
import model.piece.position.Position;

import java.util.List;

public class Player {

    private final Pieces pieces;
    private final Team team;
    private boolean isAlive;

    public Player(final Pieces pieces, final Team team) {
        this.pieces = pieces;
        this.team = team;
        this.isAlive = true;
    }

    public Piece findPieceAt(final Position position) {
        return pieces.findPieceAt(position);
    }

    public boolean isPieceExistAtRoute(final List<Position> route) {
        return pieces.isPieceExistAtRoute(route);
    }

    public int countPiecesAtRoute(final List<Position> route) {
        return pieces.countPiecesAtRoute(route);
    }

    public boolean isCannonExistAtRoute(final List<Position> route) {
        return pieces.isCannonExistAtRoute(route);
    }

    public boolean isPieceExistAt(final Position position) {
        return pieces.isPieceExistAt(position);
    }

    public boolean isGeneralExistAt(final Position position) {
        return pieces.isPieceTypeExistAt(position, PieceType.GENERAL);
    }

    public boolean isCannonExistAt(final Position position) {
        return pieces.isPieceTypeExistAt(position, PieceType.CANNON);
    }

    public void removePieceAt(final Position position) {
        pieces.removePieceAt(position);
    }

    public int calculatePoints() {
        return pieces.calculatePoints();
    }

    public void makeDead() {
        isAlive = false;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public Team getTeam() {
        return team;
    }
}
