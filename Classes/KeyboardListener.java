import java.awt.event.*;

/**
 * @author baseball435 from Youtube
 * @author Anqi Wu (modifications)
 * @version 2.0, June 3 2014. (Added another array to store only released keys, and a refresh method)
 * @version 2.1, June 5 2014. (JavaDoc)
 */
public class KeyboardListener implements KeyListener
{
  /**
   * pressedKeys - boolean[] - Stores the keys that are being pressed.
   */
  protected boolean[] pressedKeys = new boolean[256]; 
  /**
   * releasedKeys - boolean[] - Stores the keys that were released.
   */
  protected boolean[] releasedKeys = new boolean[256];
  
  /**
   * Stores the key that was pressed in the array.
   * 
   * @param event - KeyEvent - The keyboard event.
   */
  public void keyPressed (KeyEvent event)
  {
    pressedKeys[event.getKeyCode()] = true;
  }
  
  /**
   * Stores the key that was released in the array.
   * Sets the key to not pressed in the array.
   * 
   * @param event - KeyEvent - The keyboard event.
   */
  public void keyReleased (KeyEvent event)
  {
    pressedKeys[event.getKeyCode()] = false;
    releasedKeys[event.getKeyCode()] = true;
  }
  
  /**
   * Empty override.
   */
  public void keyTyped (KeyEvent event)
  {
    return;
  }
  
  /**
   * Gets whether the key specified in the parameter is pressed.
   * 
   * @param key - int - The key represented by ASCII.
   */
  public boolean isKeyPressed (int key)
  {
    return pressedKeys[key];
  }
  
  /**
   * Gets whether the key specified in the parameter is released.
   * 
   * @param key - int - The key represented by ASCII.
   */
  public boolean isKeyReleased(int key)
  {
    return releasedKeys[key];
  }
  
  /**
   * Refreshed the releasedKey array (sets keys to false)
   */
  public void refreshReleased ()
  {
    releasedKeys = new boolean[256];
  }
}