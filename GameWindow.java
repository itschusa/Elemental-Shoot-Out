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
 * @version 1.5, May 30 2014. (Changed access level of paused to public static, added frame and its accessor method. Added argument to constructor param list.)
 */
public class GameWindow
{
  /**
   * gameWindow - reference - Reference variable to a new instance of a JFrame object. 
   */
  private final JFrame gameWindow = new JFrame();
  /**
   * screenFactory - reference - Reference variable to the corresponding ScreenFactory object.
   */
  private final ScreenFactory screenFactory;
  /**
   * game - reference - Reference variable to the corresponding LevelMap object.
   */
  private final LevelMap game;
  /**
   * panel - reference - Reference variable to the corresponding SidePanel object.
   */
  private final SidePanel panel;
  /**
   * pause - reference - Reference variable to the corresponding PausePanel object.
   */
  private final PausePanel pause;
  /**
   * keyboardListener - reference - Reference variable to the corresponding KeyboardListener object.
   */
  private final KeyboardListener keyboardListener;
  /**
   * movement - static int - Represents the direction the user wants to move in. 
   */
  protected static int movement = 0;
  /**
   * thread - reference - References to the corresponding Thread object.
   */
  private Thread thread;
  /**
   * paused - static boolean - Represents whether or not the game in currently being paused. 
   */
  public static boolean paused = false;
  /**
   * frame - reference - Represents the corresponding MenuFrames object. 
   */
  private MenuFrames frame;
  
  //constructor, sets title, panel
  /**
   * The class constructor. 
   * It sets the window's title and the appropriate panels.
   * 
   * @param description  String - Describes the current game level.
   * @param level int - Represents the current level difficulty.
   * @param frame MenuFrames - The current instance of MenuFrames is passed through to give this class access to it. 
   */
  public GameWindow(String description, int level, MenuFrames frame)
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
    this.frame = frame;
    
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
  
  /**
   * The "closeWindow" method, which disposes of the current instance of GameWindow and brings the current game to a stop. 
   */
  public void closeWindow()
  {
    gameWindow.dispose();
    game.stop();
  }
  
  /**
   * The "pause" method, which allows the game to be paused. 
   */
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
  
  /**
   * The "getKeyboardListener" method, which allows public access the KeyboardListener object attached to this GameWindow. 
   * 
   * @return Returns the instance of the KeyboardListner object attached to this GameWindow.
   */
  public KeyboardListener getKeyboardListener ()
  {
    return keyboardListener;
  }
  
  /**
   * The "getScreenFactory" method, which allows public access to the current ScreenFactory in use.
   * 
   * @return Returns the instance of the ScreenFactory in use. 
   */
  public ScreenFactory getScreenFactory ()
  {
    return screenFactory;
  }
  
  /**
   * The "getWindow" method, which allows public access to the JFrame object in use.
   * 
   * @return Returns the current instance of the JFrame object in use. 
   */
  public JFrame getWindow ()
  {
    return gameWindow;
  } 
  
  /**
   * The "getMove" method, which allows public access to the current value of "movement".
   * 
   * @return Returns the current value of "movement".
   */
  public int getMove()
  {
    return movement;
  }
  
  /**
   * The "verifyClose" method, which confirms whether or not the user wishes to leave the game before exiting the program. 
   * 
   * @param choice int - Represents the user's choice. 
   */
  private void verifyClose()
  {
    int choice = JOptionPane.CANCEL_OPTION;
    choice = JOptionPane.showConfirmDialog (new JOptionPane(), "Would you like to exit this game?", "Confirm Quit",
                                            JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION)
      System.exit(0);
  }
  
  /**
   * The "getMenus" method, which allows public access to the current instance of MenuFrames.
   * 
   * @return Returns the instance of MenuFrames in use. 
   */
  public MenuFrames getMenus()
  {
    return frame;
  }
}