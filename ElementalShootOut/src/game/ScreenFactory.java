package game;

/**
 * The ScreenFactory is a class that can create, and set a Screen to show.
 * 
 * @author baseball435
 * @author Anqi Wu (setCurrentScreen, win, loseFocus, JavaDoc)
 * @version 1.0, May 22 2014.
 * @version 1.1, May 28 2014. (Panda Company - changed Screen to LevelScreen)
 * @version 1.2, June 4 2014. (Added win and loseFocus methods)
 * @version 1.3, June 8 2014. (JavaDoc, removed setCurrentScreen)
 * @version 1.4, June 8 2014. (Added getGame.)
 */
public class ScreenFactory
{
  /**
   * game - GameWindow - The GameWindow of the ScreenFactory.
   */
  private final GameWindow game;
  /**
   * screen - LevelScreen - The current LevelScreen of the ScreenFactory.
   */
  private LevelScreen screen;
  
  /**
   * Constructs a new ScreenFactory with the given GameWindow.
   * 
   * @param game - GameWindow - The GameWindow of the Screen Factory.
   */
  public ScreenFactory (GameWindow game)
  {
    this.game = game;
  }
  
  /**
   * Sets the currentScreen to the one in the parameter and creates it.
   * 
   * @param screen - LevelScreen - The LevelScreen to store and create.
   */
  public void showScreen (LevelScreen screen)
  {
    this.screen = screen;
    this.screen.onCreate();
  }
  
  /**
   * Returns the current LevelScreen.
   * 
   * @return Returns the current LevelScreen instance.
   */
  public LevelScreen getCurrentScreen ()
  {
    return screen;
  }
  
  /**
   * Calls win in the GameWindow.
   * 
   * @param level int - Integer representation of the level difficulty which was won. 
   */
  public void win (int level)
  {
    game.win(level);
  }
  
  /**
   * Calls loseFocus in the GameWindow.
   */
  public void loseFocus ()
  {
    game.loseFocus();
  }
  
  /**
   * Returns the current gameWindow.
   * 
   * @param Returns the current GameWindow instance.
   */
  public GameWindow getGame()
  {
    return game;
  }
}