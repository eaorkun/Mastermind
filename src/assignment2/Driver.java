package assignment2;/* EE422C Assignment #2 submission by
 * Eralp Orkun
 * eao789
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 */

import java.util.Scanner;

/**
 * assignment2.Driver class used to run Mastermind, no instance of class required
 * EE422C programming assignment #2.
 * Lab Section: Unique #17110, (5-6:30pm Thursday)
 *
 * @author Eralp Orkun
 * @version 1.01 02-16-2021
 */

public class Driver
{

   public static void main(String[] args)
   {
      boolean debug = false;
      if (args.length > 0)
      { //checks for arguments
         if (args[0].equals("1"))
         { // debug argument
            debug = true;
         }
      }
      Scanner curScanner = new Scanner(System.in);
      Game curGame = new Game(debug, curScanner);
      while (curGame.isGameRepeat())
      { //repeat full gameplays
         curGame.runGame();
      }
   }
}
