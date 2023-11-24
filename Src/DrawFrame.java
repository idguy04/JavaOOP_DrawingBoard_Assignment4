import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * DrawFrame Class - The class that manages the frame of the application
 * and manages the buttons and their handlers.
 * It extends {@link javax.swing.JFrame},
 * implements {@link ItemListener} and {@link ActionListener} and all the frame it self
 * then it writes itself to be the listener of the events happening regarding the upper buttons.
 * it will keep track on managing the {@link DrawPanel} which is the smart panel that can draw shapes on itself.
 * The update will happen via the set methods in the {@link DrawPanel#setShapeType}, 
 * {@link DrawPanel#setDrawingColor}, {@link DrawPanel#setFilledShape} so the DrawPanel will know
 * what shape should it draw next.
 */
public class DrawFrame extends JFrame implements ItemListener,ActionListener{
	/** {@link DrawPanel} - the smart panel that can draw shapes on itself and allowing us to do it with our mouse. */
	private final DrawPanel drawPanel;
	/** undoButton - the {@link javax.swing.JButton} that manages the undo - removing the last shape drawn. */
	private final JButton undoButton;
	/** clearButton - the {@link javax.swing.JButton} that clears the DrawPanel completley. */
	private final JButton clearButton;
	/** colorChoices - the {@link javax.swing.JComboBox} containing all the color choices that user can use. */
	private final JComboBox colorChoices;
	/** shapeChoices - the {@link javax.swing.JComboBox} containing all the shape types that the user can draw. */
	private final JComboBox shapeChoices;
	/** filledCheckBox - the {@link javax.swing.JCheckBox} to set whether the shape will be filled or not. */
	private final JCheckBox filledCheckBox;
	/** colors - the actual colors indexed the same as the colorChoices for easy generic use
	* (update the actual color using the same index as in the colorChoice). */
	private static final Color[] colors = {Color.BLACK, Color.BLUE,
								Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN, 
								Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, 
								Color.RED, Color.WHITE, Color.YELLOW};
	/**
	 * DrawFrame constructor -
	 * It takes no parameters and initializes the application.
	 * First it will initialize all the final instance variables (gui elements).
	 * When it initializes the {@link DrawPanel} it send the maximum shape allowed,
	 * a reference to this window's statusLabel (so that we could update it in
	 * mouseMoved event in the DrawPanel because it should not be an instance variable),
	 * It will also send a reference to this window in order to make the JOptionPane messages pop in the middle 
	 * of the window, this happenes in the mousePressed in the DrawPanel's handler event when we reach our maximum
	 * number of shapes.
	 * Then it will call the {@link #setGui} method and give it the statusLabel for further initialization.
	 * maxShapes - the maximum number of shapes that could be drawn.
	 */
	public DrawFrame(){
		// -- init Frame --
		super("Shape Drawings");
		// -- initialize all the gui instance variables --
		// init buttons
		undoButton = new JButton("Undo");
		clearButton = new JButton("Clear");
		// init Check Box
		filledCheckBox = new JCheckBox("Filled");
		// init color combo box
		String[] colorNames = {"Black", "Blue", "Cyan",
					"Dark Gray", "Gray", "Green", "Light Gray", "Magenta", "Orange", 
					"Pink", "Red", "White", "Yellow"};
		colorChoices = new JComboBox<String>(colorNames);
		// init shape combo box
		String[] choicesNames = {"Line","Oval","Rectangle"};
		shapeChoices = new JComboBox<String>(choicesNames);
		// init statusLabel
		JLabel statusLabel = new JLabel("(0,0)");
		// init drawing panel
		int maxShapes = 100;
		drawPanel = new DrawPanel(maxShapes,this,statusLabel);
		
		setGui(statusLabel);
		setHandlers();
	}

	/** 
	* setGui - the method that initializes the frame and sets up the gui components and buttons in their spots. 
	* buttonPanel - is a panel to store all the buttons, we dont need to comunicate with this panel but only
	* with the butttons inside it - therefore it is not an instance variable.
	* @param statusLabel the status label that came from the constructor so we will not have to
	* save it as an instance variable because we do not need to update it via this object, but via the
	* mouseMoved event in the DrawPanel.
	*/
	private void setGui(JLabel statusLabel){
		// --add drawPanel to the center--
		add(drawPanel);
		//--create panel for buttons, add them into it in order, and add the panel to the north--
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(undoButton);
		buttonPanel.add(clearButton);
		buttonPanel.add(colorChoices);
		buttonPanel.add(shapeChoices);
		buttonPanel.add(filledCheckBox);
		add(buttonPanel, BorderLayout.NORTH);
		// --add statusLabel to the south--
		add(statusLabel, BorderLayout.SOUTH);
	}
	/**
	 * setHandlers - the method that writes the needed handlers to the needed gui components.
	 */
	 private void setHandlers(){
		// --add event Listener for the buttons--
		// undo
		undoButton.addActionListener(this);
		// clear
		clearButton.addActionListener(this);
		// color choice
		colorChoices.addItemListener(this);
		// shape choices
		shapeChoices.addItemListener(this);
		// is filled
		filledCheckBox.addItemListener(this);
	 }

	/**
	 * actionPerformed - this method need to be overriden in order to determine what to do when a button is clicked.
	 * it checks whether the event came from the undo button or the clear button pressed, than handles the event accordingly.
	 * If the undo button was pressed it envokes the clearLastShape method {@link DrawPanel#clearLastShape}
	 * of the DrawPanel.
	 * If the clear button was pressed it envokes the clearDrawing method {@link DrawPanel#clearDrawing}
	 * of the DrawPanel.
	 */
	public void actionPerformed(ActionEvent event){
		if (event.getSource() == undoButton)
			drawPanel.clearLastShape();
		else 
			drawPanel.clearDrawing();
	}
	/**
	 * itemStateChanged - this method need to be overridden in order to determine what to do if 
	 * the filled, color, or shape was changed. 
	 * It first checks the event was a selection event or deselection.
	 * If it was selection then it finds the source of it and
	 * handles it accordinly.
	 * If color was pressed it upates the drawingPanels field using the set method - {@link DrawPanel#setFilledShape}
	 * and the index that was selected matching the index of the colors array.
	 * If the shape type was pressed it sets the shape type in the {@link DrawPanel#setShapeType}
	 * accordingly using the index of the item in the combo box.
	 * If the filledCheckBox was pressed, it sets the field accordingly in the {@link DrawPanel#setDrawingColor}.
	 */
	public void itemStateChanged(ItemEvent event){
		if (event.getSource() == filledCheckBox)
				drawPanel.setFilledShape(filledCheckBox.isSelected());
		if (event.getStateChange() == ItemEvent.SELECTED){
			if (event.getSource() == shapeChoices)
				drawPanel.setShapeType(shapeChoices.getSelectedIndex());
			else 
				drawPanel.setDrawingColor(colors[colorChoices.getSelectedIndex()]);
		}
	}
	
}