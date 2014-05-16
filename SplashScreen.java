import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * @author Anqi Wu
 * @author Tony Colston
 * @version 1.0, May 14 2014.
 */
//Tony Colston : http://www.javaworld.com/article/2077467/core-java/java-tip-104--make-a-splash-with-swing.html
public class SplashScreen extends JWindow
{
  public SplashScreen(String filename, int waitTime)
  {
    super(); //create JWindow
    JLabel label = new JLabel(new ImageIcon(filename)); //create splashscreen image label
    System.out.println (label.getIcon().toString());//TEST
    getContentPane().add(label); //add label to the container
    pack(); //displays window
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //returns screen size
    Dimension labelSize = label.getPreferredSize(); //returns size of splashscreen imagesetlocatii
    System.out.println (labelSize); //TEST
    setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2)); //centers window
    
    final int pause = waitTime; //sets milliseconds to keep splashscreen for
    
    //closes splashscreen and opens menu frames class
    final Runnable closerRunner = new Runnable()
    {   
      public void run()
      {
        setVisible(false);
        dispose();
        createWindow();
      }
    };
    
    //pauses splashscreen and then runs closerRunner
    Runnable waitRunner = new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(pause);
          SwingUtilities.invokeAndWait(closerRunner);
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
      }
    };
    
    setVisible(true); //makes window visible
    Thread splashThread = new Thread(waitRunner, "Splash"); //creates a new thread for waitRunner
    splashThread.start(); //starts thread
  }
  
  //create menu frames window
  public void createWindow ()
  {
     MenuFrames showFrames = new MenuFrames();
  }
  
  //main method that creates the splashscreen
  public static void main (String[] args)
  {
    new SplashScreen ("SplashSMALLER.png", 4000);
  }
}