import java.util.ArrayList;
import java.awt.*;
import javax.swing.*;

/**
 * The EasyGame class is a screen that draws the easy level.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014. (extends panel, uses paintComponent)
 * @version 1.1, May 22, 2014. (extends levelscreen, updates!)
 */
public class EasyGame extends LevelScreen
{      
  KeyboardListener listener;
  
  public EasyGame (ScreenFactory screenFactory)
  {
    super(screenFactory);
    ArrayList<Element> newTargets = new ArrayList<Element>();
    String name = "Stable";
    
    for (int row = 1; row<4;row++)
    {
      for (int col=1;col<13;col++)
      {
        if (Math.random()<0.5)
          name = "Unstable";
        newTargets.add (new EasyParticle(name, new Location(col, row),super.getGameGrid()));
        name = "Stable";
      }
    }
    
    ArrayList<Element> newInventory = new ArrayList <Element>();
    for (int col = 1; col<13;col++)
    {
      name = "Stable";
      if (Math.random()<0.5)
        name = "Neutron";
      newInventory.add (new EasyParticle(name, new Location (col, 10), super.getGameGrid()));      
    }
    super.setAllTargets (newTargets);
    super.setAllInventory (newInventory);
    
    //listener = 
  }
  
  public void onCreate ()
  {
  }
  
  public void onUpdate ()
  {
    for (int x=0;x<getAllTargets().size();x++)
    {
      if (getAllTargets().get(x).getName().equals("Unstable")&& !getAllTargets().get(x).canMove())
        getAllTargets().get(x).reverseMove();
      getAllTargets().get(x).update();
    }
    
    for (int x = 0; x<getAllInventory().size();x++)
    {
      getAllInventory().get(x).update();
    }
  }
  
  public void onDraw (Graphics2D twoDimensional)
  {
    twoDimensional.drawImage (getWallpaper().getImage(), 0, 0, getWallpaper().getImageObserver()); 
    
    for (int x=0;x<getAllTargets().size();x++)
    {
      getAllTargets().get(x).draw(twoDimensional);
    }
    
    for (int x = 0; x<getAllInventory().size();x++)
    {
      getAllInventory().get(x).draw(twoDimensional);
    }
  }
}