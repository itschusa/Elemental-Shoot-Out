import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The LevelMap class represents the panel. It stores the targets and inventory.
 * 
 * @author Anqi Wu
 * @author baseball435
 * @version 1.0, May 21 2014. (the game window that stores arraylist of particles)
 * @version 1.1, May 22, 2014. (now implements Runnable, which updates/paints the current screen)
 */
public class LevelMap extends JPanel implements Runnable
{
  private final GameWindow game;
  
  //default constructor
  public LevelMap (GameWindow elemental)
  {
    game = elemental;
    setFocusable (true);
  }
  
  //baseball345
  public void run()
  {
    while (true)
    {
      try
      {
        if (game.getScreenFactory().getCurrentScreen() != null)
          game.getScreenFactory().getCurrentScreen().onUpdate();
        Thread.sleep (1000);
      }
      catch (Exception e)
      {
        System.out.println (e);
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
    
    repaint();
  }
}