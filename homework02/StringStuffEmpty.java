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
      test_containsVowel();      // fourteen tests
      test_isPalindrome();       // eight tests
      test_evensOnly();          // fill in how many tests
      test_oddsOnly();           // fill in how many tests
      test_evensOnlyNoDupes();   // fill in how many tests
      test_oddsOnlyNoDupes();    // fill in how many tests
      test_reverse();            // fill in how many tests
    }
   
   
   
   
   
   public static void test_containsVowel() {
      System.out.println( "\nFIFTEEN TESTS FOR containsVowel():" );
      System.out.print( "   Test for all lowercase vowels: " );
      try { System.out.println( StringStuffEmpty.containsVowel( "The quick brown fox." ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for all uppercase vowels: " );
      try { System.out.println( StringStuffEmpty.containsVowel( "JUMPED OVER THE LAZY DOG." ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting lowercase 'a': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "a" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting uppercase 'A': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "A" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting lowercase 'e': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "e" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting uppercase 'E': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "E" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting lowercase 'i': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "i" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting uppercase 'I': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "I" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting lowercase 'o': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "o" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting uppercase 'O': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "O" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting lowercase 'u': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "u" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting uppercase 'U': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "U" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting lowercase 'y': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "y" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting uppercase 'Y': " );
      try { System.out.println( StringStuffEmpty.containsVowel( "Y" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for detecting there are none: " );
      try { System.out.println( StringStuffEmpty.containsVowel( "bdBDBDbd" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Test for string containing numbers and symbols with 'a' at the end: " );
      try { System.out.println( StringStuffEmpty.containsVowel( "1234567890!@#$%^&*()a" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

   }

  /**
   * test method to test out the operation of the isPalindrome method
   */
   public static void test_isPalindrome() {
      System.out.println( "\nTEN TESTS FOR isPalindrome():" );
      System.out.print( "   Testing 'a' should return true: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "a" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'ab' should return false: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "ab" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'aba' should return true: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "aba" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'amanaplanacanalpanama' should return true: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "amanaplanacanalpanama" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'ABBA' should return true: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "ABBA" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'RaCeCaR' should return true: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "RaCeCaR" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'madamiamadam' should return false: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "madamiamadam" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'abcdefghigfedcba' should return false: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "abcdefghigfedcba" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'abcdefgh gfedcba' should return false: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "abcdefgh gfedcba" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing '1!2@3#4$5%5$4#3@2!1' should return true: " );
      try { System.out.println( StringStuffEmpty.isPalindrome( "1!2@3#4$5%5$4#3@2!1" ) ? "true" : "false" ); }
      catch( Exception e ) { System.out.println ( false ); }

   }

  /**
   * test method to test out the operation of the evensOnly method
   */
   public static void test_evensOnly() {
      System.out.println( "\nNINE TESTS FOR evensOnly():" );
      System.out.print( "   Testing 'REHEARSALSZ' should return RHRLZ: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "REHEARSALSZ" ).compareTo( "RHRLZ" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'REhearSALsz' should return RhrLz: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "REhearSALsz" ).compareTo("RhrLz")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' should return BDFHJLNPRTVXZ: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ).compareTo("BDFHJLNPRTVXZ")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'abcdefghijklmnopqrstuvwxyz' should return bdfhjlnprtvxz: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "abcdefghijklmnopqrstuvwxyz" ).compareTo("bdfhjlnprtvxz")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'AbCdEfGhIjKlMnOpQrStUvWxYz' should return bdfhjlnprtvxz: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "AbCdEfGhIjKlMnOpQrStUvWxYz" ).compareTo("bdfhjlnprtvxz")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'ACEGIKMOQSUWY' should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "ACEGIKMOQSUWY" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'acegikmoqsuwy' should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "acegikmoqsuwy" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing empty string should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'REhe12ar^^SALsz' should return RhrLz: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnly( "REhe12ar^^SALsz" ).compareTo("RhrLz")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

   }

  /**
   * test method to test out the operation of the oddsOnly method
   */
   public static void test_oddsOnly() {
      System.out.println( "\nNINE TESTS FOR oddsOnly():" );
      System.out.print( "   Testing 'xylophones' should return yooes: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "xylophones" ).compareTo( "yooes" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'XYloPHonES' should return YooES: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "XYloPHonES" ).compareTo("YooES")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' should return ACEGIKMOQSUWY: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "ABCDEFGHIJKLMNOPQRSTUVWXYZ" ).compareTo("ACEGIKMOQSUWY")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'abcdefghijklmnopqrstuvwxyz' should return bdfhjlnprtvxz: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "abcdefghijklmnopqrstuvwxyz" ).compareTo("acegikmoqsuwy")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'AbCdEfGhIjKlMnOpQrStUvWxYz' should return bdfhjlnprtvxz: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "AbCdEfGhIjKlMnOpQrStUvWxYz" ).compareTo("ACEGIKMOQSUWY")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'BDFHJLNPRTVXZ' should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "BDFHJLNPRTVXZ" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'bdfhjlnprtvxz' should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "bdfhjlnprtvxz" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing empty string should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'xy$%lo23ph((on97es' should return yooes: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnly( "xylophones" ).compareTo( "yooes" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

   }

  /**
   * test method to test out the operation of the evensOnlyNoDupes method
   */
   public static void test_evensOnlyNoDupes() {
      System.out.println( "\nEIGHT TESTS FOR evensOnlyNoDupes():" );
      System.out.print( "   Testing 'xylophones' should return xlphn: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "xylophones" ).compareTo( "xlphn" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'XYloPHonES' should return XlPHn: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "XYloPHonES" ).compareTo("XlPHn")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'AAAABBBBAAAA' should return B: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "AAAABBBBAAAA" ).compareTo("B")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'shshshshshshshsh' should return h: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "shshshshshshshsh" ).compareTo("h")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'AAbbAAbbCCddCCdd' should return bd: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "AAbbAAbbCCddCCdd" ).compareTo( "bd" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing empty string should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'California' should return lfrn: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "California" ).compareTo("lfrn")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'ACEGIKMOQSUWYACEGIKMOQSUWY' should return the empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.evensOnlyNoDupes( "ACEGIKMOQSUWYACEGIKMOQSUWY" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

   }

  /**
   * test method to test out the operation of the oddsOnlyNoDupes method
   */
   public static void test_oddsOnlyNoDupes() {
      System.out.println( "\nEIGHT TESTS FOR oddsOnlyNoDupes():" );
      System.out.print( "   Testing 'xylophones' should return yoes: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "xylophones" ).compareTo( "yoes" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'XYloPHonES' should return YoES: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "XYloPHonES" ).compareTo("YoES")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'AAAABBBBAAAA' should return A: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "AAAABBBBAAAA" ).compareTo("A")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'shshshshshshshsh' should return h: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "shshshshshshshsh" ).compareTo("s")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'AAbbAAbbCCddCCdd' should return bd: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "AAbbAAbbCCddCCdd" ).compareTo( "AC" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing empty string should return empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'California' should return Caio: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "California" ).compareTo("Caio")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'BDFHJLNPRTVXBDFHJLNPRTVX' should return the empty string: " );
      try { System.out.println( (0 == StringStuffEmpty.oddsOnlyNoDupes( "BDFHJLNPRTVXBDFHJLNPRTVX" ).compareTo("")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

   }

  /**
   * test method to test out the operation of the reverse method
   */
   public static void test_reverse() {
      System.out.println( "\nFOUR TESTS FOR reverse():" );
      System.out.print( "   Testing 'xylophones' should return senohpolyx: " );
      try { System.out.println( (0 == StringStuffEmpty.reverse( "xylophones" ).compareTo( "senohpolyx" )) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'XYloPHonES' should return SEnoHPolYX: " );
      try { System.out.println( (0 == StringStuffEmpty.reverse( "XYloPHonES" ).compareTo("SEnoHPolYX")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'AmanAplanAcAnalpAnamA' should return AmanAplanAcAnalpAnamA: " );
      try { System.out.println( (0 == StringStuffEmpty.reverse( "AmanAplanAcAnalpAnamA" ).compareTo("AmanAplanAcAnalpAnamA")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

      System.out.print( "   Testing 'shut the front door 1234567890' should return 0987654321 rood tnorf eht tuhs: " );
      try { System.out.println( (0 == StringStuffEmpty.reverse( "shut the front door 1234567890" ).compareTo("0987654321 rood tnorf eht tuhs")) ? "got it" : "don't got it" ); }
      catch( Exception e ) { System.out.println ( false ); }

   }


}
