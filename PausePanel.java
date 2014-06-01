import javax.swing.*;
import java.awt.*;

/**
 * The PausePanel shows the pause screen when user wants to pause.
 * 
 * @author Anqi Wu
 * @version 1.0, May 28, 2014 (two image icons are drawn - clear vs pause)
 * @version 1.1, May 31, 2014 (moved the instantiation to the top)
 */
public class PausePanel extends JPanel
{
  private GameWindow myGame;
  private boolean paused;
  private ImageIcon pauseImage = new ImageIcon("Images/Paused4.png");
  private ImageIcon clearImage = new ImageIcon ("Images/ClearImage.png");
  
  public PausePanel (GameWindow game)
  {
    myGame = game;
    
    setPreferredSize(new Dimension(700,600));
    setBackground(new Color(0,0,0,0));
    setVisible(true);
  }
  
  public void pause ()
  {
    if (paused)
      paused = false;
    else
      paused = true;
  }
  
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