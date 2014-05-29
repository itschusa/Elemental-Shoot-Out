import java.awt.*;
import javax.swing.*;

/**
 * The "InstructionsPanel" class which is (by extension) a JPanel object. It displays the instructions of Elemental Shoot-Out. 
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 25 2014. (Created the panel and added instructions.)
 * @version 1.1, May 28 2014. (Replaced JLabel's text with an image."
 */
public class InstructionsPanel extends JPanel
{
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
   * @param instructions - reference - References to the specified JLabel object.
   */
  private void setText()
  {
    JLabel instructions = new JLabel(new ImageIcon("Instructions.png"));
    add (instructions);
  }
  
}