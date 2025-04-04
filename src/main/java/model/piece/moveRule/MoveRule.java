package model.piece.moveRule;

import model.piece.position.Position;

import java.util.List;

public interface MoveRule {
    List<Position> calculateRouteToDestination(final Position startPosition, final Position destination);
}
