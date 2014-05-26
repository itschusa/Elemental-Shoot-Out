import java.awt.*;
import javax.swing.*;

public class SettingsPanel extends JPanel
{
  private Sound music;
  private boolean isOn = false;
  protected JButton musicToggle = new JButton ("Turn Music On");
  
  public SettingsPanel()
  {
    setLayout (new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    music = new Sound();
    add(musicToggle);
  }
  
  /**
   * The "toggle" method. It changes the state that music is in. 
   */
  public void toggle()
  {
    isOn = !isOn;
    if (isOn)
    {
      music.playSound();
      musicToggle.setText("Turn Music Off");
    }
    else
    {
      music.stopSound();
      musicToggle.setText("Turn Music On");
    }
  }
}