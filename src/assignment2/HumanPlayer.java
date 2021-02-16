package assignment2;

public class HumanPlayer
{
   private Code guess;

   private int numGuesses;
   public HumanPlayer(){
      numGuesses = GameConfiguration.guessNumber;
   }
   public int getGuesses()
   {
      return numGuesses;
   }

   public void setGuess(Code guess)
   {
      this.guess = guess;
   }

   public void setGuesses(int numGuesses)
   {
      this.numGuesses = numGuesses;
   }

   public Code getGuess()
   {
      return guess;
   }
}
