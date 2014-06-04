//JAVADOC
import java.awt.event.*;

/**
 * @author baseball435 from Youtube
 * @author Anqi Wu (modifications)
 * @version 2.0, June 3, 2014. (Added another array to store only released keys, and a refresh method)
 */
public class KeyboardListener implements KeyListener
{
  protected boolean[] pressedKeys = new boolean[256]; 
  protected boolean[] releasedKeys = new boolean[256];
  
  public void keyPressed (KeyEvent event)
  {
     pressedKeys[event.getKeyCode()] = true;
  }
  
  public void keyReleased (KeyEvent event)
  {
    pressedKeys[event.getKeyCode()] = false;
    releasedKeys[event.getKeyCode()] = true;
  }
  
  public void keyTyped (KeyEvent event)
  {
    return;
  }
  
  public boolean isKeyPressed (int key)
  {
    return pressedKeys[key];
  }
  
  public boolean isKeyReleased(int key)
  {
    return releasedKeys[key];
  }
  
  public void refreshReleased ()
  {
    releasedKeys = new boolean[256];
  }
}