package model.piece.moveRule;

import model.piece.position.Position;

import static model.piece.position.MovementChecker.isLeft;
import static model.piece.position.MovementChecker.isRight;
import static model.piece.position.MovementChecker.isUp;
import static model.piece.position.MovementChecker.isUpLeft;
import static model.piece.position.MovementChecker.isUpRight;

class JolMoveRule extends OneStepMoveRule {

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
                || isUpLeft(rowStep, columnStep)
                || isUpRight(rowStep, columnStep);
    }

    private boolean isAvailableMoveOfPieceOutsidePalace(final int rowStep, final int columnStep) {
        return isUp(rowStep, columnStep)
                || isLeft(rowStep, columnStep)
                || isRight(rowStep, columnStep);
    }
}
