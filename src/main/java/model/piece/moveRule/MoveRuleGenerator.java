package model.piece.moveRule;

import model.piece.PieceType;

public class MoveRuleGenerator {

    public static MoveRule generateFromPieceType(final PieceType pieceType) {
        if (pieceType == PieceType.GENERAL) {
            return new GeneralMoveRule();
        }
        if (pieceType == PieceType.GUARD) {
            return new GuardMoveRule();
        }
        if (pieceType == PieceType.BYEONG) {
            return new ByeongMoveRule();
        }
        if (pieceType == PieceType.JOL) {
            return new JolMoveRule();
        }
        if (pieceType == PieceType.CHARIOT) {
            return new ChariotMoveRule();
        }
        if (pieceType == PieceType.CANNON) {
            return new CannonMoveRule();
        }
        if (pieceType == PieceType.HORSE) {
            return new HorseMoveRule();
        }
        if (pieceType == PieceType.ELEPHANT) {
            return new ElephantMoveRule();
        }
        throw new IllegalArgumentException("잘못된 기물 타입입니다.");
    }
}
