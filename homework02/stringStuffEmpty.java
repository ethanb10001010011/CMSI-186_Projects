/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  A file full of stuff to do with the Java String class
 *  Author        :  B.J. Johnson
 *  Date          :  2017-01-19
 *  Description   :  This file presents a bunch of String-style helper methods.  Although pretty much
 *                   any and every thing you'd want to do with Strings is already made for you in the
 *                   Jave String class, this exercise gives you a chance to do it yourself [DIY] for some
 *                   of it and get some experience with designing code that you can then check out using
 *                   the real Java String methods [if you want]
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuffEmpty {
    
    
    public static boolean isPalindrome ( String s) {
        StringBuilder revWord = new StringBuilder(s);
        revWord = revWord.reverse();
        String returnString = new String(revWord);
        if( s.equalsIgnoreCase(returnString) ) {
            return true;
        } else {
            return false;
        }
        
    }


    public static boolean containsVowel( String s ) {
      String[] vowels = new String[] {"a", "e", "i", "o", "u", "y", "A", "E", "I", "O", "U", "Y"};
      for(int i = 0; i < vowels.length; i++) {
         if(s.indexOf(vowels[i]) != -1) {
            return true;
         }
      }
      return false;
   }



    public static String evensOnly( String s ) {
      String evensAlphabet = "BDFHJLNPRTVXZbdfhjlnprtvxz";
      StringBuilder evens = new StringBuilder("");
      for(int i = 0; i < s.length(); i++) {
          if(evensAlphabet.indexOf(s.charAt(i)) != -1) {
              evens.append(s.charAt(i));
          }
      }
      String returnString = new String(evens);
      return returnString;
   }



    public static String oddsOnly( String s ) {
      String oddsAlphabet = "acegikmoqsuqwyACEGIKMOQSUWY";
      StringBuilder odds = new StringBuilder("");
      for(int i = 0; i < s.length(); i++) {
          if(oddsAlphabet.indexOf(s.charAt(i)) != -1) {
              odds.append(s.charAt(i));
          }
      }
      String returnString = new String(odds);
      return returnString;
   }


    public static String removeDuplicates( String s ) {
        StringBuilder noDupes = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                String temp = s.substring(i, i + 1);
                if (noDupes.indexOf(temp) == -1) {
                    noDupes.append(temp);
                }
            }
        String returnString = new String(noDupes);
        return returnString;
    }


    public static String oddsOnlyNoDupes( String s ) {
        return removeDuplicates(oddsOnly(s));
       
   }


   public static String evensOnlyNoDupes( String s ) {
        return removeDuplicates(evensOnly(s));

   }


   public static String reverse( String s ) {
      StringBuilder revWord = new StringBuilder(s);
      revWord = revWord.reverse();
      String returnString = new String(revWord);
      return returnString;
   }


   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
   }

}
