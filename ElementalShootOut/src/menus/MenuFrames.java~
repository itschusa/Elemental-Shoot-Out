package menus;

import game.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * The "MenuFrames" class, which creates a JFrame window to which JPanel objects and other components (i.e. JButton, 
 * JMenuBar, JMenu, JMenuItem...) are added. The window acts as the program's main menu, and based on the user's 
 * interactions with it, the appropriate action will occur (opening a dialog box, changing the current panel, opening
 * the game screen, etc.). 
 * 
 * @author Chusa Nguyen
 * @author Anqi Wu
 * @version 1.0, May 12 2014. (No functionality. Buttons do not appear.)
 * @version 1.1, May 13 2014. (Completed framework. ActionListener only implemented to close, main menu, and play.)
 * @version 1.2, May 14 2014. (Added background image. Resized frame and specs.)
 * @version 1.3, May 15 2014. (Changed some variable and method names, fixed layout, added padding between buttons and initial JavaDoc notations.)
 * @version 1.4, May 19 2014. (Added background music.)
 * @version 1.5, May 20 2014. (Changed background and button graphics.)
 * @version 1.6, May 21 2014. (Added window listener, key bindings and dialog boxes.)
 * @version 2.0, May 22 2014. (Reorganized code: split panels into separate classes, listeners given separate methods.)
 * @version 2.1, May 24 2014. (Added instructions panel, replaced boolean secondPanel with int panelNum, made corrections to some comments.)
 * @version 2.2, May 25 2014. (Added settings panel and option to start/stop music.)
 * @version 2.3, May 26 2014. (Fixed glitches with music.)
 * @version 2.4, May 27 2014. (Difficulty buttons now go to separate windows.)
 * @version 2.5, May 29 2014. (Replaced features menu item with instructions menu item.)
 * @version 2.6, May 30 2014. (Fixed: settings --> main --> play --> main --> settings --> null pointer exception. Rearranged some code and added 4 new methods.)
 * @version 2.7, June 4 2014. (Removed setSettings method because of redundency, changed access level of settings, added code for highscores [should be functional].)
 * @version 2.8, June 4 2014. (All components of this class are complete. Need to do final testing.)
 * @version 2.9, June 8 2014. (Added actionlisteners to all menu buttons of each panel.)
 */
public class MenuFrames extends JFrame
{
  /**
   * menuPanel - reference - Reference variable to the object created by the MenusPanel class which extends JPanel.
   */
  private MenusPanel menuPanel;
  /**
   * difficultiesPanel - reference - Reference variable to the object created by the LevelsPanel class which extends JPanel.
   */
  private LevelsPanel difficultiesPanel;
  /**
   * instructionsPanel - reference - Reference variable to the object created by the InstructionsPanel class which extends JPanel. 
   */
  private InstructionsPanel instructionsPanel;
  /**
   * settingsPanel - reference - Reference variable to the object created by the SettingsPanel class which extends JPanel. 
   */
  private SettingsPanel settingsPanel;
  /**
   * scoresPanel - reference - Reference variable to the object created by the HighscorePanel which extends JPanel. 
   */
  private HighscorePanel scoresPanel = new HighscorePanel();
  /**
   * panelNum - int - Represents the current panel (1 = main, 2 = level selection, 3 = instructions, 4 = settings, 5 = highscores).
   */
  private int panelNum;
  /**
   * menus - reference - Reference variable to the correspoding JMenuBar object.
   */
  private JMenuBar menus = new JMenuBar();
  /**
   * fileMenu - reference - Reference variable to the corresponding JMenu object.
   */
  private JMenu fileMenu = new JMenu ("File");
  /**
   * helpMenu - reference - Reference variable to the corresponding JMenu object.
   */
  private JMenu helpMenu = new JMenu ("Help");
  /**
   * mainMenuItem - reference - Reference varaible to the corresponding JMenuItem object.
   */
  protected JMenuItem mainMenuItem = new JMenuItem ("Main Menu   F1");
  /**
   * helpItem - reference - Reference variable to the corresponding JMenuItem object.
   */
  protected JMenuItem helpItem = new JMenuItem("Help                      F2");
  /**
   * aboutItem - reference - Reference variable to the corresponding JMenuItem object.
   */
  protected JMenuItem aboutItem = new JMenuItem("About                   F3");
  /**  
   * instructionsItem - reference - Reference variable to the corresponding JMenuItem object. 
   */
  protected JMenuItem instructionsItem = new JMenuItem ("Instructions       F4");
  /**
   * closeItem - reference - Reference variable to the corresponding JMenuItem object.
   */
  protected JMenuItem closeItem = new JMenuItem("Close            F5");
  /**
   * musicPanelUncreated - static boolean - Represents whether or not the Sound class has been called to create an instance of AudioClip.
   */
  protected static boolean musicPanelUncreated = true;
  
