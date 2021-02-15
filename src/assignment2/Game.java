package assignment2;

import java.util.Locale;
import java.util.Scanner;



public class Game
{
   private boolean gameLoop = true;
   private boolean debugMode = false;
   private Scanner gameScanner;

   public Game(boolean debug, Scanner curScanner)
   {
      debugMode = debug;
      gameScanner = curScanner;

   }

   public boolean isGameLoop()
   {
      return gameLoop;
   }

   public void runGame(){
      initializeGame();


      gameLoop = false;
   }

   private void initializeGame()
   {
      printRules();
      Code secretCode = new Code(SecretCodeGenerator.getInstance().getNewSecretCode());
      System.out.print("Generating secret code ... ");
      if(debugMode){
         System.out.println("(for this example the secret code is " + secretCode + ")\n");
      }
      else{
         System.out.println("\n");
      }
      boolean ready = false;
      while (!ready){
         ready = promptReady();
      }
      if(gameLoop){
         
      }
   }

   private boolean promptReady()
   {
      boolean ready = false;
      System.out.println("You have 12 guesses to figure out the secret code or you lose the\n" +
         "game. Are you ready to play? (Y/N):");
      String response = gameScanner.nextLine();
      response = response.toUpperCase();
      if (response.equals("Y") || response.equals("N")){
         ready = true;
         if(response.equals("N")){
            gameLoop = false;
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
