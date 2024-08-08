import java.io.IOException;
import java.util.Scanner;

public class Game {
    private int guesses=5;
    private String randomWord;
    private String [] gameBoard = {"_", "_", "_", "_", "_"};
    private String [] userGuess = {"_", "_", "_", "_", "_"};
    private String [] randomWordArray = {"_", "_", "_", "_", "_", "_", "_", "_", "_"};
    

    public Game() throws IOException, InterruptedException{
        try {
            randomWord = Game.generateRandomWord();
        }
        catch (Exception e){
            randomWord = "slope";
        }

    }
    public void run() throws IOException, InterruptedException {
        makeBoard(gameBoard);
        Scanner scan = new Scanner(System.in); 
        System.out.println("Guess a word");
        String userInput = scan.nextLine();
        System.out.println();
        
        if (checkValidity(userInput)){
            guesses-=1;
            toStringArray(userInput);
            giveHint();
            
        }
        System.out.println("You have "+ guesses + " guesses");
        System.out.println();
        
        while (checkGuess(userInput)) {
            System.out.println("Guess a word");
            userInput = scan.nextLine();
            if (checkValidity(userInput)){
                toStringArray(userInput);
                giveHint();
                guesses-=1;
            }
            System.out.println("You have "+ guesses + " guesses");
            System.out.println();
            
        }
        scan.close();

    }

    public void makeBoard(String[] gameBoard){
        for (String x: gameBoard){
            System.out.print(x + " ");
            
        }
        System.out.println();
        System.out.println();
    }

    public static String generateRandomWord() throws IOException, InterruptedException{
        Request request = new Request();
        String result = request.find();
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
        System.out.println("x");
        for (String s: userGuess) {
            for (int i=2; i<7; i++) {
                if (s == randomWordArray[i]) {
                    System.out.println ("Hint:" + s + " is a letter in the word, but it is in the wrong spot.");
                }
        }
        
            
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

    


}
