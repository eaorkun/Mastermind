/* EE422C Assignment #2 submission by
 * Eralp Orkun
 * eao789
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 */

package assignment2;

import java.util.Scanner;

/**
 * Game class used to run Mastermind, high in hierarchy
 * EE422C programming assignment #2.
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 *
 * @author Eralp Orkun
 * @version 1.01 02-16-2021
 */

public class Game
{
   private final boolean debugMode;
   private final Scanner gameScanner;
   private boolean gameRepeat = true;
   private boolean playAgain = false;
   private boolean playingGame = true;
   private boolean gameWon = false;
   private GameBoard gameBoard;
   private HumanPlayer player;

   /**
    * Game Constructor, takes in debug flag and a scanner for I/O
    *
    * @param debug      debug mode on/off
    * @param curScanner used for I/O
    */

   public Game(boolean debug, Scanner curScanner)
   {
      debugMode = debug;
      gameScanner = curScanner;
      printRules();
   }

   /**
    * Returns whether gameplay is set to repeat for another game
    */
   public boolean isGameRepeat()
   {
      return gameRepeat;
   }

   /**
    * Complete play-through of the Mastermind game
    */
   public void runGame()
   {
      initializeGame();
      while (playingGame)
      { //while game in progress
         if (gameWon)
         { //if player has won the game
            boolean prompt = true;
            while (prompt)
            { // keep prompting for valid response
               prompt = promptExtraGame();
            }
         }
         else if (player.getNumGuesses() == 0)
         { //if player has lost the game
            System.out.println("Sorry, you are out of guesses. You lose, boo-hoo.");
            System.out.println();
            boolean prompt = true;
            while (prompt)
            {// keep prompting for valid response
               prompt = promptExtraGame();
            }
            playingGame = false;
         }
         else
         { //regular turn based gameplay
            System.out.println("You have " + player.getNumGuesses() + " guesses left.");

            boolean validGuess = false;
            while (!validGuess)
            { //keep prompting for valid response
               System.out.println("What is your next guess?");
               System.out.println("Type in the characters for your guess and press enter.");
               validGuess = promptGuess();
            }
         }
      }
   }

   /**
    * Prompts the user for an I/O guess and processes accordingly
    *
    * @return Returns true if the guess was a valid guess
    */
   private boolean promptGuess()
   {
      boolean validGuess = true;
      System.out.print("Enter guess: ");
      String guess = gameScanner.nextLine();
      guess = guess.trim();
      System.out.println();
      if (guess.length() != GameConfiguration.pegNumber)
      { //if size doesn't match, invalid
         validGuess = false;
         processGuess(guess, validGuess);
         return validGuess;
      }
      else
      {
         for (int i = 0; i < guess.length(); ++i)
         { //iterate for each guess char
            char curChar = guess.charAt(i);
            boolean validChar = false;
            for (int j = 0; j < GameConfiguration.colors.length; ++j)
            { //iterates over all valid colors
               if (curChar == GameConfiguration.colors[j].charAt(0)) //if current char is a color
               {
                  validChar = true;
                  break;
               }
            }
            if (!validChar)
            { //if not valid
               validGuess = false;
            }
         }
         processGuess(guess, validGuess);
         return validGuess;
      }
   }

   /**
    * Processes a guess and updates the game state accordingly
    *
    * @param guess      user input guess
    * @param validGuess boolean condition reflecting if guess is valid
    */
   private void processGuess(String guess, boolean validGuess)
   {
      if (guess.equals("HISTORY"))
      { //check for HISTORY input
         int length = gameBoard.getGuesses().size();
         for (int i = 0; i < length; ++i)
         { //prints all elements in history
            System.out.println(gameBoard.getGuesses().get(i) + "        "
               + gameBoard.getFeedbacks().get(i));
         }
         System.out.println();
         System.out.println("You have " + player.getNumGuesses() + " guesses left.");
      }
      else
      {
         if (validGuess)
         { //if guess is valid
            Code codeGuess = new Code(guess);
            player.setGuess(codeGuess);
            player.setNumGuesses(player.getNumGuesses() - 1);
            String result = gameBoard.getSecretCode().calculateFeedback(player.getGuess());
            gameBoard.getGuesses().add(player.getGuess());
            gameBoard.getFeedbacks().add(result);
            if (player.getNumGuesses() > 0)
            { // Skip if last guess as shown on sample output
               System.out.print(guess + " -> ");
               System.out.print("Result: ");
               System.out.print(result);
               if (gameBoard.getSecretCode().equals(player.getGuess()))
               {
                  System.out.print(" - You win !!");
                  gameWon = true;
               }
               System.out.println();
               System.out.println();
            }
            else if (gameBoard.getSecretCode().equals(player.getGuess()))
            { //if guess matches secret code
               System.out.print(guess + " -> ");
               System.out.print("Result: ");
               System.out.print(result);
               System.out.print(" - You win !!");
               System.out.println();
               System.out.println();
               gameWon = true;
            }
         }
         else
         {
            System.out.print(guess + " -> ");
            System.out.println("INVALID GUESS");
            System.out.println();
         }
      }
   }

