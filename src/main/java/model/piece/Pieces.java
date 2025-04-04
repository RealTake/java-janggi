package model.piece;

import model.piece.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pieces {

    private final List<Piece> pieces;

    private Pieces(final List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public static Pieces initializeRedTeamPieces() {
        return new Pieces(TeamPiecesGenerator.generateRedTeamPieces());
    }

    public static Pieces initializeGreenTeamPieces() {
        return new Pieces(TeamPiecesGenerator.generateGreenTeamPieces());
    }

    public static Pieces continuePiecesFrom(final List<Piece> pieces) {
        return new Pieces(pieces);
    }

    public Piece findPieceAt(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다."));
    }

    public boolean isPieceExistAt(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(position));
    }

    public boolean isPieceTypeExistAt(final Position position, final PieceType pieceType) {
        if (isPieceExistAt(position)) {
            return findPieceAt(position).isPieceTypeOf(pieceType);
        }
        return false;
    }

    public boolean isPieceExistAtRoute(final List<Position> route) {
        return pieces.stream()
                .anyMatch(piece -> route.contains(piece.getPosition()));
    }

    public int countPiecesAtRoute(final List<Position> route) {
        return Math.toIntExact(pieces.stream()
                .filter(piece -> route.contains(piece.getPosition()))
                .count());
    }

    public boolean isCannonExistAtRoute(final List<Position> route) {
        return pieces.stream()
                .anyMatch(piece -> piece.isPieceTypeOf(PieceType.CANNON) && route.contains(piece.getPosition()));
    }

    public void removePieceAt(final Position position) {
        if (isPieceExistAt(position)) {
            Piece piece = findPieceAt(position);
            pieces.remove(piece);
        }
    }

    public int calculatePoints() {
        return pieces.stream()
                .mapToInt(Piece::getPoint)
                .sum();
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
