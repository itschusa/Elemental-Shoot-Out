//import files
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * The SplashScreen class creates the a splash screen, which displays the game logo, name, company name and logo, and
 * the developer names within a window. After a set number of seconds, the window disposes itself, and creates the game 
 * window.
 * 
 * @author Anqi Wu
 * @author Tony Colston (http://www.javaworld.com/article/2077467/core-java/java-tip-104--make-a-splash-with-swing.html)
 * @version 1.0, May 14, 2014.
 * @version 1.1, May 15, 2014.
 * @version 1.2, May 29, 2014. (Added another argument to constructor argument list and boolean firstRun).
 * @version 1.3, May 30, 2014. (Added mouse input.)
 */
public class SplashScreen extends JWindow
{
  protected static boolean firstRun;
  /**
   * This constructor creates an instance of SplashScreen with the image set by the string parameter and the time in
   * milliseconds before the splash screen closes set by the int parameter. The window is centred on the screen. This
   * constructor also adds two anonymous classes that implement Runnable. The first, closerRunner, closes the splash
   * screen window and creates the game window. The second, waitRunner, pauses the splash screen and then runs 
   * closeRunner.
   * @param fileName - String - The name of the image to be loaded.
   * @param waitTime - final int - The time taken before the splash screen closes.
   * @param label - JLabel - The JLabel which stores the image of the splash screen.
   * @param screenSize - Dimension - The size of the screen.
   * @param windowSize - Dimension - The size of the splash screen.
   * @param closerRunner - final Runnable - Closes the window and creates the game screen window.
   * @param waitRunner - final Runnable - Pauses the splash screen and runs closerRunner.
   * @param e - Exception - Catches errors regarding thread sleep, etc.
   * @param splashThread - Thread - The Thread that stores the Runnable object that is started.
   */
  public SplashScreen(String filename, final int waitTime, boolean firstRun)
  {
    super(); //create JWindow
    this.firstRun = firstRun;
    
    JLabel label = new JLabel(new ImageIcon(filename)); //create image label
    getContentPane().add(label); //add label to the container
    pack(); //displays window
    
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //returns screen size
    Dimension labelSize = label.getPreferredSize(); //returns size of splashscreen imagesetlocatii
    setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2)); //centers window
    
    if (!firstRun)
    {
      addMouseListener(new MouseAdapter()
                         {
        public void mousePressed(MouseEvent e)
        {
          setVisible(false);
          dispose();
        }
      });
    }
    
    //anonymous class - closes splashscreen and opens menu frames class
    final Runnable closerRunner = new Runnable()
    {
      public void run()
      {
        setVisible(false);
        dispose();
        if (SplashScreen.firstRun)
          createWindow();
      }
    };
    
    //anonymous class - pauses splashscreen and then runs closerRunner
    Runnable waitRunner = new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(waitTime);
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
    setAlwaysOnTop(true);
  }
  
  //create menu frames window
  /**
   * Creates a MenuFrames object to display the game screen.
   * @param showFrames - MenuFrames - The game window.
   */
  private void createWindow ()
  {
     new MenuFrames();
  }
}