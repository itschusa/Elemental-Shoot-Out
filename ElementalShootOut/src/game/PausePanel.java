package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The PausePanel shows the pause screen when user wants to pause, and also the win screen when the user wins.
 * 
 * @author Anqi Wu
 * @version 1.0, May 28 2014 (two image icons are drawn - clear vs pause)
 * @version 1.1, May 31 2014 (moved the instantiation to the top)
 * @version 1.2, June 2 2014 (JavaDoc)
 * @version 1.3, June 4 2014 (Stores the win images, displays win screen, added showWin and resetLevel methods)
 * @version 1.4, June 10 2013 (JavaDoc)
 */
public class PausePanel extends JPanel
{
  /**
   * myGame - GameWindow - Stores the current game window.
   */
  private GameWindow myGame;
  /**
   * paused - boolean - Stores whether the current game is paused.
   */
  private boolean paused;
  /**
   * pauseImage - ImageIcon - Stores the image of the pause screen.
   */
  private ImageIcon pauseImage = new ImageIcon("Images/Paused4.png");
  /**
   * clearImage - ImageIcon - Stores the image when 'paused' is not true.
   */
  private ImageIcon clearImage = new ImageIcon ("Images/ClearImage.png");
  /**
   * winImage - ImageIcon - Stores the win screen.
   */
  private ImageIcon winImage1 = new ImageIcon ("Images/Win1.png");
  /**
   * winImage - ImageIcon - Stores the win screen.
   */
  private ImageIcon winImage2 = new ImageIcon ("../Images/Win2.png");
  /**
   * winImage - ImageIcon - Stores the win screen.
   */
  private ImageIcon winImage3 = new ImageIcon ("Images/Win3.png");
  /**
   * level - int - Stores the level to continue to.
   */
  private int level;
  /**
   * buttonPanel - JPanel - Stores the panel that the button is contained in.
   */
  private JPanel buttonPanel = new JPanel();
  /**
   * continueButton - JButton - Stores the continue button.
   */
  private JButton continueButton = new JButton ("Continue");
  
  /**
   * Creates a new PausePanel object. Sets the background to transparent.
   * 
   * @param game - GameWindow - The current GameWindow.
   */
  public PausePanel (GameWindow game)
  {
    myGame = game;
    
    setPreferredSize(new Dimension(700, 600));
    setBackground(new Color(0, 0, 0, 0));
    setVisible(true);
  }
  
  /**
   * Sets paused to the opposite of the current stored value.
   * If paused = true, sets paused to false.
   * If paused = false, sets paused to true.
   */
  public void pause ()
  {
    if (paused)
      paused = false;
    else
      paused = true;
  }
  
  /**
   * Shows the win screen and creates a button that allows the game to continue.
   * This method also creates an anonymous class that implements ActionListener. This class listens for when the user
   * presses the continue button and then changes the screen to the next level.
   * 
   * @param int level - Integer representation of the level to continue to.
   * @param e reference - Reference variable for the specified ActionEvent object. 
   */
  public void showWin (int level)
  {
    this.level = level;
    buttonPanel.setBackground (new Color (0, 0, 0));
    buttonPanel.add(continueButton);
    add (buttonPanel);
    buttonPanel.setVisible (true);
    validate();
    repaint();
    
    buttonPanel.requestFocusInWindow();
    
    continueButton.addActionListener (new ActionListener ()
                                        {
      public void actionPerformed (ActionEvent e)      { 
        resetLevel ();
      }});
  }
  
  /**
   * Resets the level and continues the game to the next level.
   */
  public void resetLevel ()
  {
    remove(buttonPanel);
    myGame.changeScreen (level);
    level = 0;
    repaint();
  }
  
  /**
   * Paints the graphics of the pause screen or the win screen.
   * When the level is greater than 0, the win screen is painted.
   * Otherwise, when paused is true, paints the pause screen.
   * When paused is false, paints the clear screen.
   * 
   * @param g - Graphics - The Graphics object.
   */
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    
    if (level == 2)
      g.drawImage (winImage1.getImage(), 0, 0, winImage1.getImageObserver()); 
    else if (level == 3)
      g.drawImage (winImage2.getImage(), 0, 0, winImage2.getImageObserver());
    else if (level == 4)
      g.drawImage (winImage3.getImage(), 0, 0, winImage3.getImageObserver());
    else
    {
      if (paused)
        g.drawImage (pauseImage.getImage(), 0, 0, pauseImage.getImageObserver()); 
      else
        g.drawImage (clearImage.getImage(), 0, 0, clearImage.getImageObserver()); 
    }
    
    repaint();
  } 
}