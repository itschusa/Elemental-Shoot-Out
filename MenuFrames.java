import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The "MenuFrames" class, which creates a JFrame window to which JPanel objects and other components (i.e. JButton, 
 * JMenuBar, JMenu, JMenuItem...) are added. The window acts as the program's main menu, and based on the user's 
 * interactions with it, the appropriate action will occur (opening a dialog box, changing the current panel, opening
 * the game screen, etc.). 
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 12 2014. (No functionality. Buttons do not appear.)
 * @version 1.1, May 13 2014. (Completed framework. ActionListener only implemented to close, main menu, and play.)
 * @version 1.2, May 14 2014. (Added background image. Resized frame and specs.)
 * @version 1.3, May 15 2014. (Changed some variable and method names, fixed layout, added padding between buttons and initial JavaDoc notations.)
 */
public class MenuFrames extends JFrame
{
  /**
   * menuPanel - reference - Reference variable to the corresponding JPanel object.
   */
  JPanel menuPanel = new JPanel();
  /**
   * difficultiesPanel - reference - Reference variable to the corresponding JPanel object.
   */
  JPanel difficultiesPanel = new JPanel();
  /**
   * playButton - reference - Reference variable to the corresponding JButton object.
   */
  JButton playButton = new JButton ("Play");
  /**
   * instructionsButton - reference - Reference variable to the corresponding JButton object.
   */
  JButton instructionsButton = new JButton ("Instructions");
  /**
   * scoresButton - reference - Reference variable to the corresponding JButton object.
   */
  JButton scoresButton = new JButton ("High Scores");
  /**
   * easyButton - reference - Reference variable to the corresponding JButton object.
   */
  JButton easyButton = new JButton ("Easy");
  /**
   * mediumButton - reference - Reference variable to the corresponding JButton object.
   */
  JButton mediumButton = new JButton ("Medium");
  /**
   * difficultButton - reference - Reference variable to the corresponding JButton object.
   */
  JButton difficultButton = new JButton ("Difficult");
  /**
   * constraints - reference - Reference variable to the corresponding GridBagConstraints object.
   */
  GridBagConstraints constraints = new GridBagConstraints();
  /**
   * secondPanel - boolean - Represents whether the current panel in use is the menu (first) or difficulties (second) panel.
   */
  private boolean secondPanel;
  
  /**
   * The constructor of the "MenuFrames" class. It creates the JFrame by calling the constructor of this class's super
   * class. It sets an image as the background, sets the menu bar along with its options, as well as sets the main
   * menu's panel by calling the appropriate methods.
   */
  public MenuFrames()
  {
    super ("Elemental Shoot-Out: A Chemistry Game");
    addBackground("Theme 6.png");
    menuBars();
    mainMenuPanel();
    frameSpecifications();
  }
  
  /**
   * The "addBackground" method. It sets the window's background by retrieving an image from the specified path.
   * 
   * @param imageName - String - The name of the image to be retrieved.
   */
  public void addBackground (String imageName)
  {
    setContentPane(new JLabel(new ImageIcon(imageName)));
    setLayout(new GridBagLayout());
  }
  
  /**
   * The "frameSpecifications" method. It sets the specifications of the JFrame window by defining its size,
   * visibility, resizability, and what should be done by default should the exit button be clicked.
   */
  public void frameSpecifications ()
  {    
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    setVisible (true);
    setSize (800,400);
    setResizable (false);
  }
  
  /**
   * The "menuBars" method. It creates and sets the window's menu bar and its options by creating the appropriate
   * components (JMenuBar, JMenu, JMenuItem). It also defines what should be done if each is clicked through
   * implementing ActionListener items to each applicable component individually. 
   * 
   * @param menus - reference - Refers to the corresponding JMenuBar object. 
   * @param fileMenu - reference - Refers to the corresponding JMenu object. 
   * @param helpMenu - reference - Refers to the corresponding JMenu object. 
   * @param mainMenuItem - reference - Refers to the corresponding JMenuItem object. 
   * @param closeItem - reference - Refers to the corresponding JMenuItem object. 
   * @param aboutItem - reference - Refers to the corresponding JMenuItem object. 
   * @param featuresItem - reference - Refers to the corresponding JMenuItem object. 
   * @param shortcutItem - reference - Refers to the corresponding JMenuItem object. 
   */
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
          remove(difficultiesPanel);
          mainMenuPanel();
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
  
  /**
   * The "mainMenuPanel" method. Sets the current panel to the main menu's panel and adds the appropriate JButton
   * objects. This panel and its components use the GridBagLayout layout manager, and are given constraints. 
   * ActionListener objects are implemented to each button. 
   */
  public void mainMenuPanel()
  {
    secondPanel = false;
    menuPanel.setLayout(new GridBagLayout());
    
    constraints.fill=constraints.HORIZONTAL;
    constraints.insets = new Insets (10, 0, 0, 0);
    constraints.gridx = 1;
    constraints.gridy = 1;
    menuPanel.add(playButton, constraints);
    constraints.gridy = 2;
    menuPanel.add(instructionsButton, constraints);
    constraints.gridy = 3;
    menuPanel.add(scoresButton, constraints);
    
    add(menuPanel);
    menuPanel.setBackground(new Color(0,0,0,0));
    repaint();
    
    playButton.addActionListener (new ActionListener ()
                                    {
      public void actionPerformed (ActionEvent e)
      { 
        remove(menuPanel);
        levelsPanel();
        repaint();
      }
    });
  }
  
  /**
   * The "levelsPanel" method. Sets the current panel to the level selection's panel and adds the appropriate JButton
   * objects. This panel and its components use the GridBagLayout layout manager, and are given constraints. 
   */
  public void levelsPanel()
  {
    secondPanel = true;
    difficultiesPanel.setLayout(new GridBagLayout());
    
    constraints.fill=constraints.HORIZONTAL;
    constraints.weighty = 1;
    constraints.gridx = 1;
    constraints.gridy = 1;
    difficultiesPanel.add(easyButton, constraints);
    constraints.gridy = 2;
    difficultiesPanel.add(mediumButton, constraints);
    constraints.gridy = 3;
    difficultiesPanel.add(difficultButton, constraints);
    difficultiesPanel.setBackground(new Color(0,0,0,0));
    add(difficultiesPanel);
    revalidate();
  }
}