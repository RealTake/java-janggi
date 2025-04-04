package model;

import model.piece.Piece;
import model.piece.position.Position;
import model.player.Player;
import model.player.Players;
import model.player.Team;

import java.util.List;

public class JanggiProcess {

    public static final int COUNT_OF_JANGGI_PLAYER = 2;
    private final Players players;
    private Turn turn;

    private JanggiProcess(final Players players, final Team firstTurnTeam) {
        this.players = players;
        this.turn = new Turn(firstTurnTeam);
    }

    public static JanggiProcess initializeJanggi(final Player greenPlayer, final Player redPlayer, final Team firstTurnTeam) {
        Players players = new Players(List.of(greenPlayer, redPlayer));
        return new JanggiProcess(players, firstTurnTeam);
    }

    public boolean isAllPlayersAlive() {
        return players.countAlivePlayers() == COUNT_OF_JANGGI_PLAYER;
    }

    public Piece findCurrentTurnPlayerPieceAt(final Position position) {
        return getCurrentTurnPlayer().findPieceAt(position);
    }

    private Player getCurrentTurnPlayer() {
        return players.getPlayerByTeam(turn.getCurrentTurnTeam());
    }

    public void movePiece(final Position startPosition, final Position destination) {
        players.movePiece(startPosition, destination, turn.getCurrentTurnTeam());
    }

    public void changeTurn() {
        turn.changeTurn();
    }

    public Team getCurrentTurnTeam() {
        return turn.getCurrentTurnTeam();
    }

    public Player getWinner() {
        if (isAllPlayersAlive()) {
            throw new IllegalArgumentException("아직 승자가 결정되지 않았습니다.");
        }
        return players.getFirstAlivePlayers();
    }

    public int calculateTeamPoints(final Team team) {
        return players.calculateTeamPoints(team);
    }

    public Player getPlayerByTeam(final Team team) {
        return players.getPlayerByTeam(team);
    }
}
