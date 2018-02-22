/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Die.java
 *  Purpose       :  Provides a class describing a single die that can be rolled
 *  @author       :  Ethan Boone
 *  Date          :  2017-02-06
 *  Description   :  This class provides the data fields and methods to describe a single game die.  A
 *                   die can have "N" sides.  Sides are randomly assigned sequential pip values, from 1
 *                   to N, with no repeating numbers.  A "normal" die would thus has six sides, with the
 *                   pip values [spots] ranging in value from one to six.  Includes the following:
 *                   public Die( int nSides );                  // Constructor for a single die with "N" sides
 *                   public int roll();                         // Roll the die and return the result
 *                   public int getValue()                      // get the value of this die
 *                   public void setSides()                     // change the configuration and return the new number of sides
 *                   public String toString()                   // Instance method that returns a String representation
 *                   public static String toString()            // Class-wide method that returns a String representation
 *                   public static void main( String args[] );  // main for testing porpoises
 *
 *  Notes         :  Restrictions: no such thing as a "two-sided die" which would be a coin, actually.
 *                   Also, no such thing as a "three-sided die" which is a physical impossibility without
 *                   having it be a hollow triangular prism shape, presenting an argument as to whether
 *                   the inner faces are faces which then should be numbered.  Just start at four for
 *                   minimum number of faces.  However, be aware that a four-sided die dosn't have a top
 *                   face to provide a value, since it's a tetrahedron [pyramid] so you'll have to figure
 *                   out a way to get the value, since it won't end up on its point.
 *
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */


public class Die {
    
   private int sides;
   private int pips;
   private final int MINIMUM_SIDES = 4;
   
   public Die( int nSides ) {
       this.sides = nSides;
       this.pips = 0;
       if ( nSides  < MINIMUM_SIDES ) {
           throw new IllegalArgumentException("Number of sides must be greater than 4");
       }
       roll();
   }
   
   public int roll() {
       pips = (int) (Math.random()*sides + 1);
       return pips;
   }
   
   public int getValue() {
       return this.pips;
   }
   
   public int setSides( int sides ) {
       this.sides = sides;
       if ( sides < MINIMUM_SIDES) {
           throw new IllegalArgumentException("Number of sides must be greater than 4");
       }
       return sides;
   }
   
   public String toString() {
       return "[" + this.pips + "]";
   }
   
   public static String toString( Die d ) {
       return  "[" + d.pips + "]";
   }
   
   public static void main( String[] args ) {
       System.out.println( "Hello world from the Die class..." );
       //creating a new die with 5 sides
       Die d = new Die( 5 );
       System.out.println(d.roll());
       System.out.println(d.getValue());
       System.out.println(d.toString());
       System.out.println(toString(d));
       System.out.println(d.setSides(6));
       System.out.println(d.roll());
       System.out.println(d.toString());
       System.out.println(toString(d));
   }
}