  /**
   * The constructor of the "MenuFrames" class. It creates the JFrame by calling the constructor of this class's super
   * class. It sets an image as the background, sets the menu bar along with its options, as well as sets the main
   * menu's panel by calling the appropriate methods.
   */
  public MenuFrames()
  {
    super ("Elemental Shoot-Out: A Chemistry Game");
    addBackground("Images/Wallpaper.png");
    menuBars();
    mainMenuPanel();
    frameSpecifications();
  }
  
  /**
   * The "addBackground" method. It sets the window's background by retrieving an image from the specified path.
   * 
   * @param imageName - String - The name of the image to be retrieved.
   */
  private void addBackground (String imageName)
  {
    setContentPane(new JLabel(new ImageIcon(imageName)));
    setLayout(new GridBagLayout());
  }
  
  /**
   * The "frameSpecifications" method. It sets the specifications of the JFrame window by defining its size,
   * visibility, resizability, and what should be done by default should the exit button be clicked.
   * An anonymous class that extends WindowAdapter listens for when the user wants to close the window and then it does
   * the same thing as if the menu item was clicked.
   */
  private void frameSpecifications ()
  {
    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    
    addWindowListener(new java.awt.event.WindowAdapter() {
      public void windowClosing(java.awt.event.WindowEvent windowEvent) 
      {
        closeItem.doClick();
      }
    });
    
    setVisible (true);
    requestFocus();
    toFront();
    setSize (900,600);
    setResizable (false);
    setLocationRelativeTo(null);
  }
  
  /**
   * The "menuBars" method. It creates and sets the window's menu bar and its options by creating and including
   * the appropriate components (JMenuBar, JMenu, JMenuItem). This method also calls others in order to add an
   * ActionListener and handle keyboard input for each menu item. 
   */
  private void menuBars()
  {
    menus.setFocusable(true);
    menus.add(fileMenu);
    menus.add(helpMenu); 
    fileMenu.add(mainMenuItem);
    fileMenu.add(closeItem);
    helpMenu.add(helpItem);
    helpMenu.add(aboutItem);
    helpMenu.add(instructionsItem);
    setJMenuBar(menus); 
    menuBarActionListeners();
    keyBindings();
  }
  
  /**
   * The "verifyClose" method. It shows the user a JOptionPane dialog box to verify whether or not the user would like to 
   * exit the program before closing all windows. 
   * 
   * @param choice - int - Integer representation of the user's choice. 
   */
  private void verifyClose()
  {
    int choice = JOptionPane.CANCEL_OPTION;
    choice = JOptionPane.showConfirmDialog (this, "Would you like to exit this game?", "Confirm Quit",
                                            JOptionPane.YES_NO_OPTION);
    if (choice == JOptionPane.YES_OPTION)
      System.exit(0);
    else
      validate();
  }
  
  /**
   * The "menuBarActionListeners" method. It attaches an ActionListener object to each item contained in
   * the menu bar. 
   * 
   * @param e reference - Reference variable for the specified ActionEvent object. 
   */
  private void menuBarActionListeners()
  {
    mainMenuItem.addActionListener (new ActionListener(){
      public void actionPerformed (ActionEvent e)      {
        if (panelNum != 1)
        {
          mainMenuPanel();
        }
      }}); 
    
    closeItem.addActionListener (new ActionListener(){
      public void actionPerformed (ActionEvent e)      {
        verifyClose();
      }}); 
    
    aboutItem.addActionListener(new ActionListener(){
      public void actionPerformed (ActionEvent e)      {
        new SplashScreen ("Images/SplashSMALLER.png", 5000, false);
      }});
    
    helpItem.addActionListener(new ActionListener(){
      public void actionPerformed (ActionEvent e)      {
        try
        {
          Runtime.getRuntime().exec("hh.exe HelpFilesProject.chm");
        }
        catch (IOException ex)
        {
        }
      }});  
    
    instructionsItem.addActionListener(new ActionListener(){
      public void actionPerformed (ActionEvent e) {
        instructions();
      }});
  }
  