   /**
    * Prompts the user through I/O whether they want another Game
    *
    * @return returns true if program should continue prompting
    */
   private boolean promptExtraGame()
   {
      boolean prompt = true;
      System.out.print("Are you ready for another game (Y/N) : ");
      String response = gameScanner.nextLine();
      response = response.toUpperCase();
      response = response.trim();
      if (response.equals("Y") || response.equals("N"))
      { //if valid response
         prompt = false;
         gameRepeat = response.equals("Y");
         playAgain = response.equals("Y");
         gameWon = false;
         playingGame = false;
      }
      return prompt;
   }

   /**
    * Initializes new unique game of Mastermind, run every new play-through
    */
   private void initializeGame()
   {
      playingGame = true;
      //printGameConfig(); Used for Debugging Purposes
      Code secretCode = new Code(SecretCodeGenerator.getInstance().getNewSecretCode());
      boolean ready = false;
      while (!ready)
      { //while invalid response
         ready = promptReady();
      }
      if (playingGame)
      { // if game is in progress
         System.out.print("Generating secret code ...");
         if (debugMode)
         { //if debugMode is on
            System.out.println(" (for this example the secret code is " + secretCode + ")\n");
         }
         else
         {
            System.out.println("\n");
         }
         gameBoard = new GameBoard(secretCode);
         player = new HumanPlayer();
      }
   }

   /**
    * Prints Game Configuration settings to the screen
    */
   private void printGameConfig()
   {
      System.out.println("Game Configurations:");
      System.out.println("Total guesses - " + GameConfiguration.guessNumber);
      System.out.print("Available Colors - ");
      for (int i = 0; i < GameConfiguration.colors.length; ++i)
      {
         System.out.print(GameConfiguration.colors[i] + " ");
      }
      System.out.println();
      System.out.println("Total Pegs - " + GameConfiguration.pegNumber);
      System.out.println();
   }

   /**
    * Prompts the user on if they are ready to start the game
    *
    * @return returns true if further prompting is needed
    */
   private boolean promptReady()
   {
      boolean ready = false;
      String response;
      if (playAgain)
      {
         response = "Y";
      }
      else
      {
         System.out.print("You have " + GameConfiguration.guessNumber
            + " guesses to figure out the secret code or you lose the " +
            "game. Are you ready to play? (Y/N): ");
         response = gameScanner.nextLine();
      }
      response = response.toUpperCase();
      response = response.trim();
      System.out.println();
      if (response.equals("Y") || response.equals("N"))
      { //if valid response
         ready = true;
         if (response.equals("N"))
         { // if No was the response
            playingGame = false;
            gameRepeat = false;
         }
      }
      return ready;
   }

   /**
    * Prints the rules of Mastermind to the console.
    */
   private static void printRules()
   {
      System.out.println("Welcome to Mastermind. Here are the rules.\n\n" +
         "This is a text version of the classic board game Mastermind.\n\n" +
         "The computer will think of a secret code. The code consists of 4 " +
         "colored pegs. The pegs MUST be one of six colors: blue, green, " +
         "orange, purple, red, or yellow. A color may appear more than once in " +
         "the code. You try to guess what colored pegs are in the code and " +
         "what order they are in. After you make a valid guess the result " +
         "(feedback) will be displayed.\n\n" +
         "The result consists of a black peg for each peg you have guessed " +
         "exactly correct (color and position) in your guess. For each peg in " +
         "the guess that is the correct color, but is out of position, you get " +
         "a white peg. For each peg, which is fully incorrect, you get no " +
         "feedback.\n\n" +
         "Only the first letter of the color is displayed. B for Blue, R for " +
         "Red, and so forth. When entering guesses you only need to enter the " +
         "first character of each color as a capital letter.\n");
   }
}
