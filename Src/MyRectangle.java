// MyRectangle.java
// Declaration of class MyRectangle.
import java.awt.Color;
import java.awt.Graphics;
/**
 * Class - MyRectangle.
 * This class defines a rectangles,
 * it has the rectangles coordinates.
 */
public class MyRectangle extends MyBoundedShape{
  /**
   * constructor initializes private vars with default values
   * using the super's constructor.
   * also it will define if the shape will be filled or not.
   */
   public MyRectangle(){} 

  /** 
   * constructor with input values.
   * will use the supers constructor and take care
   * of the variables
   * @param x1 the x-coordinate of the upper left of the shape.
   * @param y1 the y-coordinate of the upper left of the shape.
   * @param x2 the x-coordinate that will determine the size.
   * @param y2 the y-coordinate that will determine the size.
   * @param myColor the color that the shape will be drawn in.
   * @param filled determines whether the shape is filled.
   */
   public MyRectangle(int x1, int y1, int x2, int y2,
      Color myColor, boolean filled){
      super(x1,y1,x2,y2,myColor,filled);
   } 

  /** 
   * draws a Rectangle in the specified color
   */
   public void draw(Graphics g){
      g.setColor(getColor());
      
      if (isFilled())
         g.fillRect(getUpperLeftX(), getUpperLeftY(),
            getWidth(), getHeight());
      else
         g.drawRect(getUpperLeftX(), getUpperLeftY(),
            getWidth(), getHeight());
   } 
} // end class MyRectangle
