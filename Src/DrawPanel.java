// DrawPanel.java
// Program that uses classes MyLine, MyOval and MyRectangle to draw
// random shapes
import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
/**
 * This Class handles the creation and drawing of all the shapes.
 * It extends {@link javax.swing.JPanel}.
 * It is managed by the {@link DrawFrame} Class, such that every change that the user 
 * makes regarding the shapes that should be drawn will be updated in this class.
 */
public class DrawPanel extends JPanel{
	/** shapes - the array of shapes that was drawn. */
   private MyShape[] shapes;
   	/** shapeCount - the count of the shapes that was drawn. */
   private int shapeCount;
   	/** statusLabel - will store the statusLabel from the parent window (DrawFrame) in order to upate it. */
   private final JLabel statusLabel;
   	/** maxShapeNum - The maximum shapes that can be drawn. */
   private final int maxShapeNum;
   	/** shapeType - The type of current shape that will be drawn. */
   private int shapeType;
   	/** filledShape - Whether the shape that will be drawn is filled or not. */
   private boolean filledShape; 
    /** currentColor - The current color of the current shape */
   private Color currentColor;
    /** parentWindow - The parent's window (DrawFrame) reference for the pop up messages */
   private final JFrame parentWindow;
    /** currentShape - The Current drawing shape while mouse dragging*/
   private MyShape currentShape;  
   /**
    * constructor, sets the background color to be white.
    * Then it initializes the 'shapes' array with the maximum capacity which was given to it.
    * afterwards the constructor initializes the parentWindow reference.
    * then it initilizes the the statusLable. 
    * Important to say that the first intialization is happening by the default of JVM.
    * It will also create a new MouseHandler {@link MouseHandler} which will handle the drawing on the panel and the statusLabel
    * update and add it to the this panel.
    * @param maxShapeNum the maximum capacity of shapes which could be drawn.
    * @param parentWindow the parents window so that we can send messages using
    * {@link javax.swing.JOptionPane#showMessageDialog} in {@link MouseHandler#mousePressed}.
    * @param statusLabel the {@link DrawFrame} status label that will be updated in {@link MouseHandler#mouseMoved}
    */
   public DrawPanel(int maxShapeNum,DrawFrame parentWindow,JLabel statusLabel){
   	   setBackground(Color.WHITE);
   	   this.maxShapeNum = maxShapeNum;
   	   shapes = new MyShape[maxShapeNum];
   	   this.parentWindow = parentWindow;
   	   this.statusLabel = statusLabel;
   	   MouseHandler handler = new MouseHandler();
   	   addMouseListener(handler);
   	   addMouseMotionListener(handler);
   }
   /** @param currentColor the color that will be drawn*/
   public void setDrawingColor(Color currentColor){
       this.currentColor = currentColor;
   }
   /** @param filledShape whether the shape will be filled or not */
   public void setFilledShape(boolean filledShape){
   	   this.filledShape = filledShape; 
   }
   /** @param shapeType the shape type that will be drawn*/
   public void setShapeType(int shapeType){
   	   this.shapeType = shapeType;
   }
   /** 
   * Public method to keep track of the shape count after pressing the 'Undo' button.
   * It will also use the {@link javax.swing.JComponent#repaint} method that calls the 
   * {@link #paintComponent} method to redraw the panel without the last shape drawn.
   */
   public void clearLastShape(){
   	   if (shapeCount > 0){ // check if there are any shape drawn
   	   	   shapeCount--;
   	   	   repaint();
   	   }
   }
   /**
   * public method to keep track over the Clear button pressing 
   * It will also repaint the panel via {@link javax.swing.JComponent#repaint} method that invokes {@link #paintComponent} method
   * without the shapes that was drawn.
   * It sets the shapeCount to be 0, so that when it will repaint it will repaint the shapes in 
   * shapes[] array up to index smaller than 0 (shapeCount), therefore will not draw anything.
   */
   public void clearDrawing(){
   	   shapeCount = 0;
   	   repaint();  
   }
  /**
   * This private method handles the creation of shapes which was given by the {@link MouseHandler#mousePressed}.
   * it gets all the coordinates needed to create a shape than creates it,
   * using the fileds that we have for current shape drawing(currentColor, filledShape, shapeType).
   * @return returns a new shape acording to the current parameters and the coordinates which was 
   * given to it by the {@link MouseHandler#mousePressed} method in the handler, if an error occured it returns null.
   * @param coordinates the coordinates that needs to be drawn.
   */
   private MyShape createShape(int... coordinates){
   	   // return null if amount of coordinates given is not exactly 4.
   	   if (coordinates.length != 4)
   	   	   return null;
   	   
   	   switch(shapeType){
   	   	case 0:
   	   		return new MyLine(coordinates[0],coordinates[1],
   	   							coordinates[2],coordinates[3],currentColor);
   	   	case 1:
   	   		return new MyOval(coordinates[0],coordinates[1],
   	   							coordinates[2],coordinates[3],currentColor,filledShape);
   	   	case 2:
   	   		return new MyRectangle(coordinates[0],coordinates[1],
   	   							coordinates[2],coordinates[3],currentColor,filledShape);
   	   	default:
   	   		return null;
   	   }
   } 
  /**
   * for each shape in the array, draw the individual shapes.
   * if a shape is being drawn it will update the shape according to the 
   * users mouse movements (which is handled by {@link MouseHandler} )
   */ 
   public void paintComponent(Graphics g){
      super.paintComponent(g);
      
      // drawing and redrawing the shape
      for (int i =0; i < shapeCount; i++){
          shapes[i].draw(g);
      }
      //check if shape is currently drawing
      if(currentShape!=null){
      	  currentShape.draw(g);
      }
   }
   /**
    * private class MouseHandler - handles all the mouse movements/ clicks
    * etc. It extends MouseAdapter bacause we want to use only two overriden methods,
    * and not all of them.
    */
   private class MouseHandler extends MouseAdapter 
   								implements MouseMotionListener{

      /** 
       * This method controls what should be done when the user pressess his mouse.
       * It first gets the event that happened by pressing the mouse button,
       * then it checks whether the press came from the left button press, if it didn't
       * it will return, it also checks if we did not reach our maximun number of shapes
       * which are allowed to be drawn by checking whether the 'shapeCount' is not bigger than the 'maxShapeNum',
       * and if it is we will return.
       * After all the checks are passed successfuly than it will create a new 'currentShape' at the exact 
       * position that was pressed.
       */
      public void mousePressed(MouseEvent event){
      	  // check if the press came from the left muse button.
      	  if (event.getButton() != MouseEvent.BUTTON1)
      	  	  return;
      	  // check if the current shape count is not the maximum shapes.
      	  else if (shapeCount >= maxShapeNum){
      	  	   JOptionPane.showMessageDialog(parentWindow, "Can not exceed the current number of shapes: " + maxShapeNum, "Capacity Overload",
      	  	   	   									JOptionPane.WARNING_MESSAGE);
      	  	  return;
      	  }
      	  //currentShape = new MyShape();// make a new current shape 
      	  currentShape = createShape(event.getX(),event.getY(),
      	  	  	  							event.getX(),event.getY());  	  	  
      }
      /**
       * This method controls what will happen when we pressed the left mouse botton and than released it.
       * it will check if we pressed the left button by checking if there is a currentShape at this moment,
       * if there is not we will return.
       * Afterwards it checks whether the shape that we drew is a 
       * legit shape (meaning it has a size, its upper left corner is not the same as its lower right corner).
       * if it is, this function will add the shape to the array in the shapCount position
       * and will increase the shapeCount by 1.
       * then it will set 'currentShape' to be null, because there is not a shape being drawn anymore.
       */
      public void mouseReleased(MouseEvent event){
      	   // check if the release came from the left mouse button pressed.
      	  if (currentShape == null)
      	  	  return;
      	  // check if the starting point is different then the ending point
      	  // If it is not different that means that we drew a shape that is not legit.
      	  // therefore we will return and will not add the shape.
      	  else if (currentShape.getX1()==currentShape.getX2() &&
      	  	  		 currentShape.getY1()==currentShape.getY2())
      	  	return;
      	  
		  shapes[shapeCount++] = currentShape;
		  currentShape=null;	      	  
      }
      /** 
       * This method controls what happends when the mouse is during a dragging procedure.
       * It first checks whether the dragging started with a left mouse press by checking
       * if the currentShape drawing is null. If it is not it will set the buttom left corner
       * of the shape to be in the current position of the mouse, then calls {@link javax.swing.JComponent#repaint}
       * which calls {@link #paintComponent} .
       * It will call mouseMoved {@link #mouseMoved} anyways in order to update the the statusLabel.
       */
      public void mouseDragged(MouseEvent event){
      	  // check if the dragging came from the left muse button.
      	  if (currentShape != null){
			  currentShape.setX2(event.getX());
			  currentShape.setY2(event.getY());
			  repaint();
      	  }
      	  mouseMoved(event);
      }
      /**
       * This method handles the mouse moving on the panel itself.
       * it sets the statusLabel's text according to the current coordinates that the mouse move on.
       */
      public void mouseMoved(MouseEvent event){
		statusLabel.setText("("+event.getX()+","+event.getY()+")");
      }
   }
} // end class DrawPanel
