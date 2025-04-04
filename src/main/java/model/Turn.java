package model;

import model.player.Team;

public class Turn {

    private Team currentTurn;

    public Turn(final Team firstTurn) {
        this.currentTurn = firstTurn;
    }

    public void changeTurn() {
        currentTurn = currentTurn.getOtherTeam();
    }

    public Team getCurrentTurnTeam() {
        return currentTurn;
    }
}
