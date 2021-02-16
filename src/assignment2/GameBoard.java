/* EE422C Assignment #2 submission by
 * Eralp Orkun
 * eao789
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 */

package assignment2;

import java.util.ArrayList;

/**
 * GameBoard for the game Mastermind. Contains HISTORY data for Game class
 *
 * @author Eralp Orkun
 * @version 1.01 02-16-2021
 */
public class GameBoard
{
   private final Code secretCode;
   private final ArrayList<Code> guesses;
   private final ArrayList<String> feedbacks;

   /**
    * Constructor for the gameBoard
    *
    * @param secretCode the secretCode for the given gameBoard
    */
   public GameBoard(Code secretCode)
   {
      this.secretCode = secretCode;
      guesses = new ArrayList<>();
      feedbacks = new ArrayList<>();
   }

   /**
    * Gets the secret code for the gameBoard
    *
    * @return returns a Code object containing the secret code
    */
   public Code getSecretCode()
   {
      return secretCode;
   }

   /**
    * Gets a list of past guesses
    *
    * @return ArrayList of past Code objects that were guessed
    */
   public ArrayList<Code> getGuesses()
   {
      return guesses;
   }

   /**
    * Gets a list of past feedbacks
    *
    * @return ArrayList of past feedback Strings in response to corresponding guesses
    */
   public ArrayList<String> getFeedbacks()
   {
      return feedbacks;
   }

}
