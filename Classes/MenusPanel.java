import javax.swing.*;
import java.awt.*;

/**
 * The "MenusPanel" class which creates an instance of JPanel. JButton objects are added to it. 
 * 
 * @author Chusa Nguyen
 * @author Anqi Wu
 * @version 1.0, May 22 2014. (Created class based on previous code from "MenuFrames.")
 * @version 1.1, June 5, 2014. (Added title.)
 */
public class MenusPanel extends JPanel
{
  /**
   * constraints - reference - Refers to the created instance of GridBagConstraints.
   */
  private GridBagConstraints constraints = new GridBagConstraints();
  /**
   * play - reference - References to the specified image.
   */
  private ImageIcon play = new ImageIcon ("../Images/play2.png");
  /**
   * playRoll - reference - References to the specified image.
   */
  private ImageIcon playRoll = new ImageIcon ("../Images/play3.png");
  /**
   * settings - reference - References to the specified image.
   */
  private ImageIcon settings = new ImageIcon ("../Images/gear2.png");
  /**
   * settingsRoll - reference - References to the specified image.
   */
  private ImageIcon settingsRoll = new ImageIcon ("../Images/gear3.png");
  /**
   * instruction - reference - References to the specified image.
   */
  private ImageIcon instruction = new ImageIcon ("../Images/magnifyingglass2.png");  
  /**
   * instructionRoll - reference - References to the specified image.
   */
  private ImageIcon instructionRoll = new ImageIcon ("../Images/magnifyingglass3.png"); 
  /**
   * highscore - reference - References to the specified image.
   */
  private ImageIcon highscore = new ImageIcon ("../Images/clipboard2.png");
  /**
   * highscoreRoll - reference - References to the specified image.
   */
  private ImageIcon highscoreRoll = new ImageIcon ("../Images/clipboard3.png");
  /**
   * playButton - reference - References to the specified JButton object.
   */
  protected JButton playButton = new JButton(play);
  /**
   * settingsButton - reference - References to the specified JButton object.
   */
  protected JButton settingsButton = new JButton (settings);
  /**
   * instructionsButton - reference - References to the specified JButton object.
   */
  protected JButton instructionsButton = new JButton (instruction);
  /**
   * scoresButton - reference - References to the specified JButton object.
   */
  protected JButton scoresButton = new JButton (highscore);
  
  /**
   * The class constructor. 
   * It sets the panel's layout manager and background, as well as calling the method to set buttons. 
   */
  public MenusPanel()
  {
    setLayout (new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    
    ImageIcon titleImage = new ImageIcon ("../Images/MainMenuTitle.png");
    JLabel title = new JLabel (titleImage);

    constraints.gridwidth = 3;
    constraints.insets = new Insets (10, 10, 10, 10);
    constraints.gridx=1;
    constraints.gridy=0;
    add (title, constraints);
    
    setButtons();
  }
  
  /**
   * The "setButtons" method. It adds the buttons to the panel after setting their specifications (border, location, etc.).
   */
  private void setButtons()
  {      
    playButton.setContentAreaFilled(false);
    playButton.setBorder (null);
    playButton.setRolloverIcon(playRoll);
    
    settingsButton.setContentAreaFilled (false);
    settingsButton.setBorder (null);
    settingsButton.setRolloverIcon (settingsRoll);
    
    instructionsButton.setContentAreaFilled(false);
    instructionsButton.setBorder (null);
    instructionsButton.setRolloverIcon (instructionRoll);
    
    scoresButton.setContentAreaFilled(false);
    scoresButton.setBorder (null);
    scoresButton.setRolloverIcon (highscoreRoll);
    
    constraints.gridwidth = 1;
    constraints.gridx = 2;
    constraints.gridy = 1;
    add(playButton, constraints);
    
    constraints.gridx = 1;
    constraints.gridy = 2;
    add(instructionsButton, constraints);
    
    constraints.gridx = 2;
    add(settingsButton, constraints);
    
    constraints.gridx = 3;
    add (scoresButton, constraints);
  }
}