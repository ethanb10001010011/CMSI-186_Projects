import java.text.DecimalFormat;

public class Ball {

   private static final double RADIUS = 4.45;
   private static final double WEIGHT = 1.0;
   private static final double SLOWING_SPEED = 0.99;

   private boolean isInBounds = true;
   private double xPos;
   private double yPos;
   private double xSpeed;
   private double ySpeed;

   private double[] currPosition = new double[2];
   private double[] currSpeed = new double[2];

  /**
   * Constructor to make a new Ball, no parameters
   *  @param xLocation double-precision value of the X location of the ball
   *  @param yLocation double-precision value of the Y location of the ball
   *  @param xMovement double-precision value for the initial speed of the ball in X direction
   *  @param yMovement double-precision value for the initial speed of the ball in Y direction
   */
   public Ball( double xLocation, double yLocation, double xMovement, double yMovement ) {
      xPos = xLocation;
      yPos = yLocation;
      xSpeed = xMovement;
      ySpeed = yMovement;
   }

  /**
   *  method to fetch the current speed of the ball
   *  @return  double-precision two-element array of X and Y speed values
   */
   public double[] getCurrSpeed() {
      currSpeed[0] = xSpeed;
      currSpeed[1] = ySpeed;
      return currSpeed;
   }

  /**
   *  method to fetch the current position of the ball
   *  @return  double-precision two-element array of X and Y location values
   */
   public double[] getCurrPosition() {
      currPosition[0] = xPos;
      currPosition[1] = yPos;
      return currPosition;
   }

  /**
   *  method to determine if the ball is still moving
   *  @return  boolean true if ball is moving, false if at rest
   *  Note:    at rest is defined as speed <= 1.0 inch/second
   */
   public boolean isStillMoving() {
      return ( 1.0 <= Math.abs(currSpeed[0] * 12.0) );
   }

  /**
   *  method to flag the ball as "out of bounds" which will set its speed to zero and its
   *    "isInBounds" value to false so it will effectively no longer be in the simulation
   *  @param fieldWidth    double-precision value of the designated field width
   *  @param fieldHeight   double-precision value of the designated field height
   */
   public void setBallOutOfBounds( double fieldWidth, double fieldHeight ) {
      if( (Math.abs(currPosition[0]) >= (fieldWidth / 2.0)) ||
          (Math.abs(currPosition[1]) >= (fieldHeight / 2.0)) ) {
         isInBounds = false;
         currSpeed[0] = 0.0;
         currSpeed[1] = 0.0;
      }
   }

  /**
   *  method to update the speed of the ball for one slice of time
   *  @param timeSlice     double-precision value of time slace for simulation
   *  @return  double-precision two element array of new velocity values
   */
   public double[] updateSpeed( double timeSlice ) {
      currSpeed[0] *= Math.pow( 0.99, timeSlice );
      currSpeed[1] *= Math.pow( 0.99, timeSlice );
      return currSpeed;
   }

   public double[] updatePosition( double timeSlice ) {
      currPosition[0] += currSpeed[0];
      currPosition[1] += currSpeed[1];
      return currPosition;
   }

  /**
   *  method to update the ball's position and velocity
   *  @param timeSlice     double-precision value of time slace for simulation
   */
   public void move( double timeSlice ) {
      currSpeed = updateSpeed( timeSlice );
      currPosition = updatePosition( timeSlice );
   }

  /**
   * our venerable "toString()" representation
   *  @return  String-y version of what this Ball is
   */
   public String toString() {
      DecimalFormat positionString = new DecimalFormat( "#0.00");
      DecimalFormat speedString = new DecimalFormat( "#0.0000" );
      String output = "The position is" + positionString.format( currPosition[0] ) + " for the x-axis, and " + positionString.format( currPosition[1] ) + " for the y-axis.";
           
      if( !isInBounds ) {
         output += "\nThe ball is out of bounds.";
      } else if( !isStillMoving() ) {
         output += "\nThe ball is not moving.";
      } else {
         output += "\nThe speed is " + speedString.format( currSpeed[0] ) + "ips for the x-axis and " + speedString.format( currSpeed[1] ) + " ips for the y-axis.";
      }
      return output;
   }

  /**
   * a main method for testing -- pretty simple
   *  @param args[] String array of command line arguments
   */
   public static void main( String args[] ) {
      System.out.println( "\nTesting Ball.java" );
      Ball ball1 = new Ball( 12, 42, 4, 6 );
      Ball ball2 = new Ball( 34, 21, 7, 3 );
      Ball ball3 = new Ball( 65, 23, 2, 3);
      System.out.println( "Ball 1 toString is " + ball1.toString() );
      System.out.println( "Ball 2 toString is " + ball2.toString() );
      System.out.println( "Ball 3 toString is " + ball3.toString() );
      ball1.move( 1 );
      ball2.move( .4 );
      ball3.move( 4.3 );
      System.out.println( "Ball 1 toString is " + ball1.toString() );
      System.out.println( "Ball 2 toString is " + ball2.toString() );
      System.out.println( "Ball 3 toString is " + ball3.toString() );
      ball1.setBallOutOfBounds( 100, 100 );
      ball2.setBallOutOfBounds( 50, 50 );
      ball3.setBallOutOfBounds( 3, 3 );
      System.out.println( "Is Ball 1 in bounds? : " + ball1.isInBounds );
      System.out.println( "Is Ball 2 in bounds? : " + ball2.isInBounds );
      System.out.println( "Is Ball 3 in bounds? : " + ball3.isInBounds );

   }
}
