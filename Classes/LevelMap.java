import javax.swing.*;
import java.awt.*;

/**
 * The LevelMap class represents the panel that updates and draws the game.
 * It also outputs the user's current amount of points, and displays the inventory ring.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @author baseball435
 * @version 1.0, May 21 2014. (the game window that stores arraylist of particles)
 * @version 1.1, May 22, 2014. (now implements Runnable, which updates/paints the current screen)
 * @version 1.2, May 27, 2014. (added temporary main menu button - can close the current window and create the main menu, stacktrace errors vs print errors)
 * @version 1.3, May 28, 2014. (prints total user points)
 * @version 1.4, May 31, 2014. (added ring that tells user which inventory element they are using.)
 * @version 1.5, June 5, 2014. (Somewhat displays how many points were added, JavaDoc)
 * @version 1.6, June 8 2014. (Displays a random fact every 60 points.)
 */
public class LevelMap extends JPanel implements Runnable
{
  /**
   * game - GameWindow - Stores the GameWindow of the LevelMap.
   */
  private final GameWindow game;
  /**
   * stop - boolean - Stores whether the thread should be stopped.
   */
  private boolean stop;
  /**
   * ring - ImageIcon - Stores the image of the ring that specifies which inventory in element is used next.
   */
  private ImageIcon ring = new ImageIcon ("../Images/Ring.png");
  /**
   * index - int - The index number of the fact to be printed. 
   */
  private int index = 0;
  
  
  private boolean[] factsPrinted = new boolean[10];
  private int countPrinted = 0;
  
  
  /**
   * Constructs a new panel with the specified GameWindow.
   * 
   * @param elemental - GameWindow - The game's window.
   */
  public LevelMap (GameWindow elemental)
  {
    game = elemental;
    setFocusable (true);
  }
  
  /**
   * Stops updating the screen.
   */
  public void stop()
  {
    stop = true;
  }
  
  //baseball345
  /**
   * Updates the screen every 2 milliseconds.
   * 
   * @param e - Exception - Catches exceptions.
   */
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
  
  /**
   * Paints the game.
   * The screen, ring, points and status is drawn, respectively.
   * 
   * @param g - Graphics - The Graphics object.
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   * @param tempPoints - int - The temporary amount of points the user won or lost.
   */
  public void paintComponent (Graphics g)
  {
    super.paintComponent(g);
    
    Graphics2D twoDimensional = (Graphics2D) g;
    twoDimensional.setRenderingHint (RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    if (game.getScreenFactory().getCurrentScreen() != null)
      game.getScreenFactory().getCurrentScreen().onDraw (twoDimensional);
    
    twoDimensional.drawImage (ring.getImage(), 30, 479, ring.getImageObserver());
    twoDimensional.drawString("Total Points: " + game.getScreenFactory().getCurrentScreen().getPlayer().getCurrentPoints(), 10, 20);
    
    int tempPoints = game.getScreenFactory().getCurrentScreen().getTempPoints();
    
    if (tempPoints > 0)
      twoDimensional.drawString ("Fascinating! +" + tempPoints, 10, 40);
    else
    {
      if (tempPoints < 0)
        twoDimensional.drawString ("Uh Oh! " + tempPoints, 10, 40);
    }
    
    if (game.getScreenFactory().getCurrentScreen().getPlayer().getCurrentPoints() > 0 && game.getScreenFactory().getCurrentScreen().getPlayer().getCurrentPoints() % 60 == 0)
    {
      if (!factsPrinted[countPrinted])
      {
        index = (int)(Math.random()*10);
        factsPrinted [countPrinted] = true;
      }
      twoDimensional.drawString(Database.factList[game.getLevel() - 1][index], 100, 450);
    }
    
    repaint();
  }
  
  /**
   * The "incrementCounter" method, which increments the value of countPrinted by 1. It resets the value of countPrinted
   * if it becomes larger than 9, the last index of factsPrinted.
   */
  public void incrementCounter()
  {
    countPrinted ++;
    if (countPrinted > 9)
      countPrinted = 0;
  }
}