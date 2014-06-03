import javax.swing.*;
import java.awt.*;

/**
 * The PausePanel shows the pause screen when user wants to pause.
 * 
 * @author Anqi Wu
 * @version 1.0, May 28, 2014 (two image icons are drawn - clear vs pause)
 * @version 1.1, May 31, 2014 (moved the instantiation to the top)
 * @version 1.2, June 2, 2014 (JavaDoc)
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
   * Creates a new PausePanel object. Sets the background to transparent.
   * 
   * @param game - GameWindow - The current GameWindow.
   */
  public PausePanel (GameWindow game)
  {
    myGame = game;
    
    setPreferredSize(new Dimension(700,600));
    setBackground(new Color(0,0,0,0));
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
   * Paints the graphics of the pause screen.
   * When paused is true, paints the pause screen.
   * When paused is false, paints the clear screen.
   * 
   * @param g - Graphics - The Graphics object.
   */
  public void paintComponent (Graphics g)
  {
    super.paintComponent (g);
    
    if (paused)
      g.drawImage (pauseImage.getImage(), 0, 0, pauseImage.getImageObserver()); 
    else
      g.drawImage (clearImage.getImage(), 0, 0, clearImage.getImageObserver()); 
    
    repaint();
  }
  
}