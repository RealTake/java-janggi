package model.piece;

public enum PieceType {
    GENERAL(100),
    GUARD(3),
    ELEPHANT(3),
    HORSE(5),
    CHARIOT(13),
    CANNON(7),
    BYEONG(2),
    JOL(2);

    private final int point;

    PieceType(final int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }
}
