import java.awt.*;
import javax.swing.*;

/**
 * The "InstructionsPanel" class which is (by extension) a JPanel object. It displays the instructions of Elemental Shoot-Out. 
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 25 2014. (Created the panel and added instructions.)
 * @version 1.1, May 28 2014. (Replaced JLabel's text with an image.)
 * @version 1.2, June 8 2014. (Added button to return to menu.)
 */
public class InstructionsPanel extends JPanel
{
  /**
   * menuButton - reference - References to the specified JButton object. 
   */
  protected JButton menuButton = new JButton ("Return to Menu");
  
  /**
   * The constructor. It sets the layout manager of the panel, its background colour, and calls the appropriate methods for setting content to the panel. 
   */
  public InstructionsPanel()
  {
    setLayout(new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    setText();
  }
  /**
   * The "setText" method which adds text (the program's gameplay instructions) in the form of a JLabel to the panel.
   * 
   * @param instructions reference - References to the specified JLabel object.
   * @param constraints reference - References to the specified GridBagConstraints object.
   */
  private void setText()
  {
    JLabel instructions = new JLabel(new ImageIcon("../Images/Instructions.png"));
    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 1;
    constraints.gridwidth = 5;
    constraints.gridheight = 5;
    add (instructions, constraints);
    
    constraints.gridwidth = 1;
    constraints.gridheight = 1;
    constraints.gridx = 4;
    constraints.gridy = 6;
    add(menuButton, constraints);
    
  }
}