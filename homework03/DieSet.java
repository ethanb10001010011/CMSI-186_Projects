/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  Ethan Boone
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.util.Arrays;

public class DiceSet {

   private int count;
   private int sides;
   private Die[] ds = null;

   // public constructor:

   public DiceSet( int count, int sides ) {
       this.count = count;
       this.sides = sides;
       ds = new Die[ count ];
       if ( sides <= 4 || count < 0 ) {
           throw new IllegalArgumentException("Please check the count and sides values.");
       }
       for( int i = 0; i < ds.length; i++ ) {
           ds[i] = new Die( sides );
       }
   }

   public int sum() {
       int result = 0;
       for( int i = 0; i < ds.length; i++ ) {
           result += ds[i].getValue();    
       }
       return result;
   }

   public void roll() {
       String dsValues = "";
       for( int i = 0; i < ds.length; i++ ) {
           ds[i].roll();
           dsValues += ds[i].toString(); 
       }
   }

   public int rollIndividual( int dieIndex ) {
       int result = 0;
       if (dieIndex >= ds.length || dieIndex < 0) {
           throw new IllegalArgumentException("dieIndex is out of range");
       }
       result = ds[dieIndex].roll();
       return result;
   }

   public int getIndividual( int dieIndex ) {
       int result = 0;
       if (dieIndex >= ds.length || dieIndex < 0) {
           throw new IllegalArgumentException("dieIndex is out of range");
       }
       result = ds[dieIndex].getValue();
       return result;
   }

   public String toString() {
       String result = "";
       for ( int i = 0; i < ds.length; i++ ) {
           result += "[" + ds[i].getValue() + "]";
       }
       return result;
   }

   public static String toString( DiceSet ds ) {
       String result = "";
       for ( int i = 0; i < ds.count; i++ ) {
           result += "[" + ds.getIndividual(i) + "]"; 
       }
       return result;
   }

   public boolean isIdentical( DiceSet diceSet ) {
       if( diceSet.toString().equals(this.toString()) ) {
           return true;
       } else {
           return false;
       }
   }

   public static void main( String[] args ) {
       System.out.println( "Hello world from the DieSet class..." );
       DiceSet ds = new DiceSet ( 1, 5 );
       DiceSet diceSet = new DiceSet( 1, 5);
       ds.roll();
       System.out.println(ds.rollIndividual(0));
       System.out.println(diceSet.getIndividual(0));
       System.out.println(ds.toString());
       System.out.println(toString(diceSet));
       System.out.println(ds.isIdentical(diceSet));
   }
}
