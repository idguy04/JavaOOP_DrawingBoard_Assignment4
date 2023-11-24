// MyOval.java
// Declaration of class MyOval.
import java.awt.Color;
import java.awt.Graphics;
/** 
 * Class- My Oval
 * this class defines an oval.
 * it has its coordinates and whether its filled 
 * or not.
 */
public class MyOval extends MyBoundedShape{
  /**
   * constructor initializes private vars with default values
   * using the super's constructor.
   * also it will define if the shape will be filled or not.
   */
   public MyOval(){}

  /** 
   * constructor with input values.
   * will use the supers constructor and take care
   * of the variables.
   * @param x1 the x-coordinate of the upper left of the shape.
   * @param y1 the y-coordinate of the upper left of the shape.
   * @param x2 the x-coordinate that will determine the size.
   * @param y2 the y-coordinate that will determine the size.
   * @param myColor the color that the shape will be drawn in.
   * @param filled determines whether the shape is filled.
   */
   public MyOval(int x1, int y1, int x2, int y2,
      Color myColor, boolean filled){
      super(x1,y1,x2,y2,myColor,filled);
   } 

  /** 
   * draws an Oval in the specified color 
   */
   public void draw(Graphics g){
      g.setColor(getColor());
      if (isFilled())
         g.fillOval(getUpperLeftX(), getUpperLeftY(),
            getWidth(), getHeight());
      else
         g.drawOval(getUpperLeftX(), getUpperLeftY(),
            getWidth(), getHeight());
   } 
} // end class MyOval
