import javax.swing.*;

/**
 * The GameWindow creates the window, and creates the level panel.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014.
 */
public class GameWindow extends JFrame
{
  //the panel
  private LevelMap game;
  
  //constructor, sets title, panel
  public GameWindow(String description, int level)
  {
    super ("Elemental Shoot-Out: "+description);
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent windowEvent) 
      {
        System.exit(0);
      }
    });
    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible (true);
    setSize (900,600);
    setResizable (false);
    
    if (level == 1)
      game = new EasyGame ();
    else
      System.out.println ("Not Available Yet");
    
    game.setBackground (new java.awt.Color (255,255,255));
    setContentPane (game);
  }
}