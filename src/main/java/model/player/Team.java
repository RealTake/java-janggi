package model.player;

public enum Team {

    RED,
    GREEN;

    public Team getOtherTeam() {
        if (this == RED) {
            return GREEN;
        }
        return RED;
    }
}
