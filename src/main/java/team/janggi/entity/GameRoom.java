package team.janggi.entity;

import java.time.LocalDateTime;
import team.janggi.domain.Team;

public class GameRoom {
    private final long id;
    private Team currentTurn;
    private final LocalDateTime createdDt;

    public GameRoom(Team currentTurn) {
        this(-1, currentTurn, LocalDateTime.now());
    }

    public GameRoom(long id, Team currentTurn, LocalDateTime createdDt) {
        this.id = id;
        this.currentTurn = currentTurn;
        this.createdDt = createdDt;
    }

    public void changeTurn(Team nextTurn) {
        this.currentTurn = nextTurn;
    }

    public long getId() {
        return id;
    }

    public Team getCurrentTurn() {
        return currentTurn;
    }

    public LocalDateTime getCreatedDt() {
        return createdDt;
    }

}