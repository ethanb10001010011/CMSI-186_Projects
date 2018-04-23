/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Arrays;
import java.lang.*;


public class BrobInt {

   public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
   public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
   public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
   public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
   public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
   public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
   public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
   public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
   public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
   public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
   public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

  /// Some constants for other intrinsic data types
  ///  these can help speed up the math if they fit into the proper memory space
   public static final BrobInt MAX_INT  = new BrobInt( Integer.valueOf( Integer.MAX_VALUE ).toString() );
   public static final BrobInt MIN_INT  = new BrobInt( Integer.valueOf( Integer.MIN_VALUE ).toString() );
   public static final BrobInt MAX_LONG = new BrobInt( Long.valueOf( Long.MAX_VALUE ).toString() );
   public static final BrobInt MIN_LONG = new BrobInt( Long.valueOf( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
   public  String internalValue = "";        // internal String representation of this BrobInt
   private byte   sign          = 0;         // "0" is positive, "1" is negative
   private String reversed      = "";        // the backwards version of the internal String representation
   private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string
   private String validValues   = "+-0123456789";
  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt( String value ) {
      
      if( Character.toString(value.charAt(0)).equals("-") ) {
        //System.out.println("removing negative sign :)");
        internalValue = value.substring(1);
        sign = 1;
      } else if ( Character.toString(value.charAt(0)).equals("+") ) {
        //System.out.println("removing positive sign :)");
        internalValue = value.substring(1);
        sign = 0;
      } else {
        //System.out.println("we did not remove any signs :)");
        internalValue = value;
        sign = 0;
      }

      while ( Character.toString(internalValue.charAt(0)).equals("0") && internalValue.length() > 1 ) {
        internalValue = internalValue.substring(1);
      }

      if ( !validateDigits() ) {
        System.out.println("You have entered an invalid brobdingnagian integer");
        System.exit(1);
      }

      StringBuilder s = new StringBuilder(internalValue);
      reversed = s.reverse().toString();

   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() {
      for( int i = 0; i < internalValue.length(); i++ ) {
        if( validValues.indexOf(internalValue.charAt(i)) == -1 || internalValue.length() < 1) {
          return false;
        } 
      }
      return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
      StringBuilder s = new StringBuilder(internalValue);
      return new BrobInt(s.reverse().toString());
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobInt passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
      StringBuilder s = new StringBuilder(gint.internalValue);
      return new BrobInt(s.reverse().toString());
   }


   public int carryOver( int a, int b, int c ) {
      if ( a + b + c < 10 ) {
        return 0;
      } else if ( a + b + c < 20 ) {
        return 1;
      } else {
        return 2;
      }
   }




  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobInt passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addInt( BrobInt gint ) {
      int[] arr1 = new int[ Math.max(this.internalValue.length() + 1, gint.internalValue.length() + 1 ) ];
      int[] arr2 = new int[ Math.max(this.internalValue.length() + 1, gint.internalValue.length() + 1 ) ];
      int[] sum = new int[Math.max(arr1.length, arr2.length)];
      StringBuilder fSum = new StringBuilder("");
      int carry = 0;


      for ( int i = 0; i < this.internalValue.length(); i++ ) {
          arr1[i] = Integer.parseInt(Character.toString(this.internalValue.charAt(this.internalValue.length() - 1 - i)));
      }
      
      //System.out.print("arr1: ");
      //for ( int i = 0; i < arr1.length; i++ ) {
        //  System.out.print(arr1[i]);
      //}
      //System.out.println("");
      

      for ( int i = 0; i < gint.internalValue.length(); i++ ) {
          arr2[i] = Integer.parseInt(Character.toString(gint.internalValue.charAt(gint.internalValue.length() - 1 - i)));
      }
      
      //System.out.print("arr2: ");
      //for ( int i = 0; i < arr2.length; i++ ) {
      //  System.out.print(arr2[i]);
      //}
      //System.out.println("");

      if( this.sign == gint.sign ) {
          for ( int i = 0; i < sum.length; i++ ) {
            if ( arr1[i] + arr2[i] + carry > 9 && arr1[i] + arr2[i] + carry < 20 ) {
                sum[i] = arr1[i] + arr2[i] + carry - 10;
              } else if ( arr1[i] + arr2[i] + carry > 19 ) {
                sum[i] = arr1[i] + arr2[i] + carry - 20;
              } else {
                sum[i] = arr1[i] + arr2[i] + carry;
              }
            carry = carryOver( arr1[i], arr2[i], carry );
          }
          
          for( int i = sum.length - 1; i >= 0; i-- ){
              fSum.append(sum[i]);
          }
          
          if( this.sign == 0 ) {
              fSum.insert(0, "+");
          } else {
              fSum.insert(0, "-");
          }
          
      } else {
          if(this.compareTo(gint) == 1) {
              return this.subtractInt(gint);
          } else {
              return gint.subtractInt(this);
          }
      }
      
      BrobInt finalSum = new BrobInt(fSum.toString());
      return finalSum;

   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobInt passed as argument to this BrobInt using int array
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractInt( BrobInt gint ) {
      int[] arr1 = new int[ Math.max(this.internalValue.length() + 1, gint.internalValue.length() + 1 ) ];
      int[] arr2 = new int[ Math.max(this.internalValue.length() + 1, gint.internalValue.length() + 1 ) ];
      int[] diff = new int[Math.max(arr1.length, arr2.length)];
      StringBuilder fDiff = new StringBuilder("");
      int carry = 0;

      for ( int i = 0; i < this.internalValue.length(); i++ ) {
          arr1[i] = Integer.parseInt(Character.toString(this.internalValue.charAt(this.internalValue.length() - 1 - i)));
      }
      
      for ( int i = 0; i < gint.internalValue.length(); i++ ) {
          arr2[i] = Integer.parseInt(Character.toString(gint.internalValue.charAt(gint.internalValue.length() - 1 - i)));
      }
      
      if ( this.sign == gint.sign ) {
          if ( this.compareAbs(gint) == 1 ) {
              for ( int i = 0; i < diff.length; i++ ) {
                  if ( (arr1[i] - carry) >= arr2[i] ) {
                      diff[i] = arr1[i] - carry - arr2[i];
                      carry = 0;
                  } else {
                      diff[i] = arr1[i] - carry + 10 - arr2[i];
                      carry = 1;
                  }
              }
              if ( this.sign == 0 ) {
                  fDiff.insert(0, "+");
              } else {
                  fDiff.insert(0, "-");
              }
          } else {
              for ( int i = 0; i < diff.length; i++ ) {
                  if ( (arr2[i] - carry) >= arr1[i] ) {
                      diff[i] = arr2[i] - carry - arr1[i];
                      carry = 0;
                  } else {
                      diff[i] = arr2[i] - carry + 10 - arr1[i];
                      carry = 1;
                  }
              }
              if ( this.sign == 0 ) {
                  fDiff.insert(0, "-");
              } else {
                  fDiff.insert(0, "+");
              }
          }
      } else {
          gint.sign = this.sign;
          return this.addInt(gint);
      }
      
      for( int i = diff.length - 1; i >= 0; i-- ){
          fDiff.append(diff[i]);
      }
      
      BrobInt finalDiff = new BrobInt(fDiff.toString());
      return finalDiff;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply this by
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
       //int[] thisArr = new int[ this.internalValue.length() ];
       //int[] product = new int[]
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {

     // handle the signs here
      if( 1 == sign && 0 == gint.sign ) {
         return -1;
      } else if( 0 == sign && 1 == gint.sign ) {
         return 1;
      }

     // the signs are the same at this point
     // check the length and return the appropriate value
     //   1 means this is longer than gint, hence larger
     //  -1 means gint is longer than this, hence larger
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);

     // otherwise, they are the same length, so compare absolute values
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = Character.valueOf( internalValue.charAt(i) );
            Character b = Character.valueOf( gint.internalValue.charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare abs of BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method does not do a lexicographical comparison using the java String "compareTo()" method
   *        It takes into account the length of the two numbers, and if that isn't enough it does a
   *        character by character comparison to determine
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareAbs( BrobInt gint ) {

     //   1 means this is longer than gint, hence larger
     //  -1 means gint is longer than this, hence larger
      if( internalValue.length() > gint.internalValue.length() ) {
         return 1;
      } else if( internalValue.length() < gint.internalValue.length() ) {
         return (-1);

     // otherwise, they are the same length, so compare absolute values
      } else {
         for( int i = 0; i < internalValue.length(); i++ ) {
            Character a = Character.valueOf( internalValue.charAt(i) );
            Character b = Character.valueOf( gint.internalValue.charAt(i) );
            if( Character.valueOf(a).compareTo( Character.valueOf(b) ) > 0 ) {
               return 1;
            } else if( Character.valueOf(a).compareTo( Character.valueOf(b) ) < 0 ) {
               return (-1);
            }
         }
      }
      return 0;
   }




  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" using the
   *        java String "equals()" method -- THAT was easy..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value    long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( Long.valueOf( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   
   public String toString() {
       StringBuilder sBob = new StringBuilder(this.internalValue);
       if ( this.sign == 1 ) {
           sBob.insert(0,"-");
       } else {
           sBob.insert(0,"+");
       }
       return sBob.toString();
       
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Helper method to display an Array representation of this BrobInt as its bytes
   *  @param  d  byte array to represent
   *  @see https://docs.oracle.com/javase/9/docs/api/java/util/Arrays.html
   *  NOTE: the java.utils.Arrays class contains a toString() method which is overridden to take quite a
   *        few different array data types as arguments.  To use this with your code, simply change the
   *        data type for the argument to match the data type of the array you want represented.  For
   *        example, change "byte[]" to "int[]" to make this method hand int arrays.
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  NOTE:  we don't really care about these, since we test the BrobInt class with the BrobIntTester
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main( String[] args ) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      //System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
      BrobInt tester1 = new BrobInt("-0134267472");
      BrobInt tester2 = new BrobInt("-0008379428");
      BrobInt tester3 = new BrobInt("0");
      BrobInt tester4 = new BrobInt("-2");
      System.out.println("tester1 value is " + tester1.toString());
      System.out.println("tester2 value is " + tester2.toString());
      System.out.println("the sum of the two tester values is: " + tester1.addInt(tester2).toString());
      System.out.println("the difference of the two tester values is: " + tester2.subtractInt(tester1).toString());
      System.out.println("the difference of the two tester [ 3 & 4 ] values is: " + tester3.subtractInt(tester4).toString());
      
      
      
      
      BrobInt[] fibSequence = new BrobInt[ Integer.parseInt(args[0]) + 1];
      fibSequence[0] = new BrobInt("0");
      fibSequence[1] = new BrobInt("1");
      for ( int i = 2; i < fibSequence.length; i++ ) {
          fibSequence[i] = new BrobInt(fibSequence[i-1].addInt(fibSequence[i-2]).toString());
      }
      System.out.println("The " + args[0] + "th term in the fibonacci sequence is " + fibSequence[fibSequence.length - 1]);
      
      
      
      System.exit( 0 );


   }
}
