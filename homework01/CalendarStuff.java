/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  <Ethan Boone>
 *  Date          :  <2018.01.23>
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY    = MONDAY    + 1;
   private static final int WEDNESDAY    = TUESDAY    + 1;
   private static final int THURSDAY    = WEDNESDAY    + 1;
   private static final int FRIDAY    = THURSDAY    + 1;
   private static final int SATURDAY    = FRIDAY    + 1;
   
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH   = FEBRUARY   + 1;
   private static final int APRIL   = MARCH   + 1;
   private static final int MAY   = APRIL   + 1;
   private static final int JUNE   = MAY   + 1;
   private static final int JULY   = JUNE   + 1;
   private static final int AUGUST   = JULY   + 1;
   private static final int SEPTEMBER   = AUGUST   + 1;
   private static final int OCTOBER   = SEPTEMBER   + 1;
   private static final int NOVEMBER   = OCTOBER   + 1;
   private static final int DECEMBER   = NOVEMBER   + 1;
 
  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static long[]    normalDays        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
   private static long[]    leapYearDays        = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return   boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear ( long year ) {
      if ( year % 4 == 0 && year % 100 == 0 && year % 400 == 0 ) {
        return true;
      } else if ( year % 4 == 0 && year % 100 != 0 ) {
        return true;
      } else {
        return false;
      }
    }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
    long numDays = 0;
      if ( isLeapYear(year) ) {
        numDays = leapYearDays[(int) month - 1];
      } else {
        numDays = normalDays[(int) month - 1];
      }
    return numDays;
  }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if ( month1 == month2 && day1 == day2 && year1 == year2 ) {
        return true;
      } else {
        return false;
      }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     String dayString1 = "" + day1;
     String monthString1 = "" + month1;
     String yearString1 = "" + year1;
     String dayString2 = "" + day2;
     String monthString2 = "" + month2;
     String yearString2 = "" + year2;

     if ( day1 < 10 ) {
       dayString1 = "0" + dayString1;
     }

     if ( day2 < 10 ) {
       dayString2 = "0" + dayString2;
     }

     if ( month1 < 10 ) {
       monthString1 = "0" + monthString1;
     }

     if ( month2 < 10 ) {
       monthString2 = "0" + monthString2;
     }

     String date1 = "" + yearString1 + monthString1 + dayString1;
     String date2 = "" + yearString2 + monthString2 + dayString2;

     if ( Integer.parseInt(date1) > Integer.parseInt(date2) ) {
       return 1;
     } else if ( Integer.parseInt(date1) == Integer.parseInt(date2) ) {
       return 0;
     } else {
       return -1;
     }

   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
    long inputMonth = month - 1;
      if ( isLeapYear(year) == false && inputMonth <= 11 && inputMonth >= 0 && day > 0 && day <= normalDays[(int) inputMonth] ) {
        return true;
      } else if ( isLeapYear(year) == true && inputMonth <= 11 && inputMonth >= 0 && day > 0 && day <= leapYearDays[(int) inputMonth] ) {
        return true;
      } else {
        return false;
      }
   }




  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
      

   public static String toMonthString( int month ) {
      switch( month - 1 ) {
        case JANUARY: return "January";
        case FEBRUARY: return "February";
        case MARCH: return "March";
        case APRIL: return "April";
        case MAY: return "May";
        case JUNE: return "June";
        case JULY: return "July";
        case AUGUST: return "August";
        case SEPTEMBER: return "September";
        case OCTOBER: return "October";
        case NOVEMBER: return "November";
        case DECEMBER: return "December";
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
        case MONDAY: return "Monday";
        case TUESDAY: return "Tuesday";
        case WEDNESDAY: return "Wednesday";
        case THURSDAY: return "Thursday";
        case FRIDAY: return "Friday";
        case SATURDAY: return "Saturday";
        case SUNDAY: return "Sunday";
        default : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }



   public static boolean withinAYear( long month1, long day1, long year1, long month2, long day2, long year2 ) {
     if ( compareDate( month1, day1, year1, month2, day2, year2 ) == 0 ) {
       return true;
     } else if( compareDate( month1, day1, year1, month2, day2, year2 ) == 1 ) {
         if( (year1 - year2 <= 1 && month1 < month2) || (year1 - year2 <= 1 && month1 <= month2 && day1 < day2) || (year1 == year2) ){
           return true;
         } else {
          return false;
         }
     } else if( compareDate( month1, day1, year1, month2, day2, year2 ) == -1 ) {
         if( (year2 - year1 <= 1 && month2 < month1) || (year2 - year1 <= 1 && month1 >= month2 && day1 > day2) || (year1 == year2) ) {
           return true;
         } else {
          return false;
         }
     } else {
         return false;
     }
   }




  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long dayCount = 0;
      long largerDay = 0;
      long largerMonth = 0;
      long largerYear = 0;
      long monthsBetween = 0;
      long leftoverDays = 0;
      long yearsBetween = 0;
      long numLeapYears = 0;

      for( int i = (int) Math.min(year1, year2); i <= Math.max(year1, year2); i++) {
        if(isLeapYear(i) == true) {
          numLeapYears++;
        }
      }


      if ( compareDate( month1, day1, year1, month2, day2, year2 ) == 0 ) {
        return 0;
      } else if ( compareDate( month1, day1, year1, month2, day2, year2 ) == 1 ) {
        largerDay = day1;
        largerMonth = month1;
        largerYear = year1;
        if( withinAYear( largerMonth, largerDay, largerYear, month2, day2, year2 ) == true ) {
          if( largerYear - year2 == 1 && largerDay == day2 && largerMonth == month2) {
            dayCount += 365;
          } else {
            if( ( Math.abs(largerMonth - month2) ) < 1 ) {
              dayCount += ( largerDay - day2 );
            } else {
                for( int i = (int) month2 + 1; i < largerMonth; i++ ) {
                dayCount += normalDays[i];
            }
                leftoverDays = (( normalDays[(int) month2] - day2 ) + day1 );
                dayCount += leftoverDays;

            }
          }

        }


      } else if ( compareDate( month1, day1, year1, month2, day2, year2 ) == -1 ) {
          largerDay = day2;
          largerMonth = month2;
          largerYear = year2;
          if ( withinAYear( largerMonth, largerDay, largerYear, month1, day1, year1 ) == true ) {
            if ( largerYear - year1 == 1 && largerDay == day1 && largerMonth == month1 ) {
              dayCount += 365;
            } else {
                if ( ( Math.max(month1, month2) - Math.min(month1, month2) ) == 0 && ( year1 == year2 ) ) {
                  dayCount += ( Math.max(day2, day1) - Math.min(day2, day1) );
              } else if ( ( Math.max(month1, month2) - Math.min(month1, month2) ) == 0 && ( year1 != year2 ) ){
                  dayCount += ( 365 - (Math.max(day2, day1) - Math.min(day2, day1)) );
              } else if( ( Math.max(month1, month2) - Math.min(month1, month2) ) != 0 && ( year1 == year2 ) ) {
                  for( int i = (int) Math.min(month1, month2) + 1; i < Math.max(month1, month2); i++ ) {
                    dayCount += normalDays[i];
                  }
                  dayCount += largerDay + ( normalDays[(int) month1] - day1 );
              } else {
                  for( int i = (int) Math.min(month1, month2) + 1; i < Math.max(month1, month2); i++ ) {
                    dayCount -= normalDays[i];
                  }
                  leftoverDays = (( normalDays[(int) month2] - day2 ) + day1 );
                  dayCount -= leftoverDays;
                  dayCount += 365;
              }
            } 
        } else {
            yearsBetween = Math.max(year1, year2) - Math.min(year1, year2);
            year2 = year1;
            dayCount += 365 * yearsBetween;
            if ( ( Math.max(month1, month2) - Math.min(month1, month2) ) == 0) {
                  dayCount += ( Math.max(day2, day1) - Math.min(day2, day1) );
            } else if( ( Math.max(month1, month2) - Math.min(month1, month2) ) != 0) {
                  for( int i = (int) Math.min(month1, month2) + 1; i < Math.max(month1, month2); i++ ) {
                    dayCount += normalDays[i];
                  }
                  dayCount += largerDay + ( normalDays[(int) month1] - day1 );
            }/* else {
                for( int i = (int) Math.min(month1, month2) + 1; i < Math.max(month1, month2); i++ ) {
                  dayCount -= normalDays[i];
                }
                  leftoverDays = (( normalDays[(int) month2] - day2 ) + day1 );
                  dayCount -= leftoverDays;
                  dayCount += 365;
            }
         */ }
        }
       return dayCount + numLeapYears;
   }
  }
/*
days between function

use compareDate method to determine which date is larger
if they are the same, return 0
if not, use withinAYear function to figure out how many years they're apart
if they're within a year
- find gap in months (for statement starting with i=lower month, ending with i=high month) adding together the 
- for months >lower month and <high month, add days together
- subtract the total days of low month and days input for lower month
- add that solution to number of days input for larger month
- 

check how many years the two dates are apart
check how many months they are apart

USE COMPAREDATE METHOD
*/
