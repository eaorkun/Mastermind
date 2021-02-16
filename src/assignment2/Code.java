/* EE422C Assignment #2 submission by
 * Eralp Orkun
 * eao789
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 */

package assignment2;

/**
 * Code class containing a Mastermind Code Sequence
 *
 * @author Eralp Orkun
 * @version 1.01 02-16-2021
 */
public class Code
{
   private final String code;

   /**
    * Code constructor
    *
    * @param code takes a code as a String
    */
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

   /**
    * Calculates the feedback for a given player code (a guess)
    * Should only be used from secret code
    *
    * @param guess input code
    * @return string containing the feedback
    */
   public String calculateFeedback(Code guess)
   {
      int blackPegs = 0;
      int whitePegs = 0;
      char[] guessString = guess.getCode().toCharArray();
      char[] secretString = getCode().toCharArray();
      for (int i = 0; i < GameConfiguration.pegNumber; ++i)
      { //Iterate checking black pegs
         if (guessString[i] == secretString[i])
         { //if position and element match
            guessString[i] = '-';
            secretString[i] = '-';
            ++blackPegs;
         }
      }
      for (int i = 0; i < GameConfiguration.pegNumber; ++i)
      { //iterate checking white pegs
         if (guessString[i] != '-')
         { //if not crossed out
            for (int j = 0; j < GameConfiguration.pegNumber; ++j)
            { //check all elements in secret code
               if (guessString[i] == secretString[j])
               { // if elements match
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

   /**
    * Gets the code as a string
    *
    * @return string containing code
    */
   private String getCode()
   {
      return code;
   }
}
