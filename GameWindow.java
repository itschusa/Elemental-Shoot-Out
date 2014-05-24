import javax.swing.*;
import java.awt.event.*;

/**
 * The GameWindow creates the window, and creates the level panel.
 * 
 * @author Anqi Wu
 * @author baseball435 (ScreenFactory class)
 * @version 1.0, May 21 2014. (creates game window)
 * @version 1.1, May 22, 2014. (added keyboard listener, instantiates vs extends JFrame)
 */
public class GameWindow
{
  //the panel
  private final JFrame gameWindow = new JFrame();
  private final ScreenFactory screenFactory;
  private final LevelMap game;
  private final KeyboardListener keyboardListener;
  
  //constructor, sets title, panel
  public GameWindow(String description, int level)
  {
    gameWindow.setTitle ("Elemental Shoot-Out: " + description);
    gameWindow.setSize (900,600);
    
    gameWindow.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent windowEvent) 
      {
        System.exit(0);
      }
    });
    
    gameWindow.setFocusable (true);
    gameWindow.setResizable (false);
    gameWindow.setLocationRelativeTo (null);
    
    screenFactory = new ScreenFactory (this);
    game = new LevelMap (this);
    keyboardListener = new KeyboardListener ();
    gameWindow.addKeyListener (keyboardListener);
    gameWindow.add (game);
    
    if (level == 1)
    {
      EasyGame screen = new EasyGame (getScreenFactory());
      getScreenFactory().setCurrentScreen (screen);
    }
    else
      System.out.println ("Not Available Yet");
    
    new Thread (game).start();
    
    gameWindow.setVisible (true);
    
    gameWindow.setBackground (new java.awt.Color (255,255,255));
    gameWindow.setContentPane (game);
  }
  
  public KeyListener getKeyboardListener ()
  {
    return keyboardListener;
  }
  
  public ScreenFactory getScreenFactory ()
  {
    return screenFactory;
  }
  
  public JFrame getWindow ()
  {
    return gameWindow;
  } 
}