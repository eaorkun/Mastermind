package assignment2;

import java.util.Objects;

public class Code
{
   private String code;
   public Code(String code)
   {
      this.code = code;
   }

   @Override
   public String toString()
   {
      return code;
   }

   @Override
   public boolean equals(Object o)
   {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Code code1 = (Code) o;
      return code.equals(code1.code);
   }

   public String calculateFeedback(Code guess)
   {
      int blackPegs = 0;
      int whitePegs = 0;
      char[] guessString = guess.getCode().toCharArray();
      char[] secretString = getCode().toCharArray();
      for (int i = 0; i < GameConfiguration.pegNumber; ++i){
         if(guessString[i] == secretString[i]){
            guessString[i] = '-';
            secretString[i] = '-';
            ++blackPegs;
         }
      }
      for (int i = 0; i < GameConfiguration.pegNumber; ++i){
         if(guessString[i] != '-'){
            for(int j = 0; j < GameConfiguration.pegNumber; ++j){
               if(guessString[i] == secretString[j]){
                  guessString[i] = '-';
                  secretString[j] = '-';
                  ++whitePegs;
                  break;
               }
            }
         }
      }
      return blackPegs + "B_" + whitePegs + "W";
   }

   private String getCode()
   {
      return code;
   }
}
