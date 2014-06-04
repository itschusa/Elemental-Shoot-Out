import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The SidePanel stores all the components of the side panel while the user is playing the game.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 28, 2014. (added panel to the side, the main menu, pause and sound buttons. Sound button not yet functional)
 * @version 1.1, May 30 2014. (Fixed null pointer exception described in MenuFrames and added ActionListener to sound.)
 * @version 1.2, June 4, 2014. (Music now does not lose focus of the game)
 */
public class SidePanel extends JPanel
{
  /**
   * myGame - reference - References to the corresponding GameWindow object. 
   */
  private GameWindow myGame;
  /**
   * menuIcon - reference - References to the corresponding ImageIcon object.
   */
  private ImageIcon menuIcon = new ImageIcon ("../Images/list_3.png");
  /**
   * pauseIcon - reference - References to the corresponding ImageIcon object.
   */
  private ImageIcon pauseIcon = new ImageIcon ("../Images/circle_pause_2.png");
  /**
   * soundIcon - reference - References to the corresponding ImageIcon object.
   */
  private ImageIcon soundIcon = new ImageIcon ("../Images/volume_icon_1.png");
  /**
   * constraints - reference - References to the corresponding ImageIcon object.
   */
  private GridBagConstraints constraints = new GridBagConstraints();
  /**
   * createdSettings - boolean - Represents whether or not an instance of the SettingsPanel class has already been created.
   */
  private boolean createdSettings = false;
  
  /**
   * The class constructor. 
   * The constructor sets the panel specifications, adds the buttons with the specifications, and sets the 
   * class's layout manager. Each button has own ActionListener object. 
   * 
   * @param game GameWindow - The current instance of GameWindow being used is passed through.
   */
  public SidePanel (GameWindow game)
  {
    myGame = game;
    
    setPreferredSize(new Dimension(200,600));
    setBackground(new Color(0,0,0,125));
    
    JButton mainMenu = new JButton (menuIcon);
    mainMenu.setPreferredSize(new Dimension (64, 64));
    mainMenu.setContentAreaFilled(false);
    mainMenu.setBorder (null);
    mainMenu.setRolloverIcon (new ImageIcon ("../Images/list_roll.png"));
    
    JButton pauseButton = new JButton (pauseIcon);
    pauseButton.setPreferredSize(new Dimension (64, 64));
    pauseButton.setContentAreaFilled(false);
    pauseButton.setBorder (null);
    pauseButton.setRolloverIcon (new ImageIcon ("../Images/circle_pause_roll.png"));
    
    JButton soundButton = new JButton (soundIcon);
    soundButton.setPreferredSize(new Dimension (64, 64));
    soundButton.setContentAreaFilled(false);
    soundButton.setBorder (null);
    soundButton.setRolloverIcon (new ImageIcon ("../Images/volume_icon_roll.png"));
    
    setLayout (new GridBagLayout());
    
    add (mainMenu);
    add (pauseButton);
    
    constraints.insets = new Insets (10, 10, 10, 10);
    constraints.gridx = 1;
    constraints.gridy = 1;
    add(mainMenu, constraints);
    constraints.gridy = 2;
    add(pauseButton, constraints);
    constraints.gridy = 3;
    add(soundButton, constraints);
    
    setVisible(true);
    
    mainMenu.addActionListener (new ActionListener ()
                                  {
      public void actionPerformed (ActionEvent e)      { 
        if (createdSettings)
          myGame.getMenus().removeSettings();
        myGame.getMenus().setVisible(true);
        myGame.closeWindow();
      }});
    
    pauseButton.addActionListener (new ActionListener ()
                                  {
      public void actionPerformed (ActionEvent e)      { 
        myGame.pause();
      }});
    
    soundButton.addActionListener (new ActionListener ()
                                     {
      public void actionPerformed (ActionEvent e) {
        myGame.getWindow().requestFocusInWindow();
        if (!SettingsPanel.isOn)
        {
          if (!SettingsPanel.musicInitialized)
          {
            myGame.getMenus().setSettings();
            createdSettings = true;
          }
          myGame.getMenus().getSettings().toggleOn();
          SettingsPanel.isOn = true;
        }
        else
        {
          if (SettingsPanel.musicInitialized)
            myGame.getMenus().getSettings().toggleOff();
          SettingsPanel.isOn = false;
        }
      }});
  }
}