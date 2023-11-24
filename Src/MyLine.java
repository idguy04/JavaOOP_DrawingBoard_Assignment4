
import java.awt.Color;
import java.awt.Graphics;
/** 
 * MyLine Class-
 * this class represents a line with all its properties
 */
public class MyLine extends MyShape{

  /**
   * empty constructor which will call the father's empty constructor
   * by default.
   */
   public MyLine(){} 	   

   /** 
    * constructor with input values.
    * uses super's constructor to initialize the values.
    * @param x1 the x-coordinate of the upper left of the shape.
    * @param y1 the y-coordinate of the upper left of the shape.
    * @param x2 the x-coordinate that will determine the size.
    * @param y2 the y-coordinate that will determine the size.
    * @param myColor the color that the shape will be drawn in.
    */
   public MyLine(int x1, int y1,
   	   int x2, int y2, Color myColor){
   	   super(x1,y1,x2,y2,myColor);
   } 
 
  /**
   * draw the line in the specified color 
   */
   public void draw(Graphics g){
      g.setColor(getColor());
      g.drawLine(getX1(), getY1(), getX2(), getY2());
   } 
} // end class MyLine
