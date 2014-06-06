import java.awt.*;
import javax.swing.*;
/**
 * The "SettingsPanel" class which extends JPanel. It allows the user to navigate and 'play with' specific game settings.
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 25 2014. (Created class to instantiate and access Sound class.)
 * @version 1.1, May 26 2014. (Split toggle method into two to fix glitches - song would not start/stop properly and overlapped.)
 * @version 1.2, May 30 2014. (Created a method to initialize clip in a separate method, added musicInitialized and getPanel method.)
 * @version 1.3, June 5 2014. (Created 
 */
public class SettingsPanel extends JPanel
{
  /**
   * music - reference - Reference variable to the object created by the Sound class.
   */
  private Sound music;
  /**
   * isOn - boolean - Describes whether the AudioClip from the Sound class is playing or not. 
   */
  public static boolean isOn = false;
  /**
   * musicOn - reference - Reference variable to the specified JButton. 
   */
  protected JButton musicOn = new JButton (new ImageIcon ("../Images/volume_2.png"));
  /**
   * musicOff - reference - Reference variable to the specified JButton. 
   */
  protected JButton musicOff = new JButton (new ImageIcon ("../Images/volume_off.png"));
  /**
   * musicInitialized - static boolean - Represents whether or not the AudioClip object from the Sound class has been instantiated yet. 
   */
  protected static boolean musicInitialized = false;  
  /**
   * constraints - reference - Reference variable for the corresponding GridBagConstraints object.
   */
  private GridBagConstraints constraints = new GridBagConstraints();
  /**
   * resetButton - reference - Reference variable for the corresponding JButton object. 
   */
  protected JButton resetButton = new JButton ("Reset Scores");
  
  /**
   * The constructors. It sets the properties of this JPanel and adds components to it. 
   */
  public SettingsPanel()
  {
    setLayout (new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    setInitialStates();
  }
  
  /**
   * The "setInitialStates" method. It adds the buttons to the panel and sets their initial clickable states. 
   */
  private void setInitialStates()
  {
    JLabel titleImage = new JLabel (new ImageIcon ("../Images/SettingsTitle.png"));
    JLabel soundLabel = new JLabel ("Sound On/Off:");
    musicOn.setContentAreaFilled(false);
    musicOn.setBorder (null);    
    musicOn.setRolloverIcon(new ImageIcon ("../Images/volume_2Roll.png"));
    musicOff.setContentAreaFilled(false);
    musicOff.setBorder (null);
    musicOff.setRolloverIcon(new ImageIcon ("../Images/volume_OffRoll.png"));
    
    constraints.gridwidth = 6;
    constraints.gridy = 0;
    constraints.gridx = 0;
    add (titleImage, constraints);    
    constraints.insets = new Insets (5, 10, 5, 10);
    constraints.gridy = 1;
    constraints.gridx = 0;
    constraints.gridwidth = 2;
    add (soundLabel, constraints);
    
    constraints.gridwidth = 1;
    constraints.gridx = 2;
    add(musicOn, constraints);
    constraints.gridx = 3;
    add(musicOff, constraints);
    musicOff.setEnabled(false);
    
    constraints.gridy = 3;
    constraints.gridx = 4;
    constraints.gridwidth = 2;
    add (resetButton, constraints);
  }
  
  /**
   * The "initializeClip" method. It creates a new instance of the Sound class and sets musicInitialized to true. 
   */
  public void initializeClip()
  {
    music = new Sound();
    musicInitialized = true;
  }
  
  /**
   * The "toggleOn" method. It changes the state of the buttons to their opposite and plays the sound clip in a loop. 
   */
  public void toggleOn()
  {
    music.playSound();
    musicOn.setEnabled(false);
    musicOff.setEnabled(true);
    isOn = !isOn;
  }
  
  /**
   * The "toggleOff" method. It reverses the state of the buttons and stops the sound clip from playing. 
   */
  public void toggleOff()
  {
    music.stopSound();
    musicOn.setEnabled(true);
    musicOff.setEnabled(false);
    isOn = !isOn;
  }
  
  /**
   * The "getPanel" method. It returns a SettingsPanel object. 
   * 
   * @return Returns this instance of SettingsPanel.
   */
  public SettingsPanel getPanel()
  {
    return this;
  }
}