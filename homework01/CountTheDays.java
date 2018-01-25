/**
 *  File name     :  CountTheDays.java
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
