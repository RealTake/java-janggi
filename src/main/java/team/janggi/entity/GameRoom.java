package team.janggi.entity;

import java.time.LocalDateTime;
import team.janggi.domain.Team;

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

    public void changeTurn(Team nextTurn) {
        this.currentTurn = nextTurn;
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