import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int guesses=5;
    private int count = 0;
    private String randomWord;
    private String [] gameBoard = {"_", "_", "_", "_", "_"};
    private String [] userGuess = {"_", "_", "_", "_", "_"};
    private String [] randomWordArray = {"_", "_", "_", "_", "_"};
    private ArrayList<String> outputArray = new ArrayList<String>();
    private ArrayList<String> letters = new ArrayList<String>();
    private String [][] guessesArray = {{""}, {""}, {""}, {""}, {""}};

    public Game() throws IOException, InterruptedException{
        try {
            randomWord = Game.generateRandomWord();
            wordToStringArray (randomWord);
        }
        catch (Exception e){
            randomWord = "slope";
        }

    }
    public void run() throws IOException, InterruptedException {
        System.out.println("Welcome to the Wordle console based game! Guess the 5 letter word!");
        makeBoard();
        Scanner scan = new Scanner(System.in); 
        System.out.println("You have "+ guesses + " guesses");
        System.out.println("Guess a word");
        String userInput = scan.nextLine();
        System.out.println();
        
        if (checkValidity(userInput)){
            guesses-=1;
            toStringArray(userInput);
            giveHint();
            addOutput(userInput);
            currentGame(userInput);
            makeBoard();
            
        }
        System.out.println("--------------");
        System.out.println("Guesses left: "+ guesses);
        System.out.println();
        
        while (checkGuess(userInput)) {
            System.out.println("Guess a word");
            userInput = scan.nextLine();
            if (checkValidity(userInput)){
                toStringArray(userInput);
                giveHint();
                System.out.println();
                addOutput(userInput);
                System.out.println();
                currentGame(userInput);
                makeBoard();
                guesses-=1;
            }
            System.out.println("--------------");
            System.out.println("Guesses left: "+ guesses);
            System.out.println();
        }
        scan.close();

    }

    public void makeBoard(){
        for (String [] x: guessesArray){
            for (String y: x){
                System.out.print(y + " ");
            }
            System.out.println();
        }
    }
        
    
    public static String generateRandomWord() throws IOException, InterruptedException{
        Request request = new Request();
        String result = request.find();
        //
        System.out.println(result);
        return result.substring(2, 7);
    }

    public boolean checkGuess(String userInput){
        if (userInput.equals(randomWord)){
            System.out.println("You Win! The word was " + randomWord);
            return false;
        }
        if (guesses == 0) {
            System.out.println("You Lose, you ran out of guesses! The word was " + randomWord);
            return false;
        }
        
        return true;
        
    }

    public void giveHint() {
        for (int i=0; i<5; i++) {
            for (String s: userGuess) {
                if (!letters.contains(s)){
                    if (s.equals(randomWordArray[i]) && !randomWordArray[i].equals(userGuess[i])) {
                        letters.add(s);
                    } 
                }
                            
            }
        }
        System.out.println("Current Hints:");
        for (String l: letters) {
            System.out.println( l +" is a letter in the word, but it is in the wrong spot.");
        }
                    
                    
    }

    public void toStringArray(String input) {
        for (int i=0; i< input.length(); i++) {
            char character = input.charAt(i);
            String characterStr = Character.toString(character);
            userGuess[i] = characterStr;

        }
    }

    public void wordToStringArray (String word) {
        for (int i=0; i< word.length(); i++) {
            char character = word.charAt(i);
            String characterStr = Character.toString(character);
            randomWordArray[i] = characterStr;
        }
        
    }
    
    public boolean checkValidity(String userInput) {
        if (userInput.length() != 5) {
            System.out.println("This is an invalid response. Please check to make sure that you entered a word that is not over or under 5 letters.");
            return false;
        }
        for (String s:userGuess) {
            if (s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5")||s.equals("6")||s.equals("7")||s.equals("8")||s.equals("9")){
            System.out.println("This is an invalid response. Please make sure there are no numbers in your response.");
            return false;
            }
            if (s.equals("!")||s.equals("@")||s.equals("#")||s.equals("$")||s.equals("%")||s.equals("^")||s.equals("&")||s.equals("*")||s.equals("(")||s.equals(")")||s.equals("-")||s.equals("+")||s.equals("=")) {
                System.out.println("This is an invalid response. Please make sure there are no special characters in your response.");
                return false;
            }

        }
        return true;
    }

    public void currentGame (String userInput) {
        System.out.println("Game Board:");
        for (int i=0; i<5; i++) {
            for (String s: userGuess) {
                if (s.equals(randomWordArray[i]) && randomWordArray[i].equals(userGuess[i])) {
                    gameBoard[i] = s;
                }
            }
        }
        guessesArray[count] = gameBoard;    
        gameBoard = new String[] {"_", "_", "_", "_", "_"};
        count++;
    }

    public void addOutput (String userInput) {
        outputArray.add(userInput);
        System.out.println();
        System.out.println("Here are your previous guesses:");
        for (String output:outputArray) {
            System.out.println(output);
        }
        System.out.println();
    }
}
