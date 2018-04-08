import java.text.DecimalFormat;

public class SoccerSim {

  private static final double FIELD_WIDTH = 240.0; //in feet
  private static final double FIELD_LENGTH = 330.0; //in feet
  private static final double[] FIELD_CENTER = {0.0, 0.0};
  private static final double[] POLE_LOC = {120, 165}; //width, length
  private static final double POLE_RADIUS = 5.55;
  private static final double DEFAULT_TIME_SLICE = 1.0;

  private double[] fieldDimensions = new double[2]; //length, width
  private Ball[] bagOBalls = null;
  private int numBalls = 0;
  private Clock timeKeeper = null;
  private double optionalTimeSlice = DEFAULT_TIME_SLICE;
  private boolean firstReport = true;
  private boolean timeSliceArgPresent = true;



   public SoccerSim() {
      timeKeeper = new Clock();
      fieldDimensions[0] = FIELD_LENGTH;
      fieldDimensions[1] = FIELD_WIDTH;
   }

  /**
   *  Method to validate the input arguments
   *  @param arguments String array of the arguments supplied to the program
   */
   public void validateArgsAndSetupSim( String arguments[] ) throws NumberFormatException, IllegalArgumentException {
      


      if( ( arguments.length % 4 ) == 1 ) {
      } else {
        timeSliceArgPresent = false;
      }




      if( arguments.length == 0 ) {
        System.out.println( "You have not entered any arguments.");
        System.exit(0);
      } else {
        

        numBalls = (int) Math.floor( arguments.length / 4 );
        if( !timeSliceArgPresent ) {
          if( (numBalls * 4) != arguments.length ) {
            throw new IllegalArgumentException();
          }
        } else {
          if( (numBalls * 4 + 1) != arguments.length ) {
            throw new IllegalArgumentException();
          }
        }



        int i = 0;
        int j = 0;
        while( j < arguments.length ) {
          try {
            this.bagOBalls[i] = new Ball( Double.parseDouble( arguments[j + 0] ), Double.parseDouble( arguments[j + 1] ), Double.parseDouble( arguments[j + 2] ), Double.parseDouble( arguments[j + 3] ) );
          }
          catch( NumberFormatException nfe ) {
            System.out.println( "You have entered invalid arguments." );
            System.exit(1);
          }
          j += 4;
          i++;
        }
            



          try {
               optionalTimeSlice = this.timeKeeper.validateTimeSliceArg( arguments[arguments.length - 1] );
            }
          catch( NumberFormatException nfe ) {
               System.out.println( "You have entered an invalid time slice." );
               System.exit(1);
            }
          catch( IllegalArgumentException iae ) {
               System.out.println( "You have entered an invalid time slice." );
               System.exit(1);
            }
        }


   }




  /**
   *  method to report the status of the simulation for every clock tick
   *  @param  c  Clock object which keeps track of time
   *  NOTE: this method calls the clock.tick() method to get one second to elapse
   */
   public void report() {
      String output = "";
      if( firstReport ) {
        firstReport = false;
        output += "\nFirst report at 00:00:00.0000. The field is " + FIELD_LENGTH + " feet by " + FIELD_WIDTH + " feet. There is a pole at " + POLE_LOC[0] + "," + POLE_LOC[1] +".";
      } else {
        output += "\nProgress report at " + timeKeeper.toStringColons() + ".";
      }

      for( int i = 0; i < bagOBalls.length; i++) {
        output += "\nBall " + (i + 1) + ": " + bagOBalls[i].toString();
      }
      System.out.println(output);
   }

  /**
   *  method to move the balls in the soccerBall array
   *
   */
   public void simUpdate() {
      for( int i = 0; i < bagOBalls.length; i++ ) {
         bagOBalls[i].move( optionalTimeSlice );
         bagOBalls[i].setBallOutOfBounds( FIELD_WIDTH, FIELD_LENGTH );
      }
   }
   
  /**
   *  method to move the balls in the soccerBall array
   *
   */
   public int[] collisionCheck() {
      int[] collidedBalls = {-9999, -9999};
      double distance = 0;

      for( int i = 0; i < bagOBalls.length; i++ ) {
        for( int j = i + 1; j < bagOBalls.length; j++ ) {
          distance = Math.sqrt( Math.pow( (bagOBalls[i].getCurrPosition()[0] - bagOBalls[j].getCurrPosition()[0]), 2) + Math.pow( (bagOBalls[i].getCurrPosition()[1] - bagOBalls[j].getCurrPosition()[1]), 2));
          if( (distance * 12.0) <= 8.9 ) {
               collidedBalls[0] = i;
               collidedBalls[1] = j;
               return collidedBalls;
            }
        }
      }
      for( int i = 0; i < bagOBalls.length; i++ ) {
        distance = Math.sqrt( Math.pow( (bagOBalls[i].getCurrPosition()[0] - POLE_LOC[0]), 2) + Math.pow( (bagOBalls[i].getCurrPosition()[1] - POLE_LOC[1]), 2));
        if( (distance * 12.0) <= (4.45 + POLE_RADIUS) ) {
           collidedBalls[0] = i;
           collidedBalls[1] = -99;
           return collidedBalls;
        }
      }

      return collidedBalls;
   }

   public boolean atLeastOneBallStillMoving() {
      for( int i = 0; i < bagOBalls.length; i++ ) {
         if( bagOBalls[i].isStillMoving() ) {
            return true;
         }
      }
      return false;
   }

  /**
   *  main method of the simulation
   *  @param  args  String array of the command line arguments
   */
   public static void main( String args[] ) {
      System.out.println("Starting SoccerSim Program.");
      SoccerSim madridVSbarcelona = new SoccerSim();
      try {
         madridVSbarcelona.validateArgsAndSetupSim( args );
      }
      catch( NumberFormatException nfe ) {
          System.out.println( "You have entered invalid args string." );
          System.exit(1);
      }
      catch( IllegalArgumentException iae ) {
          System.out.println( "You have entered invalid args string." );
          System.exit(1);
      }


      madridVSbarcelona.report();
      int numIterations = 1;

      while( true ) {
        madridVSbarcelona.timeKeeper.tick( 0.0, 0.0, 0.0, madridVSbarcelona.optionalTimeSlice );
        madridVSbarcelona.simUpdate();
        madridVSbarcelona.report();
        if( madridVSbarcelona.collisionCheck()[1] != -9999) {
          if( madridVSbarcelona.collisionCheck()[1] == -99 ) {
            System.out.println( "\nBall " + madridVSbarcelona.collisionCheck()[0] + " collided with the pole." );
          } else {
            System.out.println( "\nBall " + madridVSbarcelona.collisionCheck()[0] + " collided with Ball " + madridVSbarcelona.collisionCheck()[1] );
          }
          break;
        }
        if( !madridVSbarcelona.atLeastOneBallStillMoving() ) {
          System.out.println( "None of the balls are moving anymore so the simulation is ending.");
          break;
        }
        numIterations++;
      }
      System.out.println( "Simulation required " + numIterations + " iterations to complete." );
    }
}