  /**
   * The "keyBindings" method. It uses input and action maps to attach actions to specified keys.
   * 
   * @param e reference - Reference variable for the specified ActionEvent object. 
   */
  private void keyBindings()
  {
    menus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0, false), "main menu");
    menus.getActionMap().put("main menu", new AbstractAction(){
      public void actionPerformed (ActionEvent e){
        mainMenuItem.doClick();}});
    menus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0, false), "help");
    menus.getActionMap().put("help", new AbstractAction(){
      public void actionPerformed (ActionEvent e){
        helpItem.doClick();}});
    menus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0, false), "about");
    menus.getActionMap().put("about", new AbstractAction(){
      public void actionPerformed (ActionEvent e) {
        aboutItem.doClick();}});
    menus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0, false), "instructions");
    menus.getActionMap().put("instructions", new AbstractAction(){
      public void actionPerformed (ActionEvent e) {
        instructionsItem.doClick();}});
        menus.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0, false), "close");
    menus.getActionMap().put("close", new AbstractAction(){
      public void actionPerformed (ActionEvent e) {
        closeItem.doClick();}});
  }
  
  /**
   * The "mainMenuPanel" method. It creates an instance of the JPanel "MenusPanel" and adds it to the frame, as well
   * as adding ActionListener objects to the components on the panel.
   */
  private void mainMenuPanel()
  {
    if (panelNum == 2)
      remove(difficultiesPanel);
    else if (panelNum == 3)
      remove (instructionsPanel);
    else if (panelNum == 4)
      removeSettings();
    else 
      if (scoresPanel != null)
      remove (scoresPanel);
    panelNum = 1;  
    menuPanel = new MenusPanel();
    add(menuPanel);
    menuPanelActionListeners();
    validate();
    repaint();    
  }
  
  /**
   * The "menuPanelActionListeners" method. It attaches an ActionListener object to each component (JButton)
   * on the panel.
   * 
   * @param e reference - Reference variable for the specified ActionEvent object. 
   */
  private void menuPanelActionListeners()
  {
    menuPanel.playButton.addActionListener (new ActionListener ()
                                              {
      public void actionPerformed (ActionEvent e)      { 
        remove(menuPanel);
        repaint();
        levelsPanel();
      }});
    menuPanel.instructionsButton.addActionListener (new ActionListener ()
                                                      {
      public void actionPerformed (ActionEvent e)      { 
        remove (menuPanel);
        instructions();
      }
    });
    menuPanel.settingsButton.addActionListener (new ActionListener ()
                                                  {
      public void actionPerformed (ActionEvent e)      { 
        remove (menuPanel);
        settings();
        repaint();
      }
    });   
    menuPanel.scoresButton.addActionListener (new ActionListener ()
                                                {
      public void actionPerformed (ActionEvent e)      { 
        remove (menuPanel);
        highscorePanel();
      }
    });   
  }
  
  /**
   * The "levelsPanel" method. Sets the current panel to the level selection's panel and adds the appropriate JButton
   * objects. It also adds ActionListener objects to each component (JButton).
   */
  private void levelsPanel()
  {
    panelNum = 2;
    difficultiesPanel = new LevelsPanel();
    add(difficultiesPanel);   
    levelsPanelActionListeners();
    validate();
  }
  
  /**
   * The "levelsPanelActionListeners" method, which adds an ActionListener object to each component on the panel.
   * 
   * @param e reference - Reference variable for the specified ActionEvent object. 
   */
  private void levelsPanelActionListeners()
  {
    difficultiesPanel.easyButton.addActionListener (new ActionListener ()
                                                      {
      public void actionPerformed (ActionEvent e)
      { 
        setVisible(false);
        mainMenuPanel();
        new GameWindow("Easy Level", 1, getMenuFrame());
      }
    }); 
    difficultiesPanel.mediumButton.addActionListener (new ActionListener ()
                                                        {
      public void actionPerformed (ActionEvent e)
      { 
        setVisible(false);
        mainMenuPanel();
        new GameWindow("Medium Level", 2,getMenuFrame());
      }
    }); 
    difficultiesPanel.difficultButton.addActionListener (new ActionListener ()
                                                           {
      public void actionPerformed (ActionEvent e)
      { 
        setVisible(false);
        mainMenuPanel();
        new GameWindow("Hard Level", 3, getMenuFrame());
      }
    }); 
    difficultiesPanel.menuButton.addActionListener (new ActionListener ()
                                                      {
      public void actionPerformed (ActionEvent e)
      {
        mainMenuPanel();
      }});
  }
  
  /**
   * The "instructions" method. It removes the current panel and replaces it with the instructions panel.
   */
  private void instructions()
  {
    if (panelNum != 3)
    {
      if (panelNum == 1)
        remove (menuPanel);
      else if (panelNum == 2)
        remove(difficultiesPanel);
      else if (panelNum == 4)
        remove (settingsPanel);
      else 
        remove (scoresPanel);
      panelNum = 3;
      instructionsPanel = new InstructionsPanel();
      add (instructionsPanel);
      validate();
    }
    repaint();
    instructionsPanel.menuButton.addActionListener (new ActionListener ()
                                                      {
      public void actionPerformed (ActionEvent e)
      {
        mainMenuPanel();
      }});
  }
  
  /**
   * The "settings" method. It adds an instance of SettingsPanel to the frame, and creates a new instance if one has yet to have been created. 
   * It also initializes the AudioClip object from the SettingsPanel class upon first instantiation. 
   */
  public void settings()
  {
    panelNum = 4;
    if (musicPanelUncreated)
    {
      musicPanelUncreated = false;
      settingsPanel = new SettingsPanel();
      if (!SettingsPanel.getMusicInitialized())
        settingsPanel.initializeClip();
      settingsActionListeners();
    }
    add (settingsPanel);
    validate();
  } 
  
  /**
   * The "settingsActionListeners" method. It implements an ActionListener object to each component of the settings panel.
   * 
   * @param choice int - Represents the user's choice on the JOptionPane dialog box. 
   * @param e reference - Reference variable for the specified ActionEvent object. 
   */
  private void settingsActionListeners()
  {
    settingsPanel.musicOn.addActionListener (new ActionListener ()
                                               {
      public void actionPerformed (ActionEvent e) {
        settingsPanel.toggleOn();
        repaint();
      }});
    settingsPanel.musicOff.addActionListener (new ActionListener ()
                                                {
      public void actionPerformed (ActionEvent e) {
        settingsPanel.toggleOff();
        repaint();
        validate();
      }});
    settingsPanel.resetButton.addActionListener(new ActionListener()
                                                  {
      public void actionPerformed (ActionEvent e) {
        int choice = JOptionPane.CANCEL_OPTION;
        choice = JOptionPane.showConfirmDialog (new JOptionPane(), "Would you like to erase all current scores?", 
                                                "Confirm Reset", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION)
        {
          scoresPanel.resetScores();
        }
        repaint();
        revalidate();
      }});
      settingsPanel.menuButton.addActionListener (new ActionListener ()
                                                      {
      public void actionPerformed (ActionEvent e)
      {
        mainMenuPanel();
      }});
  }
  
  /**
   * The "highscorePanel" method. It adds an instance of HighscorePanel to the current JFrame. 
   */
  private void highscorePanel()
  {
    panelNum = 5;
    scoresPanel = new HighscorePanel();
    add(scoresPanel);
    validate();
    repaint();
    scoresActionListener();
  }
  
  /**
   * The "scoresActionListener" method. It adds an ActionListener object to the panel's reset button. 
   * 
   * @param e reference - Reference variable to the specified ActionEvent object. 
   */
  private void scoresActionListener()
  {
    scoresPanel.printButton.addActionListener (new ActionListener()
                                                 {
      public void actionPerformed (ActionEvent e) {
        scoresPanel.printScores();
      }});
    scoresPanel.menuButton.addActionListener (new ActionListener ()
                                                      {
      public void actionPerformed (ActionEvent e)
      {
        mainMenuPanel();
      }});
  }
  
  /**
   * The "getMenuFrame" method, which returns a MenuFrames object. 
   * 
   * @return Returns this instance of MenuFrames. 
   */
  public MenuFrames getMenuFrame()
  {
    return this;
  }
    
  /**
   * The "removeSettings" method, which allows other classes to remove the settings panel. 
   */
  public void removeSettings()
  {
    remove(settingsPanel);
  }
  
  /**
   * The "getSettings" method, which returns an instance of SettingsPanel.
   * 
   * @return Returns the current instance of SettingsPanel by calling its accessor method. 
   */
  public SettingsPanel getSettings()
  {
    return settingsPanel.getPanel();
  }
}