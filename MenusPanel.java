import javax.swing.*;
import java.awt.*;

public class MenusPanel extends JPanel
{
  private GridBagConstraints constraints = new GridBagConstraints();
  
  private ImageIcon play = new ImageIcon ("play2.png");
  private ImageIcon playRoll = new ImageIcon ("play3.png");
  private ImageIcon settings = new ImageIcon ("gear2.png");
  private ImageIcon settingsRoll = new ImageIcon ("gear3.png");
  private ImageIcon instruction = new ImageIcon ("magnifyingglass2.png");  
  private ImageIcon instructionRoll = new ImageIcon ("magnifyingglass3.png"); 
  private ImageIcon highscore = new ImageIcon ("clipboard2.png");
  private ImageIcon highscoreRoll = new ImageIcon ("clipboard3.png");
  
  protected JButton playButton = new JButton(play);
  protected JButton settingsButton = new JButton (settings);
  protected JButton instructionsButton = new JButton (instruction);
  protected JButton scoresButton = new JButton (highscore);
  
  public MenusPanel()
  {
    setLayout (new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    setButtons();
  }
  
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
    
    constraints.insets = new Insets (10, 10, 10, 10);
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