package model.piece;

import model.piece.position.Position;

import java.util.List;
import java.util.Objects;

public class Piece {

    private final PieceProfile pieceProfile;
    private Position position;

    public Piece(final PieceProfile pieceProfile, final Position position) {
        this.pieceProfile = pieceProfile;
        this.position = position;
    }

    public List<Position> calculateRouteToDestination(final Position destination) {
        return pieceProfile.calculateRouteToDestination(position, destination);
    }

    public void changePosition(final Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public boolean isPieceTypeOf(final PieceType pieceType) {
        return pieceProfile.isPieceTypeOf(pieceType);
    }

    public PieceType getPieceType() {
        return pieceProfile.getPieceType();
    }

    public int getPoint() {
        return pieceProfile.getPieceType().getPoint();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(pieceProfile, piece.pieceProfile) && Objects.equals(position, piece.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceProfile, position);
    }
}
