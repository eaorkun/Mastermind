package assignment2;

import java.util.ArrayList;

public class GameBoard
{
   private final Code secretCode;
   private ArrayList<Code> guesses;
   private ArrayList<String> feedbacks;

   public GameBoard(Code secretCode)
   {
      this.secretCode = secretCode;
      guesses = new ArrayList<>();
      feedbacks = new ArrayList<>();
   }

   public Code getSecretCode()
   {
      return secretCode;
   }

   public ArrayList<Code> getGuesses(){
      return guesses;
   }
   public ArrayList<String> getFeedbacks(){
      return feedbacks;
   }

}
