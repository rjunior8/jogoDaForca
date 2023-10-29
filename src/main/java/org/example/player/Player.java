package org.example.player;

public class Player implements PlayerInterface{
    protected char[] hits;
    protected char[] errors;
    protected int attempts;
    public Player(char[] hits, char[] errors, int attempts){
        this.hits = hits;
        this.errors = errors;
        this.attempts = attempts;
    }

    @Override
    public int getAttempts() {
        return this.attempts;
    }
}
