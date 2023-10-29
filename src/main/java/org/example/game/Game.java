package org.example.game;

import org.example.constants.Attempt;
import org.example.constants.Gibbet;
import org.example.guy.body.Body;
import org.example.player.Player;
import org.example.random.words.RandomWord;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Game extends Player implements GameInterface{
    private String word;
    private char[] formedWordByHits;
    public Game(char[] hits, char[] errors, int attempts){
        super(hits, errors, attempts);
        this.word = getWord();
    }
    @Override
    public void start() {
        this.formedWordByHits = getBlankChars(this.word.length());
        run();
    }

    private String getWord(){
        RandomWord randomWord = new RandomWord();
        return randomWord.getRandomWord();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private boolean wordContainsLetter(char letter){
        String word = this.word.toLowerCase();
        return word.indexOf(Character.toLowerCase(letter)) >= 0;
    }

    private List<Integer> getOccurrences(char letter){
        String word = this.word.toLowerCase();
        return IntStream
                .iterate(word.indexOf(Character.toLowerCase(letter)), index -> index >= 0, index -> word.indexOf(Character.toLowerCase(letter), index + 1))
                .boxed()
                .collect(Collectors.toList());
    }

    private void addLetterToHits(char letter){
        this.hits = Arrays.copyOf(this.hits, this.hits.length + 1);
        this.hits[this.hits.length - 1] = letter;
    }

    private void addLetterToEmptyWord(char letter){
        List<Integer> indexes = getOccurrences(letter);
        for (int index : indexes){
            this.formedWordByHits[index] = letter;
        }
    }

    private void addLetterToErrors(char letter){
        this.errors = Arrays.copyOf(this.errors, this.errors.length + 1);
        this.errors[this.errors.length - 1] = letter;
    }

    private char[] getBlankChars(int size){
        char[] blankChars = new char[size];
        for (int i = 0; i < this.word.length(); i++){
            blankChars[i] += ' ';
        }
        return blankChars;
    }

    private void printSequence(char separator, char[] sequence){
        String sep = String.valueOf(separator);
        String joined = CharBuffer.wrap(sequence).chars()
                .mapToObj(intValue -> String.valueOf((char) intValue))
                .collect(Collectors.joining(sep));
        System.out.println(joined);
    }

    private void printHits(){
        char[] blankChars = getBlankChars(this.word.length() + 1);
        printSequence(' ', this.formedWordByHits);
        printSequence('_', blankChars);
    }

    private void printErrors(){
        printSequence(' ', this.errors);
    }

    private void correctAnswer(char input){
        addLetterToHits(input);
        addLetterToEmptyWord(input);
    }

    private void wrongAnswer(char input){
        this.attempts += 1;
        addLetterToErrors(input);
    }

    private char userInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a letter: ");
        return scanner.next().charAt(0);
    }

    private void printGibbetStatus(){
        switch (this.attempts){
            case 0:
                System.out.println(Gibbet.GIBBET);
                break;
            case 1:
                System.out.println(Body.HEAD.getPhase());
                break;
            case 2:
                System.out.println(Body.HEAD_AND_RIGHT_ARM.getPhase());
                break;
            case 3:
                System.out.println(Body.HEAD_RIGHT_ARM_AND_LEFT_ARM.getPhase());
                break;
            case 4:
                System.out.println(Body.HEAD_RIGHT_ARM_LEFT_ARM_AND_STEM.getPhase());
                break;
            case 5:
                System.out.println(Body.HEAD_RIGHT_ARM_LEFT_ARM_STEM_AND_RIGHT_LEG.getPhase());
                break;
            case 6:
                System.out.println(Body.HEAD_RIGHT_ARM_LEFT_ARM_STEM_RIGHT_LEG_AND_LEFT_LEG.getPhase());
                break;
        }
    }

    private void printGame(){
        System.out.println("\n\t\tJogo da Forca");
        printErrors();
        System.out.println("\n");
        printGibbetStatus();
        System.out.println("\n");
        printHits();
        System.out.println("\n");
    }

    private void verifyAnswer(){
        char input = Character.toLowerCase(userInput());
        if (wordContainsLetter(input)){
            correctAnswer(input);
        } else {
            wrongAnswer(input);
        }
    }

    private void endOfGame(){
        if (win()){
            end("WON");
        } else if (lost()){
            end("LOSE");
        }
    }

    private boolean win(){
        String word = this.word.toLowerCase();
        return Arrays.equals(word.toCharArray(), this.formedWordByHits);
    }

    private boolean lost(){
        return this.attempts == Attempt.SIX.getNumber();
    }

    @Override
    public void end(String status) {
        System.out.println("You " + status + '!');
        System.out.println("\nThe word is: " + this.word);
    }

    private void run(){
        while (!win() && !lost()){
            printGame();
            verifyAnswer();
            clearScreen();
        }
        endOfGame();
    }
}
