import javax.swing.*;
import java.awt.*;

/**
 * The LevelMap class represents the panel. It stores the targets and inventory.
 * 
 * @author Anqi Wu
 * @author baseball435
 * @version 1.0, May 21 2014. (the game window that stores arraylist of particles)
 * @version 1.1, May 22, 2014. (now implements Runnable, which updates/paints the current screen)
 * @version 1.2, May 27, 2014. (added temporary main menu button - can close the current window and create the main menu, stacktrace errors vs print errors)
 * @version 1.3, May 28, 2014. (prints total user points)
 */
public class LevelMap extends JPanel implements Runnable
{
  private final GameWindow game;
  private boolean stop;
  
  //default constructor
  public LevelMap (GameWindow elemental)
  {
    game = elemental;
    setFocusable (true);
  }
  
  public void stop()
  {
    stop = true;
  }
  
  //baseball345
  public void run()
  {
    while (!stop)
    {
      try
      {
        if (game.getScreenFactory().getCurrentScreen() != null)
          game.getScreenFactory().getCurrentScreen().onUpdate();
        Thread.sleep (2);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  //baseball345
  public void paintComponent (Graphics g)
  {
    super.paintComponent(g);
    
    Graphics2D twoDimensional = (Graphics2D) g;
    twoDimensional.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    if (game.getScreenFactory().getCurrentScreen() != null)
      game.getScreenFactory().getCurrentScreen().onDraw (twoDimensional);
    
    twoDimensional.drawString("Total Points: "+game.getScreenFactory().getCurrentScreen().getPlayer().getCurrentPoints(), 10,20);
    repaint();
  }
}