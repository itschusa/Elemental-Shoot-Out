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
 * @version 1.1, May 22 2014. (added keyboard listener, instantiates vs extends JFrame)
 * @version 1.2, May 26 2014. (Coded keyboard listener, modified window listener.)
 * @version 1.3, May 27 2014. (bug fix with up key, added closeWindow method)
 * @version 1.4, May 28 2014. (Added side and pause panels (for every level). Medium game also shows up!)
 * @version 1.5, May 30 2014. (Changed access level of paused to public static, added frame and its accessor method. Added argument to constructor param list.)
 * @version 1.6, May 31 2014. (Added difficult level)
 * @version 1.7, June 4 2014. (Added keyReleased = for shooting, to eliminate player shooting randomly, win, loseFocus and changeScreen methods)
 * @version 1.8, June 5 2014. (JavaDoc)
 */
public class GameWindow
{
  /**
   * gameWindow - JFrame - Reference variable to a new instance of a JFrame object. 
   */
  private final JFrame gameWindow = new JFrame();
  /**
   * screenFactory - ScreenFactory - Reference variable to the corresponding ScreenFactory object.
   */
  private final ScreenFactory screenFactory;
  /**
   * game - LevelMap - Reference variable to the corresponding LevelMap object.
   */
  private final LevelMap game;
  /**
   * panel - SidePanel - Reference variable to the corresponding SidePanel object.
   */
  private final SidePanel panel;
  /**
   * pause - PausePanel - Reference variable to the corresponding PausePanel object.
   */
  private final PausePanel pause;
  /**
   * keyboardListener - KeyboardListener - Reference variable to the corresponding KeyboardListener object.
   */
  private final KeyboardListener keyboardListener;
  /**
   * movement - static int - Represents the direction the user wants to move in. 
   */
  protected static int movement = 0;
  /**
   * movement2 - static int - Represents the secondary key the user presses.
   */
  protected static int movement2 = 0;
  /**
   * thread - Thread - References to the corresponding Thread object.
   */
  private Thread thread;
  /**
   * paused - static boolean - Represents whether or not the game in currently being paused. 
   */
  public static boolean paused = false;
  /**
   * frame - MenuFrames - Represents the corresponding MenuFrames object. 
   */
  private MenuFrames frame;
  
  /**
   * The class constructor. 
   * It sets the window's title and the appropriate panels.
   * A 900 by 600 pixel window is created.
   * A KeyListener is added to get user input.
   * The appropriate LevelScreen is added and displayed.
   * A new panel is added to update the game.
   * SidePanel and PausePanel are added as well. 
   * 
   * @param description  String - Describes the current game level.
   * @param level int - Represents the current level difficulty.
   * @param frame MenuFrames - The current instance of MenuFrames is passed through to give this class access to it. 
   */
  public GameWindow(String description, int level, MenuFrames frame)
  {
    //set title
    gameWindow.setTitle ("Elemental Shoot-Out: " + description);
    //set frame size
    gameWindow.setSize (900,600);
    //set close operation
    gameWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    //set window listener
    gameWindow.addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent windowEvent) 
      {
        verifyClose();
      }
    });
    //set focusable
    gameWindow.setFocusable (true);
    //set resizable
    gameWindow.setResizable (false);
    //set location (center)
    gameWindow.setLocationRelativeTo (null);
    
    //set frame
    this.frame = frame;
    //create new ScreenFactory, LevelMap, SidePanel and PausePanel
    screenFactory = new ScreenFactory (this);
    game = new LevelMap (this);
    panel = new SidePanel(this);
    pause = new PausePanel (this);
    
    //add keyboard listener
    keyboardListener = new KeyboardListener(){
      public void keyReleased (KeyEvent event)
      {
        pressedKeys[event.getKeyCode()] = false;
        releasedKeys[event.getKeyCode()] = true;
        act();
      }
      
      public void keyPressed (KeyEvent event)
      {
        pressedKeys[event.getKeyCode()] = true;
        act();
      }
      
      public void act()
      {
        if (isKeyPressed(37))
          movement = 37;
        else
        {
          if (isKeyPressed (39))
            movement = 39;
        }
        
        if (isKeyReleased(38))
          movement2 = 38;
        
        refreshReleased();
      }
    };
    
    //add keylistener
    gameWindow.addKeyListener (keyboardListener);
    
    //create new level screen
    LevelScreen screen;
    //easy, medium or hard
    if (level == 1)
      screen = new EasyGame (getScreenFactory());
    else if (level == 2)
      screen = new MediumGame (getScreenFactory());
    else
      screen = new DifficultGame (getScreenFactory());
    
    //set and show screen (game)
    getScreenFactory().setCurrentScreen (screen);
    getScreenFactory().showScreen (screen);
    
    //create a new thread and start it
    thread = new Thread (game);
    thread.start();
    
    //set panel, layout, background color, add pause panel and side panel, set visible to true
    gameWindow.setContentPane (game);
    gameWindow.setLayout (new BorderLayout());
    gameWindow.setBackground (new java.awt.Color (255,255,255));
    gameWindow.add (pause, BorderLayout.LINE_START);
    gameWindow.add (panel, BorderLayout.LINE_END);
    gameWindow.setVisible (true);
  }
  
  /**
   * Shows the win screen for the level specified in the parameter.
   * 
   * @param level - int - The level that has been won.
   */
  public void win (int level)
  {
    pause.showWin (level);
  }
  
  /**
   * Requests that the side panel be in focus.
   */
  public void loseFocus()
  {
    panel.requestFocusInWindow();
  }
  
  /**
   * Sets the current screen to another LevelScreen depending on the level as specified in the parameter.
   * Changes the launcher icon as well.
   * 
   * @param level - int - The level to show.
   * @param screen - LevelScreen - The new screen.
   * @param points - int - The user's points to transfer.
   */
  public void changeScreen (int level)
  {
    //no change
    if (level == 0)
      return;
    
    //creates a new screen
    LevelScreen screen = new MediumGame (getScreenFactory());
    //stores the points
    int points = getScreenFactory().getCurrentScreen().getPlayer().getCurrentPoints();
    
    if (level == 2 || level == 3)
    {
      //medium
      if (level == 2)
        screen.getPlayer().setIcon (new ImageIcon ("../Images/SockBunny.png"));
      else
      {
        //difficult
        if (level == 3)
        {
          screen = new DifficultGame (getScreenFactory());
          screen.getPlayer().setIcon (new ImageIcon ("../Images/HappyLlama.png"));
        }
      }
      //set new screen
      getScreenFactory().setCurrentScreen (screen);
      //add points to screen
      screen.getPlayer().addPoints (points);
      //launcher can be used
      gameWindow.requestFocusInWindow();
    }
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