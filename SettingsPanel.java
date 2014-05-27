import java.awt.*;
import javax.swing.*;
/**
 * The "SettingsPanel" class which extends JPanel. It allows the user to navigate and 'play with' specific game settings.
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 25 2014. (Created class to instantiate and access Sound class.)
 * @version 1.1, May 26 2014. (Split toggle method into two to fix glitches - song would not start/stop properly and overlapped.)
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
  private boolean isOn = false;
  /**
   * musicOn - reference - Reference variable to the specified JButton. 
   */
  protected JButton musicOn = new JButton ("Turn Music On");
  /**
   * musicOff - JButton - Reference variable to the specified JButton. 
   */
  protected JButton musicOff = new JButton ("Turn Music Off");
  
  /**
   * The constructors. It sets the properties of this JPanel and adds components to it. 
   */
  public SettingsPanel()
  {
    setLayout (new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    music = new Sound();
    setInitialStates();
  }
  
  /**
   * The "setInitialStates" method. It adds the buttons to the panel and sets their initial clickable states. 
   */
  private void setInitialStates()
  {
    add(musicOn);
    add(musicOff);
    musicOff.setEnabled(false);
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
}