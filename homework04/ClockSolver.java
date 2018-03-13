/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
  *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

import java.text.DecimalFormat;


public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private static double EPSILON_VALUE              = 0.2;      // small value for double-precision comparisons
   private static double[] clockArgs;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
     // args[0] specifies the angle for which you are looking
     //  your simulation will find all the angles in the 12-hour day at which those angles occur
     // args[1] if present will specify a time slice value; if not present, defaults to 60 seconds
     // you may want to consider using args[2] for an "angle window"

      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( args.length < 1 || args.length > 2) {
         System.out.println( "   Sorry you must enter either one or two arguments. \n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      } else {
        try {
          if ( args.length == 1 ){
             clockArgs = new double[2];
             clockArgs[1] = DEFAULT_TIME_SLICE_SECONDS;
          } else {
              clockArgs = new double[args.length];
          }
          for ( int i = 0; i < args.length; i++ ){
             clockArgs[i] = Double.parseDouble(args[i]);
          }
       } catch( NumberFormatException numFormExcept ){
          if ( args.length == 1){
             System.out.println( "Your first argument is not in a valid format.");
             System.exit( 1 );
          } else {
             System.out.println( "One or both of your arguments are invalid.");
             System.exit( 1 );
          }
       }
    }
      Clock clock = new Clock( args );
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  args  String array of the arguments from the command line
   *                args[0] is the angle for which we are looking
   *                args[1] is the time slice; this is optional and defaults to 60 seconds
   */
   public static void main( String[] args ) {
      int numTimes = 0;
      ClockSolver cse = new ClockSolver();
      Clock clock = new Clock( args );
      cse.handleInitialArguments( args );
      DecimalFormat formatInput = new DecimalFormat("#00.0");
      String[] timeArgs = new String[3];
      while( clock.getTotalSeconds() < 43200 ) {
         timeArgs[0] = formatInput.format( Math.round( clock.getTotalSeconds() / 3600 - .5) );
         timeArgs[1] = formatInput.format( Math.round( ( clock.getTotalSeconds() % 3600 ) / 60 - .5 ) );
         timeArgs[2] = formatInput.format( clock.getTotalSeconds() % 60 );
         System.out.println("Currently, the time on your clock is " + timeArgs[0] + " hours, " + timeArgs[1] + " minutes, and " + timeArgs[2] + " seconds.");
         System.out.println("Your hour hand angle is " + clock.getHourHandAngle() + ", and your minute hand angle is " + clock.getMinuteHandAngle() + ".");
         System.out.println("This means that your hands are " + clock.getHandAngle() + ", or roughly " + Math.round(clock.getHandAngle()) + " degrees apart." );
         if ( Math.abs(clock.getHandAngle() - clockArgs[0]) < EPSILON_VALUE) {
            numTimes += 1;
            System.out.println( "The desired angle of " + clockArgs[0] + " degrees was found at " + timeArgs[0] + " hours, " + timeArgs[1] + " minutes, and " + timeArgs[2] + " seconds. The number of times we've found this angle in this simulation is " + numTimes + ".");
         }
         clock.tick();
      }
      System.exit( 0 );
   }
}
