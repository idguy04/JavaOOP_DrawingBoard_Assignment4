// TestDraw.java
// Test application to display a DrawPanel
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 * TestDraw - this class contains the main only.
 * this class simply creates a new DrawFrame to make the program
 * start running.
 */
public class TestDraw
{
   /**
	* main method - Creates a new DrawFrame and initializes it.
	* sets its default close operation, its initial size and its visibility.
	* application - is the Smart Panel which controls and initializes the whole program.
	*/
   public static void main(String[] args){
   	   // create new DrawFrame 
      DrawFrame application = new DrawFrame();      
      application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
      application.setSize(500, 500);
      application.setVisible(true);
   } // end main
} // end class TestDraw
