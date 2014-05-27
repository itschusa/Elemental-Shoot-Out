import java.awt.event.*;

/**
 * @author baseball435 from Youtube
 */
public class KeyboardListener implements KeyListener
{
  protected boolean[] keys = new boolean[256];
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
    return;
  }
  
  public boolean isKeyPressed (int key)
  {
    return keys[key];
  }
  
  public boolean isKeyReleased(int key)
  {
    return !keys[key];
  }
}