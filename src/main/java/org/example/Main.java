package org.example;

import org.example.game.Game;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
         Game game = new Game(new char[] {}, new char[] {}, 0);
         game.start();
    }
}
