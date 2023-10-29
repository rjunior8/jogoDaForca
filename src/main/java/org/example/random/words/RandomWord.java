package org.example.random.words;

import java.util.*;

public class RandomWord {
    private String[] getWords(){
        return new String[]{"Banana", "Maça", "Empresa", "Cavalo", "Cachorro", "Relogio"};
    }

    public String getRandomWord(){
        Random generator = new Random();
        String[] words = this.getWords();
        Object randomWord = words[generator.nextInt(words.length)];
        return randomWord.toString();
    }
}
