/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangemaker.java
 * Purpose    :  This program determines how much of each coin is necessary to create the desired amount of change.
 * @author    :  Ethan Boone
 * Date       :  2018-05-03
 * Description:  The program utilizes the algorithm that we derived in class in order to optimize the number of coins necessary
 * 				 to create the desired amount of change.
 * Notes      :  Paired with the Tuple.java and DynamicChangemakerTestHarness.java files.
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:   Reason for change or modification
 *  -----  ----------  ------------   ---------------------------------------------------------------------
 *  1.0.0  2018-04-19  B.J. Johnson   Initial release; stolen blatently from Professor Don Murphy with his
 *                                    express permission and blessing; just added this comment block
 *  1.1.0  2018-05-03  Ethan Boone    Coded the algorithm, added java docs, and tested to ensure it passed
 *  								  all of the tests for the DynamicChangemakerTestHarness.java
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
import java.util.Arrays;
public class DynamicChangemaker {

  /**
   *  This main method handles many of the inputs when testing the DynamicChangemaker class. The make
   *  change with dynamic programming function also does some error catching of its own though.
   */
    public static void main(String[] args) {

        if (args.length != 2) {
            printError();
            return;
        }
        
        try {

            String[] denominationStrings = args[0].split(",");
            int[] denominations = new int[denominationStrings.length];

            for (int i = 0; i < denominations.length; i++) {
                denominations[i] = Integer.parseInt(denominationStrings[i]);
                if (denominations[i] <= 0) {
                    System.out.println("It is impossible to use the algorithm unless the the denominations are larger than 0...\n");
                    return;
                }

                for (int j = 0; j < i; j++) {
                    if (denominations[j] == denominations[i]) {
                        System.out.println("It is impossible to use the algorithm unless the denominations do not repeat...\n");
                        return;
                    }
                }
            }

            int inputAmount = Integer.parseInt(args[1]);
            if (inputAmount < 0) {
                printError();
                return;
            }



            Tuple desiredChange = makeChangeWithDynamicProgramming(denominations, inputAmount);
            if (desiredChange.isImpossible()) {
                System.out.println("It is impossible to make " + inputAmount + " cents with those denominations.");
            } else {
                int coinTotal = desiredChange.total();
                System.out.println(inputAmount + " cents can be made with " + coinTotal + " coin" +
                        useCorrectGrammar(coinTotal) + " as follows:");

                for (int i = 0; i < denominations.length; i++) {
                    int coinCount = desiredChange.getElement(i);
                    System.out.println("- "  + coinCount + " " + denominations[i] + "-cent coin" +
                            useCorrectGrammar(coinCount));
                }
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Denominations and amount must all be integers.\n");
            printError();
        }
        
    }
    
    
    /**
     *  This error is printed if the use inputs something incorrectly into the command line.
     */
     private static void printError() {
         System.out.println("Error: It is impossible to use the arguments that you have entered.");
     }

     /**
      *  Function that makes sure you use the correct grammar when addressing one or many coin(s)
      *  @param count is the number of coins
      *  @return adds an 's' at the end if it needs to, if it doesn't then it doesn't.
      */
     private static String useCorrectGrammar(int count) {
         return count == 1 ? "" : "s";
     }
    
    

    /**
     * makeChangeWithDynamicProgramming takes in two arguments: 1) A integer array of deonominations and 2) an integer amount
     * The function optimizes the number of coins necessary to create the desired amount.
     *
     * @param denominations  		the array of coin denominations depending on the country
     * @param desiredChange         the desired amount of change
     *
     * @return the optimized number of each coin that it would take to create the desired change.
     */
    public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int desiredChange) {
    
      if ( denominations.length <= 0 || desiredChange <= 0 ) {
    	  return Tuple.IMPOSSIBLE;
      }
      
      for ( int i = 0; i < denominations.length; i++ ) {
    	  if ( denominations[i] <= 0 ) {
    		  return Tuple.IMPOSSIBLE;
    	  }
    	  for (int j = 0; j < i; j++) {
              if (denominations[j] == denominations[i]) {
                  return Tuple.IMPOSSIBLE;
              }
          }
    	  
      }  
      
      Tuple[][] table = new Tuple[denominations.length][desiredChange + 1];
      int temp = 0;
      for( int i = 0; i < denominations.length; i++){
        temp = 0;
        for( int j = 0; j<desiredChange+1; j++){
          if( j == 0 ){
            table[i][j]=new Tuple(denominations.length);
          }
          else{
            if( j >= denominations[i] ){
              table[i][j] = new Tuple(denominations.length);
              table[i][j].setElement(i,1);
              temp = j - denominations[i];
              if( (table[i][temp].equals(Tuple.IMPOSSIBLE)) ){
                table[i][j] = Tuple.IMPOSSIBLE;
              }
              else{
                table[i][j] = table[i][j].add(table[i][temp]);
              }
            }
            else{
              table[i][j] = Tuple.IMPOSSIBLE;
            }
          if(i != 0){
            if( !table[i][j].equals(Tuple.IMPOSSIBLE) ){
              if( !table[i-1][j].equals(Tuple.IMPOSSIBLE) && (table[i-1][j].total() < table[i][j].total()) ){
                  table[i][j] = table[i-1][j];
                }
            }
            else{
                if(!table[i-1][j].equals(Tuple.IMPOSSIBLE)){
                  table[i][j] = table[i-1][j];
                }
              }
            }
          }
          }
        }
        return table[denominations.length - 1][desiredChange];
      }
}
