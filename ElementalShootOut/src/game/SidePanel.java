package game;

import menus.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The SidePanel stores all the components of the side panel while the user is playing the game.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 28 2014. (added panel to the side, the main menu, pause and sound buttons. Sound button not yet functional)
 * @version 1.1, May 30 2014. (Fixed null pointer exception described in MenuFrames and added ActionListener to sound.)
 * @version 1.2, June 4, 2014. (Music now does not lose focus of the game.)
 * @version 1.3, June 6, 2014. (Prompts user for name before returning to main menu. Option to cancel and return to game if main menu clicked.)
 * @version 1.4, June 8 2014. (Moved component implementation from constructor to private method.)
 * @version 1.5, June 10 2014. (Sound button actually shows whether the music is on or off, javadoc)
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
  private ImageIcon menuIcon = new ImageIcon ("Images/list_3.png");
  /**
   * pauseIcon - reference - References to the corresponding ImageIcon object.
   */
  private ImageIcon pauseIcon = new ImageIcon ("Images/circle_pause_2.png");
  /**
   * soundIcon - reference - References to the corresponding ImageIcon object.
   */
  private ImageIcon soundIcon = new ImageIcon ("Images/volume_icon_1.png");
  /**
   * soundIcon2 - ImageIcon - Stores the image of the sound icon when mute.
   */
  private ImageIcon soundIcon2 = new ImageIcon ("Images/volume_icon_roll.png");
  /**
   * constraints - reference - References to the corresponding ImageIcon object.
   */
  private GridBagConstraints constraints = new GridBagConstraints();
  /**
   * createdSettings - boolean - Represents whether or not an instance of the SettingsPanel class has already been created.
   */
  private boolean createdSettings = false;
  /**
   * mainMenu - JButton - Stores the main menu button that allows the user to return to the main menu.
   */
  private JButton mainMenu = new JButton (menuIcon);
  /**
   * pauseButton - JButton - Stores the pause button that allows the user to pause/unpause the game.
   */
  private JButton pauseButton = new JButton (pauseIcon);
  /**
   * soundButton - JButton - Stores the sound button that allows the user to turn on/off the sound.
   */
  private final JButton soundButton = new JButton ();
  
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
    setLayout (new GridBagLayout());
    
    setComponents();
  }
  
  
  /**
   * The "setComponents" method, which declares and adds the various components to the panel.
   * This method creates each JButton, adds ActionListener objects, and declares their specifications (location, size, etc).
   * There are three anonymous classes that implement ActionListener. The first listens for when the main menu button is
   * pressed and then asks the user if they want to save their score and then returns them to the main menu. The second
   * listens for when the pause button is pressed and then pauses the game. Finally, the third listens for when the sound
   * button is pressed and then turn the sound on or off.
   * 
   * @param e - ActionEvent - The ActionEvent object.
   */
  private void setComponents()
  {
    mainMenu.setPreferredSize(new Dimension (64, 64));
    mainMenu.setContentAreaFilled(false);
    mainMenu.setBorder (null);
    mainMenu.setRolloverIcon (new ImageIcon ("Images/list_roll.png"));
    
    pauseButton.setPreferredSize(new Dimension (64, 64));
    pauseButton.setContentAreaFilled(false);
    pauseButton.setBorder (null);
    pauseButton.setRolloverIcon (new ImageIcon ("Images/circle_pause_roll.png"));
    
    soundButton.setPreferredSize(new Dimension (64, 64));
    soundButton.setContentAreaFilled(false);
    soundButton.setBorder (null);
    
    if (SettingsPanel.isOn)
    {
      soundButton.setIcon (soundIcon);
      soundButton.setRolloverIcon (soundIcon2);
    }
    else
    {
      soundButton.setIcon (soundIcon2);
      soundButton.setRolloverIcon (soundIcon);
    }
    
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
        if (!myGame.askName())
        {
          myGame.getWindow().requestFocusInWindow();
          return;
        }
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
          if (!SettingsPanel.getMusicInitialized())
          {
            myGame.getMenus().settings();
            createdSettings = true;
          }
          myGame.getMenus().getSettings().toggleOn();
          soundButton.setIcon (soundIcon);
          soundButton.setRolloverIcon (soundIcon2);
          SettingsPanel.isOn = true;
        }
        else
        {
          if (SettingsPanel.getMusicInitialized())
          {
            myGame.getMenus().getSettings().toggleOff();
            soundButton.setIcon (soundIcon2);
            soundButton.setRolloverIcon (soundIcon);
            SettingsPanel.isOn = false;
          }
        }
      }});
  }
}