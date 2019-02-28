package de.troy_dev.henry_remote;

public class Movement {
    private int walkMode;
    private int turnMode;

    public Movement(int walkMode, int turnMode) {
        this.walkMode = walkMode;
        this.turnMode = turnMode;
    }

    public int getWalkMode() {
        return walkMode;
    }

    public void setWalkMode(int walkMode) {
        this.walkMode = walkMode;
    }

    public int getTurnMode() {
        return turnMode;
    }

    public void setTurnMode(int turnMode) {
        this.turnMode = turnMode;
    }
}
