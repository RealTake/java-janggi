package model.piece.moveRule;

import model.piece.position.Position;

import static model.piece.position.MovementChecker.isDown;
import static model.piece.position.MovementChecker.isDownLeft;
import static model.piece.position.MovementChecker.isDownRight;
import static model.piece.position.MovementChecker.isLeft;
import static model.piece.position.MovementChecker.isRight;

class ByeongMoveRule extends OneStepMoveRule {

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
                || (isDownLeft(rowStep, columnStep))
                || (isDownRight(rowStep, columnStep));
    }

    private boolean isAvailableMoveOfPieceOutsidePalace(final int rowStep, final int columnStep) {
        return isDown(rowStep, columnStep)
                || isLeft(rowStep, columnStep)
                || isRight(rowStep, columnStep);
    }
}
