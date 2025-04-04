package model.piece.moveRule;

import model.piece.position.Position;

import static model.piece.position.MovementChecker.isDown;
import static model.piece.position.MovementChecker.isDownLeft;
import static model.piece.position.MovementChecker.isDownRight;
import static model.piece.position.MovementChecker.isLeft;
import static model.piece.position.MovementChecker.isRight;
import static model.piece.position.MovementChecker.isUp;
import static model.piece.position.MovementChecker.isUpLeft;
import static model.piece.position.MovementChecker.isUpRight;

class OnlyInPalaceMoveRule extends OneStepMoveRule {

    @Override
    protected boolean isAvailableMoveOfPiece(final Position startPosition, final Position destination) {
        int rowStep = destination.calculateRowDifference(startPosition);
        int columnStep = destination.calculateColumnDifference(startPosition);
        return destination.isInPalace()
                && (isUp(rowStep, columnStep)
                || isLeft(rowStep, columnStep)
                || isRight(rowStep, columnStep)
                || isDown(rowStep, columnStep)
                || isUpLeft(rowStep, columnStep)
                || isUpRight(rowStep, columnStep)
                || isDownLeft(rowStep, columnStep)
                || isDownRight(rowStep, columnStep));
    }
}
