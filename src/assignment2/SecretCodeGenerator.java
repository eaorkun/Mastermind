/* EE422C Assignment #2 submission by
 * Eralp Orkun
 * eao789
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 */

package assignment2;

import java.util.Random;

public class SecretCodeGenerator
{
   private final Random randomGenerator;
   private static final SecretCodeGenerator instance = new SecretCodeGenerator();


   //Do not create your own SecretCodeGenerator Objects
   private SecretCodeGenerator()
   {
      randomGenerator = new Random();
   }

   //Use this method for each game only once.
   //The correct way to call this is: SecretCodeGenerator.getInstance().getNewSecretCode()
   public String getNewSecretCode()
   {
      String result = "";
      int index, numberOfPegs = GameConfiguration.pegNumber;
      String[] colors = GameConfiguration.colors;
      for (int i = 0; i < numberOfPegs; i++)
      {
         index = randomGenerator.nextInt(colors.length);
         result += colors[index];
      }
      return result;
   }

   public static SecretCodeGenerator getInstance()
   {
      return instance;
   }
}
