import assignment2.*;

import java.util.Scanner;

public class Driver
{

   public static void main(String[] args)
   {
      boolean debug = false;
      if(args.length > 0){
         if (args[0].equals("1")){
            debug = true;
         }
      }
      Scanner curScanner = new Scanner(System.in);
      Game curGame = new Game(debug, curScanner);
      while (curGame.isGameRepeat()){
         System.out.println("Playing Game");
         curGame.runGame();
      }
   }
}
