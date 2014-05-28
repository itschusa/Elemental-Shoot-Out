/**
 * @author baseball435
 * @version 1.0, May 22, 2014.
 */
public class ScreenFactory
{
  private final GameWindow game;
  private Screen screen;
  
  public ScreenFactory (GameWindow game)
  {
    this.game = game;
  }
  
  public void showScreen (Screen screen)
  {
    this.screen = screen;
    this.screen.onCreate();
  }
  
  public Screen getCurrentScreen ()
  {
    return screen;
  }
  
  //The Panda Company Code
  public void setCurrentScreen (Screen screen)
  {
    this.screen = screen;
  }
  
//  public KeyboardListener getListener()
//  {
//    return game.getKeyboardListener();
//  }
}