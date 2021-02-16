/* EE422C Assignment #2 submission by
 * Eralp Orkun
 * eao789
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 */

package assignment2;

/**
 * Contains information relating to the current player of Mastermind
 * keeps track of number of guesses and current guessed code
 *
 * @author Eralp Orkun
 * @version 1.01 02-16-2021
 */
public class HumanPlayer
{
   private Code guess;

   private int numGuesses;

   /**
    * Constructor for the human player of Mastermind
    */
   public HumanPlayer()
   {
      numGuesses = GameConfiguration.guessNumber;
   }

   /**
    * Gets remaining number of guesses for player
    *
    * @return int of remaining guesses
    */
   public int getNumGuesses()
   {
      return numGuesses;
   }

   /**
    * Sets num of remaining guesses for player
    *
    * @param numGuesses int of remaining guesses
    */
   public void setNumGuesses(int numGuesses)
   {
      this.numGuesses = numGuesses;
   }

   /**
    * Gets current guess of player
    *
    * @return Code object containing current player guess
    */
   public Code getGuess()
   {
      return guess;
   }

   /**
    * Sets current guess for player
    *
    * @param guess Code object containing current player guess
    */
   public void setGuess(Code guess)
   {
      this.guess = guess;
   }
}
