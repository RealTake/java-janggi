package model.piece.moveRule;

import model.piece.position.Position;

import static model.piece.position.MovementChecker.isDownLeftStraight;
import static model.piece.position.MovementChecker.isDownRightStraight;
import static model.piece.position.MovementChecker.isDownStraight;
import static model.piece.position.MovementChecker.isLeftStraight;
import static model.piece.position.MovementChecker.isRightStraight;
import static model.piece.position.MovementChecker.isUpLeftStraight;
import static model.piece.position.MovementChecker.isUpRightStraight;
import static model.piece.position.MovementChecker.isUpStraight;

class ChariotMoveRule extends MultiStepMoveRule {

    @Override
    protected boolean isAvailableMoveOfPiece(final Position startPosition, final Position destination) {
        int rowStep = destination.calculateRowDifference(startPosition);
        int columnStep = destination.calculateColumnDifference(startPosition);
        if (startPosition.isInPalace() && destination.isInPalace()) {
            return isAvailableMoveOfPieceInsidePalace(rowStep, columnStep);
        }
        return isAvailableMoveOfPieceOutsidePalace(rowStep, columnStep);
    }

    private boolean isAvailableMoveOfPieceInsidePalace(final int rowStep, final int columnStep) {
        return isAvailableMoveOfPieceOutsidePalace(rowStep, columnStep)
                || isUpLeftStraight(rowStep, columnStep)
                || isUpRightStraight(rowStep, columnStep)
                || isDownLeftStraight(rowStep, columnStep)
                || isDownRightStraight(rowStep, columnStep);
    }

    private boolean isAvailableMoveOfPieceOutsidePalace(final int rowStep, final int columnStep) {
        return isUpStraight(rowStep, columnStep)
                || isLeftStraight(rowStep, columnStep)
                || isRightStraight(rowStep, columnStep)
                || isDownStraight(rowStep, columnStep);
    }
}
