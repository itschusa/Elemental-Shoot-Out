import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The EasyGame class is a panel that draw the easy level.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014.
 */
public class EasyGame extends LevelMap
{
  //constructor, sets dimensions to 600 by 400, randomly creates particles
  public EasyGame ()
  {
    setPreferredSize (new Dimension (600,400));
    ArrayList<Particle> newTargets = new ArrayList<Particle>();
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
    super.setAllTargets (newTargets);
  }
  
  //draws the level
  @Override
  public void paintComponent (Graphics g)
  {
    super.paintComponent(g);
    ImageIcon wallpaper = new ImageIcon ("WallpaperGame.png");
    g.drawImage (wallpaper.getImage(), 0, 0, wallpaper.getImageObserver()); 
    
    ArrayList<Particle> toDraw = getAllTargets();
    
    for (int x=0;x<toDraw.size();x++)
    {
      g.drawImage (toDraw.get(x).getIcon().getImage(), toDraw.get(x).getLocation().getXCoord(), toDraw.get(x).getLocation().getYCoord(),toDraw.get(x).getIcon().getImageObserver());
    }
  }
}