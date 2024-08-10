//imports
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private int guesses; //current number of guesses left for user
    private int numValidGuesses; //number of valid values inputted by the user
    private String randomWord; //holds the randomWord as a string
    private String [] currentGuessStatus; //resets after every guess, holds the current user guess, with correct guesses in the correct spot, and unguessed letters represented with an underscore
    private String [] userGuess; //holds the user guess as an array to make it easier to compare with the random word
    private String [] randomWordArray; //holds the random word as an array to make it easier to compare
    private ArrayList<String> previousGuesses; //holds all previous guesses as strings
    private ArrayList<String> correctLettersGuessed; //holds previously guessed letters
    private String [][] guessesArray; //holds the currentGuessStatus array, before it is reset, each turn is stored as an array within the array to present to the user

    //constructor to initialize variables
    public Game() throws IOException, InterruptedException {

        randomWord = Game.generateRandomWord(); //generates random words from the requests class
        //initializing all variables with values
        guesses=5;
        numValidGuesses = 1;
        currentGuessStatus = new String[]{"_", "_", "_", "_", "_"};
        userGuess = new String[]{"_", "_", "_", "_", "_"};
        randomWordArray = new String[] {"_", "_", "_", "_", "_"};
        previousGuesses = new ArrayList<String>();
        correctLettersGuessed = new ArrayList<String>();
        guessesArray = new String[][]{{""}, {""}, {""}, {""}, {""}};
    }

    //run function which runs all methods
    public void run() throws IOException, InterruptedException {

        Scanner scan = new Scanner(System.in); 
        setRandomWordArray (randomWord); //making random word into array
        System.out.println("Welcome to the Wordle console based game! Guess the 5 letter word!");
        makeBoard(); //printing board with 5 underscores
        //first time asking for user input
        System.out.println("Guesses left: "+ guesses);
        System.out.println("Guess a word");
        String userInput = scan.nextLine();
        System.out.println();

        //if the user enters a valid guess, then run all these functions, which print the words guessed, the current game board, and gives the user a hint, see original function for function description
        if (checkValidity(userInput)){
            guesses-=1;
            setUserGuessArray(userInput);
            giveHint();
            addOutput(userInput);
            setCurrentGame(userInput);
            makeBoard();
            
        }
        System.out.println("--------------"); //for organization
        System.out.println("Guesses left: "+ guesses);
        System.out.println();
        
        //putting user input into a while loop, which checks if the user has won or not before asking for user input again
        while (checkGuess(userInput)) {
            System.out.println("Guess a word");
            userInput = scan.nextLine();
            System.out.println();

            // same if statement as above
            if (checkValidity(userInput)){
                setUserGuessArray(userInput);
                giveHint();
                System.out.println(); //for organization
                addOutput(userInput);
                System.out.println();
                setCurrentGame(userInput);
                makeBoard();
                guesses-=1;

            }
            System.out.println("--------------");
            System.out.println("Guesses left: "+ guesses);
            System.out.println();
        }
        scan.close(); //close the scanner
    }

    // iterates through the guessesArray and neatly prints out each guessed word with underscores to represent unguessed letters
    public void makeBoard(){

        for (String [] x: guessesArray){

            for (String y: x){
                System.out.print(y + " ");

            }
            System.out.println();
        }
    }
        
    //generates random word by calling the Requests class
    public static String generateRandomWord() throws IOException, InterruptedException{

        Request request = new Request();
        String result = request.find();
        //System.out.println(result.substring(2, 7)); //allows testing by printing out the random word 
        return result.substring(2, 7); //returns a substring so that the brackets and quotes are not included in the word
    }

    //checks if the user won or not, returns false to end the game which occurs either when the user guesses the word, or they run out of guesses
    public boolean checkGuess(String userInput){

        if (userInput.equals(randomWord)){ //checks to see if the user types the random word
            System.out.println("You Win! The word was " + randomWord);
            return false;
            
        }
        if (guesses == 0) { //checks if the user ran out of guesses
            System.out.println("You Lose, you ran out of guesses! The word was " + randomWord);
            return false;
        }
        return true; //returns true to continue game unless above conditions are true
    }

    //if the user has guessed a word, and a letter is in the word but is in the wrong spot, it will let the user know in a hint
    public void giveHint() {

        for (int i=0; i<5; i++) {
            for (String s: userGuess) {
                if (!correctLettersGuessed.contains(s)){ //checks to see if the user has guessed this letter already 
                    if (s.equals(randomWordArray[i]) && !randomWordArray[i].equals(userGuess[i])) { //checks that the letter is in the word, but in the wrong spot
                        correctLettersGuessed.add(s); //adds the letter to the list of letters guessed by the user that are correct, but in the wrong spot
                    } 
                }
                if (s.equals(randomWordArray[i]) && randomWordArray[i].equals(userGuess[i])){ //checks if the letter is in the wrong spot
                    correctLettersGuessed.remove(s); //removes letter from list
                }           
            }
        }
        System.out.println("Current Hints:");
        for (String l: correctLettersGuessed) { //iterates through all the letters guessed, but are in the wrong spot
            System.out.println( l +" is a letter in the word, but it is in the wrong spot.");
        }              
    }

    //makes the user guess into an array by iterating through the string and adding it to the array
    public void setUserGuessArray(String input) {

        for (int i=0; i< input.length(); i++) {
            char character = input.charAt(i);
            String characterStr = Character.toString(character);
            userGuess[i] = characterStr;

        }
    }

    //makes the random word into an array by iterating through the string and adding it to the array
    public void setRandomWordArray (String word) {

        for (int i=0; i< word.length(); i++) {
            char character = word.charAt(i);
            String characterStr = Character.toString(character);
            randomWordArray[i] = characterStr;
        }
    }
    
    //checks for words that are not five letters, have numbers, or special characters, returns true only if the word is valid
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

    //creates the game board by iterating through the user guess, and replacing underscores with correct letters that are in the correct spot
    public void setCurrentGame(String userInput) {

        System.out.println("Game Board:");
        for (int i=0; i<5; i++) {
            for (String s: userGuess) {
                if (s.equals(randomWordArray[i]) && randomWordArray[i].equals(userGuess[i])) { //checks if the index of the letter in the guess and the random word are the same
                    currentGuessStatus[i] = s; //adds the letter to the array for the current guess
                }
            }
        }
        guessesArray[numValidGuesses-1] = currentGuessStatus; //adds the current guess array to a 2D array, in the correct spot by using a counter (initialized at 1) which increases by 1 each turn  
        currentGuessStatus = new String[] {"_", "_", "_", "_", "_"}; //resets the current guess array
        numValidGuesses++; //increases the counter by 1
    }

    //adds the current output to the previous guesses array, then iterates through the array to print the guesses out
    public void addOutput (String userInput) {

        previousGuesses.add(userInput);
        System.out.println();
        System.out.println("Here are your previous guesses:");
        for (String output:previousGuesses) {
            System.out.println(output);
        }
        System.out.println();
    }
}