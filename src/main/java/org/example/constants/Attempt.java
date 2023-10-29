package org.example.constants;

public enum Attempt {
    SIX(6);

    private final int number;
    Attempt(int number){
        this.number = number;
    }

    public int getNumber(){
        return this.number;
    }
}
