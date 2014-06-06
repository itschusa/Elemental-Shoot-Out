/**
 * The ElementalApp class creates the Elemental Shoot-Out program by calling the splash screen.
 * 
 * @author The Panda Company
 * @version 1.0, May 14 2014.
 * @version 1.1, June 5 2014. (JavaDoc)
 */
public class ElementalApp
{
  /**
   * The main method constructs a new SplashScreen object for 10 seconds.
   * 
   * @param args - String[] - The argument to be taken in.
   */
  public static void main (String[] args)
  {
    new SplashScreen ("../Images/SplashSMALLER.png", 1000, true);
  }
}