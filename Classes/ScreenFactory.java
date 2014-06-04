//JAVADOC
/**
 * @author baseball435
 * @version 1.0, May 22, 2014.
 * @version 1.1, May 28, 2014. (Panda Company - changed Screen to LevelScreen)
 * @version 1.2, June 4, 2014. (Added win and loseFocus methods)
 */
public class ScreenFactory
{
  private final GameWindow game;
  private LevelScreen screen;
  
  public ScreenFactory (GameWindow game)
  {
    this.game = game;
  }
  
  public void showScreen (LevelScreen screen)
  {
    this.screen = screen;
    this.screen.onCreate();
  }
  
  public LevelScreen getCurrentScreen ()
  {
    return screen;
  }
  
  //The Panda Company Code
  public void setCurrentScreen (LevelScreen screen)
  {
    this.screen = screen;
  }
  
  public void win (int level)
  {
    game.win(level);
  }
  
  public void loseFocus ()
  {
    game.loseFocus();
  }
  
//  public KeyboardListener getListener()
//  {
//    return game.getKeyboardListener();
//  }
}