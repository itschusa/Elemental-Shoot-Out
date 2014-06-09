import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/**
 * The GameWindow creates the window, and creates the level panel.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @author baseball435 of Youtube(ScreenFactory class)
 * @version 1.0, May 21 2014. (creates game window)
 * @version 1.1, May 22 2014. (added keyboard listener, instantiates vs extends JFrame)
 * @version 1.2, May 26 2014. (Coded keyboard listener, modified window listener.)
 * @version 1.3, May 27 2014. (bug fix with up key, added closeWindow method)
 * @version 1.4, May 28 2014. (Added side and pause panels (for every level). Medium game also shows up!)
 * @version 1.5, May 30 2014. (Changed access level of paused to public static, added frame and its accessor method. Added argument to constructor param list.)
 * @version 1.6, May 31 2014. (Added difficult level)
 * @version 1.7, June 4 2014. (Added keyReleased = for shooting, to eliminate player shooting randomly, win, loseFocus and changeScreen methods)
 * @version 1.8, June 5 2014. (JavaDoc)
 * @version 1.9, June 6 2014. (Added username prompt to create score record.)
 * @version 2.0, June 8 2014. (Added getLevel method, modified key listener implementation to prevent program crashes. Added getMap and option to not save scores.)
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
   * gameLevel - int - Represents the current game difficulty, which is set to easy by default. 
   */
  private int gameLevel = 1;
  
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
    gameLevel = level; //sets game level as user's choice from level selection
    
    //add keyboard listener
    keyboardListener = new KeyboardListener(){
      public void keyReleased (KeyEvent event)
      {
        if (event.getKeyCode() < 256)
        {
          pressedKeys[event.getKeyCode()] = false;
          releasedKeys[event.getKeyCode()] = true;
          act();
        }
      }
      
      public void keyPressed (KeyEvent event)
      {
        if (event.getKeyCode() < 256)
        {
          pressedKeys[event.getKeyCode()] = true;
          act();
        }
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
    gameLevel = level;//if they win, game level is now next level
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
      getScreenFactory().showScreen (screen);
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
    choice = JOptionPane.showConfirmDialog (new JOptionPane(), "Would you like to exit this game?\nNote: Current score will not be saved.", "Confirm Quit",
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
  
  /**
   * The "askName" method, which prompts the user to input their name before returning to the main menu. The while loop
   * is used to repeatedly prompt for input until an acceptable name has been given.
   * 
   * @param username String - Stores the user's name.
   * @param level String - String representation of the current game level.
   * @param choice int - Represents whether or not the user would like to save their score.
   * @return Returns false if the user chooses to cancel (not return to main menu), true if an acceptable name is entered.
   */
  protected boolean askName()
  {
    String username = "";
    String level;
    int choice = JOptionPane.CANCEL_OPTION;
    if (gameLevel == 1)
      level = "Easy";
    else if (gameLevel == 2)
      level = "Medium";
    else
      level = "Difficult";

    choice = JOptionPane.showConfirmDialog (new JOptionPane(), "Would you like to save your current score?", "Confirm Save",
                                            JOptionPane.YES_NO_CANCEL_OPTION);
    if (choice == JOptionPane.CANCEL_OPTION)
      return false;
    if (choice == JOptionPane.YES_OPTION)
    {
      while (true)
      {
        username = (String)JOptionPane.showInputDialog("Enter your name. \n(Note: Maximum 10 characters, no spaces.)");
        if (username == null)
          return false;
        if (!username.equals("") && username.length() <= 10 && !username.contains(" "))
          break;
      }
      new HighscorePanel (username, getScreenFactory().getCurrentScreen().getPlayer().getCurrentPoints(), level);
    }
    return true;
  }
  
  /**
   * The "getLevel" method, which returns the current level of the game being played.
   * 
   * @return Returns the numeric representation of the game's level. 
   */
  protected int getLevel()
  {
    return gameLevel;
  }
  
  /**
   * The "getMap" method, which returns the current instance of the LevelMap which was created.
   * 
   * @return Returns the current LevelMap instance.
   */
  protected LevelMap getMap()
  {
    return game;
  }
}