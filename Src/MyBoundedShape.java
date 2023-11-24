import java.awt.Color;

/**
 * This abstract class manages the bounded shapes (like oval and rectangle)
 */

public abstract class MyBoundedShape extends MyShape {
   /**
    * filled - boolean that determines whether the shape is filled or not.
    */
   private boolean filled; // whether this shape is filled

   /**
    * constructor initializes private vars with default values using the super's
    * constructor. also it will define if the shape will be filled or not.
    */
   public MyBoundedShape() {
      super();
      filled = false;
   }

   /**
    * constructor with input values. will use the supers constructor and take care
    * of the remaining variables
    * 
    * @param x1      the x-coordinate of the upper left of the shape.
    * @param y1      the y-coordinate of the upper left of the shape.
    * @param x2      the x-coordinate that will determine the size.
    * @param y2      the y-coordinate that will determine the size.
    * @param myColor the color that the shape will be drawn in.
    * @param filled  determines whether the shape is filled.
    */
   public MyBoundedShape(int x1, int y1, int x2, int y2, Color myColor, boolean filled) {
      super(x1, y1, x2, y2, myColor);
      this.filled = filled;
   }

   /**
    * @return upper left x coordinate
    */
   public int getUpperLeftX() {
      return Math.min(getX1(), getX2());
   }

   /**
    * @return upper left y coordinate
    */
   public int getUpperLeftY() {
      return Math.min(getY1(), getY2());
   }

   /**
    * @return shape width
    */
   public int getWidth() {
      return Math.abs(getX2() - getX1());
   }

   /**
    * @return shape height
    */
   public int getHeight() {
      return Math.abs(getY2() - getY1());
   }

   /**
    * @return whether this shape is filled
    */
   public boolean isFilled() {
      return filled;
   }

   /**
    * sets whether this shape is filled
    * 
    * @param filled the boolean parameter for deterimning whether the shape is
    *               full.
    */
   public void setFilled(boolean filled) {
      this.filled = filled;
   }

}