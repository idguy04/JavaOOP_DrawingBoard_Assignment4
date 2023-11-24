import java.awt.Color;
import java.awt.Graphics;
/**
 * abstract class to manage all of our different shapes
 */
 public abstract class MyShape{

 	/** x1 - the x coordinate of the position of shape */
 	 private int x1; // x coordinate of first endpoint
 	/** y1 - the y coordinate of the position if the shape. */
     private int y1; // y coordinate of first endpoint
    /** x2 - the x coordinate of the size. */
     private int x2; // x coordinate of second endpoint
 	/** y2 - the y coordinate if the size. */
     private int y2; // y coordinate of second endpoint
 	/** myColor - the color of the shape. */
     private Color myColor; // color of this shape
     
     
    /**
     * default constructor to initialize the coordinates
     * and colors.
     */
     public MyShape(){
     	 this(0, 0, 0, 0, Color.BLACK);
     }
    /**
     * constructor that gets values,validates, and initializes them
     * @param x1 the x coordinate of the position of shape
     * @param y1 the y coordinate of the position if the shape.
     * @param x2 the x coordinate of the size.
     * @param y2 the y coordinate if the size.
     * @param myColor the color of the shape.
     */
     public MyShape(int x1, int y1, int x2, int y2, Color myColor){
      this.x1 = (x1 >= 0 ? x1 : 0);
      this.y1 = (y1 >= 0 ? y1 : 0);
      this.x2 = (x2 >= 0 ? x2 : 0);
      this.y2 = (y2 >= 0 ? y2 : 0);
      this.myColor = myColor; 
   } 
  
  /**
   * @param x1 the x-coordinate of the upper left of the shape
   */
   public void setX1(int x1){
      this.x1 = (x1 >= 0 ? x1 : 0);
   } 

  /**
   * @return the x-coordinate of the upper left of the shape.
   */
   public int getX1(){
      return x1;
   } 

  /**
   * @param x2 the x-coordinate of the second point - which determines the size. 
   */
   public void setX2(int x2){
      this.x2 = (x2 >= 0 ? x2 : 0);
   } 

  /**
   * @return the x-coordinate of the second point - which determines the size. 
   */
   public int getX2(){
      return x2;
   } 

  /** 
   * @param y1 the y-coordinate of the upper left of the shape.
   */
   public void setY1(int y1){
      this.y1 = (y1 >= 0 ? y1 : 0);
   } 

  /**
   * @return the y-coordinate of the upper left of the shape.
   */
   public int getY1(){
      return y1;
   }

  /**
   * @param y2 the y-coordinate of the second point - which determines the size. 
   */
   public void setY2(int y2){
      this.y2 = (y2 >= 0 ? y2 : 0);
   } 

  /** 
   * @return the y-coordinate of the second point - which determines the size. 
   */
   public int getY2(){
      return y2;
   }

  /** 
   * @param myColor the color of the shape.
   */
   public void setColor(Color myColor){
       this.myColor = myColor;
   } 

  /**
   * @return the color of the shape.
   */
   public Color getColor(){
      return myColor;
   } 
   /**
    * abstract method which will be implemented to
    * draw the shapes.
    */
   abstract public void draw(Graphics g);
 }

 