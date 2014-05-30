import java.awt.*;
import javax.swing.*;

/**
 * The "LevelsPanel class which creates an instance of a JPanel. JButton objects are added to it. 
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 22 2014. (Created class based on previous code from "MenuFrames.")
 */
public class LevelsPanel extends JPanel
{
  /**
   * constraints - reference - Reference variable to the GridBagConstraints object created. 
   */
  private GridBagConstraints constraints = new GridBagConstraints();
  /**
   * easy - reference - Reference variable to the image of the same name.
   */
  private ImageIcon easy = new ImageIcon ("Images/Easy.png");
   /**
   * easyRoll - reference - References to the image used for the roll-over mouse action of the easy button.
   */
  private ImageIcon easyRoll = new ImageIcon ("Images/Easy2.png");
   /**
   * medium - reference - Reference variable to the image of the same name. 
   */
  private ImageIcon medium = new ImageIcon ("Images/Medium.png");
   /**
   * mediumRoll - reference - References to the image used for the roll-over mouse action of the medium button.
   */
  private ImageIcon mediumRoll = new ImageIcon ("Images/Medium2.png");
   /**
   * hard - reference - Reference variable to the image of the same name. 
   */
  private ImageIcon hard = new ImageIcon ("Images/Hard.png");
   /**
   * hardRoll - reference - References to the image used for the roll-over mouse action of the difficult button.
   */
  private ImageIcon hardRoll = new ImageIcon ("Images/Hard2.png");
  /**
   * easyButton - reference - Reference variable to the corresponding JButton object.
   */
  protected JButton easyButton = new JButton (easy);
  /**
   * mediumButton - reference - Reference variable to the corresponding JButton object.
   */
  protected JButton mediumButton = new JButton (medium);
  /**
   * difficultButton - reference - Reference variable to the corresponding JButton object.
   */
  protected JButton difficultButton = new JButton (hard);
  
  /**
   * The class constructor. It set's the panel's layout, background colour, and calls the method to set the buttons. 
   */
  public LevelsPanel()
  {
    setLayout(new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    setButtons();
  }
  
  /**
   * The "setButtons" method. It sets the buttons on the panel and configures their general appearance (border, spacing, etc.).
   */
  private void setButtons()
  {
    easyButton.setContentAreaFilled(false);
    easyButton.setBorder (null);
    easyButton.setRolloverIcon(easyRoll);
    
    mediumButton.setContentAreaFilled(false);
    mediumButton.setBorder (null);
    mediumButton.setRolloverIcon(mediumRoll);
    
    difficultButton.setContentAreaFilled(false);
    difficultButton.setBorder (null);
    difficultButton.setRolloverIcon(hardRoll);
    
    constraints.insets = new Insets (50, 50, 50, 50);
    constraints.gridy = 1;
    constraints.gridx = 1;
    add(easyButton, constraints);
    constraints.gridx = 2;
    add(mediumButton, constraints);
    constraints.gridx = 3;
    add(difficultButton, constraints);
  }
}