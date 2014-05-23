import java.awt.*;
import javax.swing.*;

public class LevelsPanel extends JPanel
{
  private GridBagConstraints constraints = new GridBagConstraints();
  private ImageIcon easy = new ImageIcon ("Easy.png");
  private ImageIcon easyRoll = new ImageIcon ("Easy2.png");
  private ImageIcon medium = new ImageIcon ("Medium.png");
  private ImageIcon mediumRoll = new ImageIcon ("Medium2.png");
  private ImageIcon hard = new ImageIcon ("Hard.png");
  private ImageIcon hardRoll = new ImageIcon ("Hard2.png");
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
  
  
  public LevelsPanel()
  {
    setLayout(new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    setButtons();
  }
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