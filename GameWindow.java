import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * The GameWindow creates the window, and creates the level panel.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @author baseball435 (ScreenFactory class)
 * @version 1.0, May 21 2014. (creates game window)
 * @version 1.1, May 22, 2014. (added keyboard listener, instantiates vs extends JFrame)
 * @version 1.2, May 26 2014. (Coded keyboard listener, modified window listener.)
 * @version 1.3, May 27, 2014. (bug fix with up key, added closeWindow method)
 * @version 1.4, May 28, 2014. (Added side and pause panels (for every level). Medium game also shows up!)
 * @version 1.5, May 30 2014. (Changed access level of paused to public static.)
 */
public class GameWindow
{
  //the panel
  private final JFrame gameWindow = new JFrame();
  private final ScreenFactory screenFactory;
  private final LevelMap game;
  private final SidePanel panel;
  private final PausePanel pause;
  private final KeyboardListener keyboardListener;
  protected static int movement = 0;
  private Thread thread;
  public static boolean paused;
  
  //constructor, sets title, panel
  public GameWindow(String description, int level)
  {
    gameWindow.setTitle ("Elemental Shoot-Out: " + description);
    gameWindow.setSize (900,600);
    gameWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    gameWindow.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent windowEvent) 
      {
        verifyClose();
      }
    });
    
    gameWindow.setFocusable (true);
    gameWindow.setResizable (false);
    gameWindow.setLocationRelativeTo (null);
    
    screenFactory = new ScreenFactory (this);
    
    game = new LevelMap (this);
    panel = new SidePanel(this);
    pause = new PausePanel (this);
    
    keyboardListener = new KeyboardListener(){
      public void keyPressed (KeyEvent event)
      {
        keys[event.getKeyCode()] = true;
        act();
      }
      public void act()
      {
        if (isKeyPressed(37))
          movement = 37;
        else if (isKeyPressed (39))
          movement = 39;
        else
        {
          if (isKeyPressed(38))
            movement = 38;
        }
      }
    };
    
    gameWindow.addKeyListener (keyboardListener);
    
    LevelScreen screen = new LevelScreen(getScreenFactory());
    if (level == 1)
    {
      screen = new EasyGame (getScreenFactory());
      getScreenFactory().setCurrentScreen (screen);
    }
    else if (level == 2)
    {
      screen = new MediumGame (getScreenFactory());
      getScreenFactory().setCurrentScreen (screen);
    }
    else
      System.out.println ("Not Available Yet");
    
    getScreenFactory().showScreen (screen);
    
    thread = new Thread (game);
    thread.start();
    
    gameWindow.setContentPane (game);
    gameWindow.setLayout (new BorderLayout());
    gameWindow.setBackground (new java.awt.Color (255,255,255));
    gameWindow.add (pause, BorderLayout.LINE_START);
    gameWindow.add (panel, BorderLayout.LINE_END);
    gameWindow.setVisible (true);
  }
  
  public void closeWindow()
  {
    gameWindow.dispose();
    game.stop();
  }
  
  public void pause()
  {
    if (paused)
    {
      paused = false;
      gameWindow.requestFocusInWindow();
    }
    else
      paused = true;
    pause.pause();
  }
  
  public KeyboardListener getKeyboardListener ()
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
  
  public int getMove()
  {
    return movement;
  }
  
  private void verifyClose()
  {
    int choice = JOptionPane.CANCEL_OPTION;
    choice = JOptionPane.showConfirmDialog (new JOptionPane(), "Would you like to exit this game?", "Confirm Quit",
                                            JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION)
      System.exit(0);
  }
}