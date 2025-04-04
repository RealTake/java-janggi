package model.player;

import model.piece.Piece;
import model.piece.PieceType;
import model.piece.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public void movePiece(final Position startPosition, final Position destination, final Team team) {
        Player pieceOwner = getPlayerByTeam(team);
        Player opponent = getPlayerByTeam(team.getOtherTeam());
        Piece movePiece = pieceOwner.findPieceAt(startPosition);
        validateMoveOfPiece(movePiece, destination, pieceOwner, opponent);
        movePiece.changePosition(destination);
    }

    private void validateMoveOfPiece(final Piece movePiece, final Position destination, final Player pieceOwner, final Player opponent) {
        validateDestination(movePiece, destination, pieceOwner, opponent);
        validateMiddleRoute(movePiece, destination, pieceOwner, opponent);
        removeOtherPlayerPieceIfExistAt(destination, opponent);
    }

    private void validateDestination(final Piece piece, final Position destination, final Player pieceOwner, final Player opponent) {
        if (piece.isPieceTypeOf(PieceType.CANNON)) {
            validateDestinationOfCannon(destination, pieceOwner, opponent);
            return;
        }
        validateDestinationExcludeCannon(destination, pieceOwner);
    }

    private void validateDestinationOfCannon(final Position destination, final Player pieceOwner, final Player opponent) {
        if (pieceOwner.isPieceExistAt(destination) || opponent.isCannonExistAt(destination)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없는 상태입니다.");
        }
    }

    private void validateDestinationExcludeCannon(final Position destination, final Player pieceOwner) {
        if (pieceOwner.isPieceExistAt(destination)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없는 상태입니다.");
        }
    }

    private void validateMiddleRoute(final Piece piece, final Position destination, final Player pieceOwner, final Player opponent) {
        List<Position> routeToDestinationExcludeDestination = excludeDestinationInRoute(piece, destination);
        if (piece.isPieceTypeOf(PieceType.CANNON)) {
            validateMiddleRouteOfCannon(routeToDestinationExcludeDestination, pieceOwner, opponent);
            return;
        }
        validateMiddleRouteExcludeCannon(routeToDestinationExcludeDestination, pieceOwner, opponent);
    }

    private List<Position> excludeDestinationInRoute(final Piece piece, final Position destination) {
        List<Position> routeToDestination = new ArrayList<>(piece.calculateRouteToDestination(destination));
        routeToDestination.removeFirst();
        return routeToDestination;
    }

    private void validateMiddleRouteOfCannon(final List<Position> routeToDestinationExcludeDestination, final Player pieceOwner, final Player opponent) {
        int countOfPiecesAtRouteExcludeDestination = pieceOwner.countPiecesAtRoute(routeToDestinationExcludeDestination)
                + opponent.countPiecesAtRoute(routeToDestinationExcludeDestination);
        if (countOfPiecesAtRouteExcludeDestination != 1
                || pieceOwner.isCannonExistAtRoute(routeToDestinationExcludeDestination) || opponent.isCannonExistAtRoute(routeToDestinationExcludeDestination)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없는 상태입니다.");
        }
    }

    private void validateMiddleRouteExcludeCannon(final List<Position> routeToDestinationExcludeDestination, final Player pieceOwner, final Player opponent) {
        if (pieceOwner.isPieceExistAtRoute(routeToDestinationExcludeDestination) || opponent.isPieceExistAtRoute(routeToDestinationExcludeDestination)) {
            throw new IllegalArgumentException("해당 위치로 움직일 수 없는 상태입니다.");
        }
    }

    private void removeOtherPlayerPieceIfExistAt(final Position position, final Player opponent) {
        if (opponent.isPieceExistAt(position)) {
            makeOtherPlayerGameOverIfGeneralBeKilled(opponent, position);
            opponent.removePieceAt(position);
        }
    }

    private void makeOtherPlayerGameOverIfGeneralBeKilled(final Player opponent, final Position destination) {
        if (opponent.isGeneralExistAt(destination)) {
            opponent.makeDead();
        }
    }

    public int calculateTeamPoints(final Team team) {
        return getPlayerByTeam(team).calculatePoints();
    }

    public Player getPlayerByTeam(final Team team) {
        return players.stream()
                .filter(player -> player.getTeam() == team)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 팀입니다."));
    }

    public int countAlivePlayers() {
        return Math.toIntExact(players.stream()
                .filter(Player::isAlive)
                .count());
    }

    public Player getFirstAlivePlayers() {
        return players.stream()
                .filter(Player::isAlive)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("플레이어가 존재하지 않습니다."));
    }
}
