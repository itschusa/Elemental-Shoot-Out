import javax.swing.*;

/**
 * The Database class stores all the game icons, except for the difficult game.
 * 
 * @author Anqi Wu
 * @author Chusa Nguyen
 * @version 1.0, May 21 2014.
 * @version 1.1, May 24 2014. (Added launcher)
 * @version 1.2, May 28 2014. (Added the alkaliMetals array to store the names.)
 * @version 1.3, May 29 2014. (Alkali metal icons and javadoc)
 * @version 1.4, May 30 2014. (Added acid cloud obstacle.)
 * @version 1.5, May 31 2014. (Added all difficult level names, easy element icons upgraded)
 * @version 1.6, June 3 2014. (Added base icon for medium.)
 */
public class Database
{
  /**
   * icon - ImageIcon - Stores the icon for a stable particle.
   */
  public static ImageIcon icon = new ImageIcon ("../Images/Stable3.png");
  /**
   * icon2 - ImageIcon - Stores the icon for a unstable particle.
   */
  public static ImageIcon icon2 = new ImageIcon ("../Images/Unstable3.png");
  /**
   * icon3 - ImageIcon - Stores the icon for a neutron.
   */
  public static ImageIcon icon3 = new ImageIcon ("../Images/Neutron4.png");
  /**
   * icon4 - ImageIcon - Stores the icon for the launcher.
   */
  public static ImageIcon icon4 = new ImageIcon ("../Images/Launcher.png");
  /**
   * alkaliMetals - String [] - Stores names of all possible alkali metals.
   */
  public static String[] alkaliMetals = new String[] {"Lithium","Sodium","Potassium","Rubidium","Cesium","Francium"};
  /**
   * icon5 - ImageIcon - Stores the icon for lithium.
   */
  public static ImageIcon icon5 = new ImageIcon ("../Images/Lithium2.png");
  /**
   * icon6 - ImageIcon - Stores the icon for sodium.
   */
  public static ImageIcon icon6 = new ImageIcon ("../Images/Sodium2.png");
  /**
   * icon7 - ImageIcon - Stores the icon for potassium.
   */
  public static ImageIcon icon7 = new ImageIcon ("../Images/Potassium2.png");
  /**
   * icon8 - ImageIcon - Stores the icon for rubidium.
   */
  public static ImageIcon icon8 = new ImageIcon ("../Images/Rubidium2.png");
  /**
   * icon9 - ImageIcon - Stores the icon for cesium.
   */
  public static ImageIcon icon9 = new ImageIcon ("../Images/Cesium2.png");
  /**
   * icon10 - ImageIcon - Stores the icon for francium.
   */
  public static ImageIcon icon10 = new ImageIcon ("../Images/Francium2.png");
  /**
   * icon11 - ImageIcon - Stores the icon for hydroxide.
   */
  public static ImageIcon icon11 = new ImageIcon ("../Images/Hydroxide.png");
  /**
   * icon12 - ImageIcon - Stores the icon for an acid cloud.
   */
  public static ImageIcon icon12 = new ImageIcon ("../Images/cloud.png");
  /**
   * icon13 - ImageIcon - Referenc variable to the icon for the base particles. 
   */
  public static ImageIcon icon13 = new ImageIcon ("../Images/Base.png");
  /**
   * chargeNegativeOne - String [] - Stores an array of strings, where each string is the name of a particle with a charge of -1.
   */
  public static String[] chargeNegativeOne = new String[] {"Chloride","Bromide","Fluoride","Iodide","Hydroxide"};
  /**
   * chargeNegativeTwo - String [] - Stores an array of strings, where each string is the name of a particle with a charge of -2.
   */
  public static String[] chargeNegativeTwo = new String[] {"Sulfide","2Chloride","Carbonate","Sulphate","2Fluoride"};
  /**
   * chargeNegativeThree - String [] - Stores an array of strings, where each string is the name of a particle with a charge of -3.
   */
  public static String[] chargeNegativeThree = new String[] {"Phosphate"};
  /**
   * chargePositiveOne - String [] - Stores an array of strings, where each string is the name of a particle with a charge of +1.
   */
  public static String[] chargePositiveOne = new String[] {"Sodium","Silver","Potassium","Ammonium"};
  /**
   * chargePositiveTwo - String [] - Stores an array of strings, where each string is the name of a particle with a charge of +2.
   */
  public static String[] chargePositiveTwo = new String[] {"Barium", "Mercury","2Sodium","Calcium"};
  /**
   * chargePositiveThree - String [] - Stores an array of strings, where each string is the name of a particle with a charge of +3.
   */
  public static String[] chargePositiveThree = new String[] {"Iron","3Lithium","Aluminum"};
}