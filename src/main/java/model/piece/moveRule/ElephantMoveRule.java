package model.piece.moveRule;

import model.piece.position.Position;

import static model.piece.position.MovementChecker.isDownDownDownLeftLeft;
import static model.piece.position.MovementChecker.isDownDownDownRightRight;
import static model.piece.position.MovementChecker.isLeftLeftLeftDownDown;
import static model.piece.position.MovementChecker.isLeftLeftLeftUpUp;
import static model.piece.position.MovementChecker.isRightRightRightDownDown;
import static model.piece.position.MovementChecker.isRightRightRightUpUp;
import static model.piece.position.MovementChecker.isUpUpUpLeftLeft;
import static model.piece.position.MovementChecker.isUpUpUpRightRight;

class ElephantMoveRule extends MultiStepMoveRule {

    @Override
    protected boolean isAvailableMoveOfPiece(final Position startPosition, final Position destination) {
        int rowStep = destination.calculateRowDifference(startPosition);
        int columnStep = destination.calculateColumnDifference(startPosition);
        return isUpUpUpLeftLeft(rowStep, columnStep) || isUpUpUpRightRight(rowStep, columnStep)
                || isLeftLeftLeftUpUp(rowStep, columnStep) || isLeftLeftLeftDownDown(rowStep, columnStep)
                || isRightRightRightUpUp(rowStep, columnStep) || isRightRightRightDownDown(rowStep, columnStep)
                || isDownDownDownLeftLeft(rowStep, columnStep) || isDownDownDownRightRight(rowStep, columnStep);
    }
}
