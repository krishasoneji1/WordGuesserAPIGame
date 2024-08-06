import java.util.Scanner;
import java.util.ArrayList;  
import java.util.List;  

public class Main {
    public static void main (String[] args) {
        String [] gameBoard = {"_", "_", "_", "_", "_"};
        makeBoard(gameBoard);

        String [] userGuess = {"_", "_", "_", "_", "_"};

        Scanner scan = new Scanner(System.in); 
        System.out.println("Guess a word");
        String userInput = scan.nextLine();
        toStringArray(userInput, userGuess);
        checkValidity(userInput, userGuess);
        while (checkGuess(userInput)){
            System.out.println("Guess a word");
            userInput = scan.nextLine();
            toStringArray(userInput, userGuess);
            checkValidity(userInput, userGuess);
        }
        scan.close();


    }

    public static void makeBoard(String[] gameBoard){
        for (String x: gameBoard){
            System.out.print(x + " ");
            
        }
        System.out.println();
        System.out.println();
    }

    public static String generateRandomWord(){
        return "apple";
    }

    public static boolean checkGuess(String userInput){
        if (userInput.equals(generateRandomWord())){
            System.out.println("You Win! The word was " + generateRandomWord());
            return false;
        }
        else {
            return true;
        }

    }

    public static void toStringArray(String userInput, String [] array) {
        for (int i=0; i< userInput.length(); i++) {
            char character = userInput.charAt(i);
            String characterStr = Character.toString(character);
            array[i] = characterStr;
        }
    }
    
    public static void checkValidity(String userInput, String[] array) {
        if (userInput.length() != 5) {
            System.out.println("This is an invalid response. Please check to make sure that you entered a word that is not over or under 5 letters.");
        }
        for (String s:array) {
            if (s.equals("1")||s.equals("2")||s.equals("3")||s.equals("4")||s.equals("5")||s.equals("6")||s.equals("7")||s.equals("8")||s.equals("9")){
            System.out.println("This is an invalid response. Please make sure there are no numbers in your response.");
            }
            if (s.equals("!")||s.equals("@")||s.equals("#")||s.equals("$")||s.equals("%")||s.equals("^")||s.equals("&")||s.equals("*")||s.equals("(")||s.equals(")")||s.equals("-")||s.equals("_")||s.equals("+")||s.equals("=")) {
                System.out.println("This is an invalid response. Please make sure there are no special characters in your response.");
                }

        }
    }
}
    

