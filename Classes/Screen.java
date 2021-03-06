import java.awt.*;

/**
 * Represents a Screen, which can be created, updated and drawn.
 * 
 * @author baseball435 of Youtube
 * @author Anqi Wu (JavaDoc)
 * @version 1.0, May 22 2014.
 * @version 1.1, June 8 2014. (JavaDoc)
 */
public abstract class Screen
{
  /**
   * myScreenFactory - ScreenFactory - The ScreenFactory of the screen.
   */
  private final ScreenFactory myScreenFactory;
  
  /**
   * Constructs a new Screen with the given ScreenFactory.
   * 
   * @param screenFactory - ScreenFactory - The ScreenFactory that displays this screen.
   */
  public Screen (ScreenFactory screenFactory)
  {
    myScreenFactory = screenFactory;
  }
  
  /**
   * Does nothing on creation.
   * Override to do something on creation.
   */
  public abstract void onCreate ();
  
  /**
   * Does nothing when updating.
   * Override to do something when updating.
   */
  public abstract void onUpdate ();
  
  /**
   * Draws nothing.
   * Override to draw something.
   * 
   * @param twoDimensional - Graphics2D - The Graphics2D object.
   */
  public abstract void onDraw (Graphics2D twoDimensional);
  
  /**
   * Returns the ScreenFactory of this screen.
   */
  public ScreenFactory getScreenFactory()
  {
    return myScreenFactory;
  }
}