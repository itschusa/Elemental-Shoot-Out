import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author Chusa Nguyen
 * @version 1.0, May 12 2014. (No functionality. Buttons do not appear.)
 */

public class MenuFrames extends JPanel //implements ActionListener
{
  JFrame frame = new JFrame ("Elemental Shoot-Out: A Chemistry Game");
  
  public MenuFrames()
  {
    menuBars();
    //initialPanel();
    frameSpecifications();
  }
  
  public void frameSpecifications ()
  {    
    frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    frame.setVisible (true);
    frame.setSize (400,300);
    frame.setResizable (false);
  }
  
  public void menuBars()
  {
    JMenuBar menus = new JMenuBar();
    JMenu fileMenu = new JMenu ("File");
    JMenu helpMenu = new JMenu ("Help");
    JMenuItem closeItem = new JMenuItem("Close");
    JMenuItem aboutItem = new JMenuItem("About");
    JMenuItem featuresItem = new JMenuItem("Features");
    
    menus.add(fileMenu);
    menus.add(helpMenu); 
    fileMenu.add(closeItem);
    helpMenu.add(aboutItem);
    helpMenu.add(featuresItem);
    frame.setJMenuBar(menus);
  }
  
  public void initialPanel()
  {
    JPanel panel = new JPanel();
    panel.setLayout (new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    JButton playButton = new JButton ("Play");
    JButton instructionsButton = new JButton ("Instructions");
    JButton scoresButton = new JButton ("High Scores");
    
    constraints.gridx = 1;
    constraints.gridy = 1;
    panel.add(playButton, constraints);
  }
  
  public void levelsPanel()
  {
  }
  
  public static void main(String [] args)
  {
    MenuFrames showFrames = new MenuFrames();
  }
}