import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * @author Chusa Nguyen
 * @version 1.0, May 12 2014. (No functionality. Buttons do not appear.)
 * @version 1.1, May 13 2014. (Completed framework. ActionListener only implemented to close, main menu, and play.)
 */

public class MenuFrames extends JFrame
{
  //JFrame frame = new JFrame ("Elemental Shoot-Out: A Chemistry Game");
  
  JPanel panel1 = new JPanel();
  JPanel panel2 = new JPanel();
  JButton playButton = new JButton ("Play");
  JButton instructionsButton = new JButton ("Instructions");
  JButton scoresButton = new JButton ("High Scores");
  JButton easyButton = new JButton ("Easy");
  JButton mediumButton = new JButton ("Medium");
  JButton difficultButton = new JButton ("Difficult");
  private boolean secondPanel;
  
  public MenuFrames()
  {
    super ("Elemental Shoot-Out: A Chemistry Game");
    menuBars();
    initialPanel();
    frameSpecifications();
  }
  
  public void frameSpecifications ()
  {    
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    setVisible (true);
    setSize (400,300);
    setResizable (false);
  }
  
  public void menuBars()
  {
    JMenuBar menus = new JMenuBar();
    JMenu fileMenu = new JMenu ("File");
    JMenu helpMenu = new JMenu ("Help");
    JMenuItem mainMenuItem = new JMenuItem ("Main Menu");
    JMenuItem closeItem = new JMenuItem("Close");
    JMenuItem aboutItem = new JMenuItem("About");
    JMenuItem featuresItem = new JMenuItem("Features");
    JMenuItem shortcutItem = new JMenuItem ("Shortcuts");
    
    menus.add(fileMenu);
    menus.add(helpMenu); 
    fileMenu.add(mainMenuItem);
    fileMenu.add(closeItem);
    helpMenu.add(aboutItem);
    helpMenu.add(featuresItem);
    helpMenu.add(shortcutItem);
    setJMenuBar(menus);
    
    mainMenuItem.addActionListener (new ActionListener(){
      public void actionPerformed (ActionEvent e)
      {
        if (secondPanel)
        {
          remove(panel2);
          initialPanel();
        }
      }
    }); 
    closeItem.addActionListener (new ActionListener(){
      public void actionPerformed (ActionEvent e)
      {
        System.exit(0);
      }
    }); 
  }
  
  public void initialPanel()
  {
    secondPanel = false;
    panel1.setLayout (new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    
    constraints.fill=constraints.HORIZONTAL;
    constraints.weighty = 1;
    constraints.gridx = 1;
    constraints.gridy = 1;
    panel1.add(playButton, constraints);
    constraints.gridy = 2;
    panel1.add(instructionsButton, constraints);
    constraints.gridy = 3;
    panel1.add(scoresButton, constraints);
    
    add(panel1);
    repaint();
    
    playButton.addActionListener (new ActionListener ()
                                    {
      public void actionPerformed (ActionEvent e)
      { 
        remove(panel1);
        levelsPanel();
        repaint();
      }
    });
  }
  
  public void levelsPanel()
  {
    secondPanel = true;
    panel2.setLayout (new GridBagLayout());
    GridBagConstraints constraints = new GridBagConstraints();
    
    
    constraints.fill=constraints.HORIZONTAL;
    constraints.weighty = 1;
    constraints.gridx = 1;
    constraints.gridy = 1;
    panel2.add(easyButton, constraints);
    constraints.gridy = 2;
    panel2.add(mediumButton, constraints);
    constraints.gridy = 3;
    panel2.add(difficultButton, constraints);
  
    add(panel2);
    revalidate();
  }
  
  public static void main(String [] args)
  {
    MenuFrames showFrames = new MenuFrames();
  }
}