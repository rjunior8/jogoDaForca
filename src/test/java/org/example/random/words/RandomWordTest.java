package org.example.random.words;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class RandomWordTest {

    @org.junit.jupiter.api.Test
    void getRandomWord() {
        ArrayList<String> words = new ArrayList<String>(6);
        words.add("Banana");
        words.add("Maça");
        words.add("Empresa");
        words.add("Cavalo");
        words.add("Cachorro");
        words.add("Relogio");
        RandomWord randomWord = new RandomWord();
        String selectedWord = randomWord.getRandomWord();
        assertTrue(words.contains(selectedWord));
    }
}
