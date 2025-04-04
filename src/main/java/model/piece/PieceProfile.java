package model.piece;

import model.piece.moveRule.MoveRule;
import model.piece.moveRule.MoveRuleGenerator;
import model.piece.position.Position;

import java.util.List;
import java.util.Objects;

public class PieceProfile {

    private final PieceType pieceType;
    private final MoveRule moveRule;

    private PieceProfile(final PieceType pieceType, final MoveRule moveRule) {
        this.pieceType = pieceType;
        this.moveRule = moveRule;
    }

    public static PieceProfile generateFromPieceType(final PieceType pieceType) {
        return new PieceProfile(pieceType, MoveRuleGenerator.generateFromPieceType(pieceType));
    }

    public List<Position> calculateRouteToDestination(final Position startPosition, final Position destination) {
        return moveRule.calculateRouteToDestination(startPosition, destination);
    }

    public boolean isPieceTypeOf(final PieceType pieceType) {
        return this.pieceType == pieceType;
    }

    PieceType getPieceType() {
        return pieceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceProfile that = (PieceProfile) o;
        return pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }
}
