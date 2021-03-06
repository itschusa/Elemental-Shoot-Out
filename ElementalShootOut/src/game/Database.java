package game;

import javax.swing.*;

/**
 * The Database class stores all the game icons, names and facts, except for the difficult game.
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
 * @version 1.7, June 8 2014. (Added facts, missing facts for easy, currently using placeholders for testing purposes.)
 */
public class Database
{
  /**
   * icon - ImageIcon - Stores the icon for a stable particle.
   */
  public static ImageIcon icon = new ImageIcon ("Images/Stable3.png");
  /**
   * icon2 - ImageIcon - Stores the icon for a unstable particle.
   */
  public static ImageIcon icon2 = new ImageIcon ("Images/Unstable3.png");
  /**
   * icon3 - ImageIcon - Stores the icon for a neutron.
   */
  public static ImageIcon icon3 = new ImageIcon ("Images/Neutron4.png");
  /**
   * icon4 - ImageIcon - Stores the icon for the launcher.
   */
  public static ImageIcon icon4 = new ImageIcon ("Images/Launcher.png");
  /**
   * alkaliMetals - String [] - Stores names of all possible alkali metals.
   */
  public static String[] alkaliMetals = new String[] {"Lithium","Sodium","Potassium","Rubidium","Cesium","Francium"};
  /**
   * icon5 - ImageIcon - Stores the icon for lithium.
   */
  public static ImageIcon icon5 = new ImageIcon ("Images/Lithium2.png");
  /**
   * icon6 - ImageIcon - Stores the icon for sodium.
   */
  public static ImageIcon icon6 = new ImageIcon ("Images/Sodium2.png");
  /**
   * icon7 - ImageIcon - Stores the icon for potassium.
   */
  public static ImageIcon icon7 = new ImageIcon ("Images/Potassium2.png");
  /**
   * icon8 - ImageIcon - Stores the icon for rubidium.
   */
  public static ImageIcon icon8 = new ImageIcon ("Images/Rubidium2.png");
  /**
   * icon9 - ImageIcon - Stores the icon for cesium.
   */
  public static ImageIcon icon9 = new ImageIcon ("Images/Cesium2.png");
  /**
   * icon10 - ImageIcon - Stores the icon for francium.
   */
  public static ImageIcon icon10 = new ImageIcon ("Images/Francium2.png");
  /**
   * icon11 - ImageIcon - Stores the icon for hydroxide.
   */
  public static ImageIcon icon11 = new ImageIcon ("Images/Hydroxide.png");
  /**
   * icon12 - ImageIcon - Stores the icon for an acid cloud.
   */
  public static ImageIcon icon12 = new ImageIcon ("Images/cloud.png");
  /**
   * icon13 - ImageIcon - Referenc variable to the icon for the base particles. 
   */
  public static ImageIcon icon13 = new ImageIcon ("Images/Base.png");
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
  /**
   * factList - String [][] - Stores a 2D array of strings, where each string is a chemistry fact. First dimension represents level number, second represents fact number.
   */
  public static String[][] factList = new String [][]{
    {"Nuclear reactions involve changes to an atom's neutrons and protons, not it's electrons!", 
      "Nuclear reactions are grouped into two main types: nuclear fusion and nuclear fission.", "Nuclear power plants use nuclear fission to create energy!", 
      "Nuclear reactions produce 1 million times more energy than chemical reactions.", "Fission is the process of splitting a heavier nucleus into lighter nuclei.", 
      "Nuclear fusion is the process where lighter nuclei combine to form a more stable heavier nucleus.", "Some types of radioactive decay include alpha decay, beta decay and gamma decay!", 
      "Nuclear fusion in elements before iron releases energy and becomes more stable.", "Nuclear fusion in elements after iron consumes energy and becomes less stable.", 
      "Fusion weapons are also known as hydrogen bombs!"},
      {"Acids have a pH value that is less than 7.", "Bases have a pH value that is greater than 7.", "There are two types of acids: binary acids and oxyacids.", 
        "The chemical formula of a base will end with Hydroxide or Bicarbonate.", "Common acids include orange juice, vinegar and stomach acid.", "Common bases include milk and baking soda.", 
        "Acids usually taste sour.", "Bases usually taste bitter.", "Carbon dioxide is a product of neutralization when the base is a bicarbonate.", "Products of a neutralization reaction: Water and a salt."},
        {"Ionic compounds are made up of two parts: a cation and an anion.", "Ionic bonds: the metal atom donates electrons, the non-metal recieves electrons.", 
          "Ionic compounds are almost always solid at room temperature.", "Negative ions are called anions. Non-metal ions are anions.", "Positive ions are called cations. Metal atoms are cations.", 
          "When naming ionic compounds, always name the positive ion first.", "Compounds must have a net (overall) charge of zero.", 
          "Ionic compounds generally have high melting points because of their lattice structure.", "Ionic compounds tend to be highly soluble in water, and their solutions are conductive.", 
          "Acids and bases are special types of ionic compounds."}
  };
  
}