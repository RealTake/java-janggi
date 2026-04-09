package team.janggi.domain;

import java.time.LocalDateTime;

public class GameRoom {
    private final Long id;
    private Team currentTurn;
    private final LocalDateTime createdDt;

    public GameRoom(Team currentTurn) {
        this(null, currentTurn, LocalDateTime.now());
    }

    public GameRoom(Long id, Team currentTurn, LocalDateTime createdDt) {
        this.id = id;
        this.currentTurn = currentTurn;
        this.createdDt = createdDt;
    }

    public void changeTurn() {
        if (this.currentTurn == Team.CHO) {
            this.currentTurn = Team.HAN;
            return;
        }

        this.currentTurn = Team.CHO;
    }

    public Long getId() {
        return id;
    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

}