//JAVADOC
import java.awt.*;

/**
 * @author baseball435 of Youtube
 * @version 1.0, May 22, 2014.
 */
public abstract class Screen
{
  private final ScreenFactory myScreenFactory;
  
  public Screen (ScreenFactory screenFactory)
  {
    myScreenFactory = screenFactory;
  }
  
  public abstract void onCreate ();
  
  public abstract void onUpdate ();
  
  public abstract void onDraw (Graphics2D twoDimensional);
  
  public ScreenFactory getScreenFactory()
  {
    return myScreenFactory;
  }
}