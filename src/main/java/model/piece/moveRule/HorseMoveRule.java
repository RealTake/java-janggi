package model.piece.moveRule;

import model.piece.position.Position;

import static model.piece.position.MovementChecker.isDownDownLeft;
import static model.piece.position.MovementChecker.isDownDownRight;
import static model.piece.position.MovementChecker.isLeftLeftDown;
import static model.piece.position.MovementChecker.isLeftLeftUp;
import static model.piece.position.MovementChecker.isRightRightDown;
import static model.piece.position.MovementChecker.isRightRightUp;
import static model.piece.position.MovementChecker.isUpUpLeft;
import static model.piece.position.MovementChecker.isUpUpRight;

class HorseMoveRule extends MultiStepMoveRule {

    @Override
    protected boolean isAvailableMoveOfPiece(final Position startPosition, final Position destination) {
        int rowStep = destination.calculateRowDifference(startPosition);
        int columnStep = destination.calculateColumnDifference(startPosition);
        return isUpUpLeft(rowStep, columnStep) || isUpUpRight(rowStep, columnStep)
                || isLeftLeftUp(rowStep, columnStep) || isLeftLeftDown(rowStep, columnStep)
                || isRightRightUp(rowStep, columnStep) || isRightRightDown(rowStep, columnStep)
                || isDownDownLeft(rowStep, columnStep) || isDownDownRight(rowStep, columnStep);
    }
}
