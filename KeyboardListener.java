import java.awt.event.*;

/**
 * @author baseball435
 */
public class KeyboardListener implements KeyListener
{
  private boolean[] keys = new boolean[256];
  public void keyPressed (KeyEvent event)
  {
    keys[event.getKeyCode()] = true;
  }
  
  public void keyReleased (KeyEvent event)
  {
    keys[event.getKeyCode()] = false;
  }
  
  public void keyTyped (KeyEvent event)
  {
  }
  
  public boolean isKeyPressed (int key)
  {
    return keys[key];
  }
}