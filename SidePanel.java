import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The SidePanel stores all the components of the side panel while the user is playing the game.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 28, 2014. (added panel to the side, the main menu, pause and sound buttons. Sound button not yet functional)
 * @version 1.1, May 30 2014. (Fixed null pointer exception described in MenuFrames.)
 */
public class SidePanel extends JPanel
{
  private GameWindow myGame;
  private ImageIcon menuIcon = new ImageIcon ("list_3.png");
  private ImageIcon pauseIcon = new ImageIcon ("circle_pause_2.png");
  private ImageIcon soundIcon = new ImageIcon ("volume_icon_1.png");
  private GridBagConstraints constraints = new GridBagConstraints();
  
  public SidePanel (GameWindow game)
  {
    myGame = game;
    
    setPreferredSize(new Dimension(200,600));
    setBackground(new Color(0,0,0,125));
    
    JButton mainMenu = new JButton (menuIcon);
    mainMenu.setPreferredSize(new Dimension (64, 64));
    mainMenu.setContentAreaFilled(false);
    mainMenu.setBorder (null);
    mainMenu.setRolloverIcon (new ImageIcon ("list_roll.png"));
    
    JButton pauseButton = new JButton (pauseIcon);
    pauseButton.setPreferredSize(new Dimension (64, 64));
    pauseButton.setContentAreaFilled(false);
    pauseButton.setBorder (null);
    pauseButton.setRolloverIcon (new ImageIcon ("circle_pause_roll.png"));
    
    JButton soundButton = new JButton (soundIcon);
    soundButton.setPreferredSize(new Dimension (64, 64));
    soundButton.setContentAreaFilled(false);
    soundButton.setBorder (null);
    soundButton.setRolloverIcon (new ImageIcon ("volume_icon_roll.png"));
    
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
        myGame.getMenus().setVisible(true);
        myGame.closeWindow();
      }});
    
    pauseButton.addActionListener (new ActionListener ()
                                  {
      public void actionPerformed (ActionEvent e)      { 
        myGame.pause();
      }});
    
//    soundButton.addActionListener (new ActionListener ()
//                                     {
//      });
  }
}