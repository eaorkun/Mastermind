package assignment2;

public class GameBoard
{
   private final Code secretCode;

   public GameBoard(Code secretCode)
   {
      this.secretCode = secretCode;
   }

   public Code getSecretCode()
   {
      return secretCode;
   }
}
