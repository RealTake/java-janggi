package model.piece.moveRule;

import model.piece.position.Position;

import java.util.List;

public abstract class OneStepMoveRule implements MoveRule {

    @Override
    public final List<Position> calculateRouteToDestination(final Position startPosition, final Position destination) {
        if (isAvailableMoveOfPiece(startPosition, destination)) {
            return findRouteToDestination(startPosition, destination);
        }
        throw new IllegalArgumentException("현재 기물이 이동할 수 없는 위치입니다.");
    }

    protected abstract boolean isAvailableMoveOfPiece(final Position startPosition, final Position destination);

    private List<Position> findRouteToDestination(final Position startPosition, final Position destination) {
        int rowStep = destination.calculateRowDifference(startPosition);
        int columnStep = destination.calculateColumnDifference(startPosition);
        return List.of(startPosition.moveIfDestinationIsValid(rowStep, columnStep));
    }
}
