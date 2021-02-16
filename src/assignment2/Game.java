package assignment2;

import java.util.Scanner;



public class Game
{
   private boolean gameRepeat = true;
   private boolean playingGame = true;
   private boolean debugMode;
   private boolean gameWon = false;
   private Scanner gameScanner;
   private GameBoard gameBoard;
   private HumanPlayer player;


   public Game(boolean debug, Scanner curScanner)
   {
      debugMode = debug;
      gameScanner = curScanner;

   }

   public boolean isGameRepeat()
   {
      return gameRepeat;
   }

   public void runGame(){
      initializeGame();
      while (playingGame){
         if(gameWon){ //won
            boolean prompt = true;
            while(prompt){
               prompt = promptExtraGame();
            }
         }
         else if(player.getNumGuesses() == 0){ //lost
            System.out.println("(Sorry, you are out of guesses. You lose, boo-hoo.)");
            boolean prompt = true;
            while(prompt){
               prompt = promptExtraGame();
            }
            playingGame = false;
         }
         else{ //regular turn based gameplay
            System.out.println("You have " + player.getNumGuesses() + " guesses left.");

            boolean validGuess = false;
            while(!validGuess){
               System.out.println("What is your next guess?");
               System.out.println("Type in the characters for your guess and press enter.");
               validGuess = promptGuess();
            }
         }
      }

   }

   private boolean promptGuess()
   {
      boolean validGuess = true;
      System.out.print("Enter guess: ");
      String guess = gameScanner.nextLine();
      guess = guess.trim();
      System.out.println();
      if (guess.length() != GameConfiguration.pegNumber){
         validGuess = false;
         processGuess(guess, validGuess);
         return validGuess;
      }
      else{
         for(int i =0; i<guess.length(); ++i){
            char curChar = guess.charAt(i);
            boolean validChar = false;
            for (int j = 0; j < GameConfiguration.colors.length; ++j){
               if (curChar == GameConfiguration.colors[j].charAt(0))
               {
                  validChar = true;
                  break;
               }
            }
            if(!validChar){
               validGuess = false;
            }
         }
         processGuess(guess, validGuess);
         return validGuess;
      }
   }

   private void processGuess(String guess, boolean validGuess)
   {
      if(guess.equals("HISTORY")){
         int length = gameBoard.getGuesses().size();
         for(int i = 0; i < length; ++i){
            System.out.println(gameBoard.getGuesses().get(i) + "\t\t"
               + gameBoard.getFeedbacks().get(i));
         }
         System.out.println();
      }
      else {
         System.out.print(guess + " -> ");
         if(validGuess){
            Code codeGuess = new Code(guess);
            player.setGuess(codeGuess);
            player.setNumGuesses(player.getNumGuesses()-1);
            System.out.print("Result: ");
            String result = gameBoard.getSecretCode().calculateFeedback(player.getGuess());
            gameBoard.getGuesses().add(player.getGuess());
            gameBoard.getFeedbacks().add(result);
            System.out.print(result);
            if(gameBoard.getSecretCode().equals(player.getGuess())){
               System.out.print(" - You win !!");
               gameWon = true;
            }
            System.out.println();
         }
         else{
            System.out.println("INVALID GUESS");
         }
         System.out.println();
      }
   }

   private boolean promptExtraGame()
   {
      boolean prompt = true;
      System.out.print("Are you ready for another game (Y/N) : ");
      String response = gameScanner.nextLine();
      response = response.toUpperCase();
      response = response.trim();
      if (response.equals("Y") || response.equals("N")){
         prompt = false;
         gameRepeat = response.equals("Y");
         gameWon = false;
         playingGame = false;
      }
      return prompt;
   }

   private void initializeGame()
   {
      playingGame = true;
      printRules();
      Code secretCode = new Code(SecretCodeGenerator.getInstance().getNewSecretCode());
      boolean ready = false;
      while (!ready){
         ready = promptReady();
      }
      System.out.print("Generating secret code ... ");
      if(debugMode){
         System.out.println("(for this example the secret code is " + secretCode + ")\n");
      }
      else{
         System.out.println("\n");
      }

      if(playingGame){
         gameBoard = new GameBoard(secretCode);
         player = new HumanPlayer();
      }
   }

   private boolean promptReady()
   {
      boolean ready = false;
      System.out.print("You have 12 guesses to figure out the secret code or you lose the\n" +
         "game. Are you ready to play? (Y/N): ");
      String response = gameScanner.nextLine();
      response = response.toUpperCase();
      response = response.trim();
      System.out.println();
      if (response.equals("Y") || response.equals("N")){
         ready = true;
         if(response.equals("N")){
            playingGame = false;
            gameRepeat = false;
            System.out.println("Exiting Game");
         }
      }
      return ready;
   }

   private static void printRules()
   {
      System.out.println("Welcome to Mastermind. Here are the rules.\n\n" +
         "This is a text version of the classic board game Mastermind.\n\n" +
         "The computer will think of a secret code. The code consists of 4\n" +
         "colored pegs. The pegs MUST be one of six colors: blue, green,\n" +
         "orange, purple, red, or yellow. A color may appear more than once in\n" +
         "the code. You try to guess what colored pegs are in the code and\n" +
         "what order they are in. After you make a valid guess the result\n" +
         "(feedback) will be displayed.\n\n" +
         "The result consists of a black peg for each peg you have guessed\n" +
         "exactly correct (color and position) in your guess. For each peg in\n" +
         "the guess that is the correct color, but is out of position, you get\n" +
         "a white peg. For each peg, which is fully incorrect, you get no\n" +
         "feedback.\n\n" +
         "Only the first letter of the color is displayed. B for Blue, R for\n" +
         "Red, and so forth. When entering guesses you only need to enter the\n" +
         "first character of each color as a capital letter.\n");
   }
}
