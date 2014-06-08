import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.StringTokenizer;

/**
 * The "HighscorePanel" class which extends the "JPanel" class.
 * This class manages all score related information by reading from and writing to an external file. 
 * 
 * @author Chusa Nguyen
 * @version 1.1, June 4 2014. (Completed basic implementation. Missing: printer functionality, completed javadoc.)
 * @version 1.2, June 5 2014. (Completed javadoc. Need to test printer functionality.)
 * @version 1.3, June 8 2014. (Added menu button.)
 */
public class HighscorePanel extends JPanel
{
  /**
   * MAX_SCORES - final int - Represents the maximum number of scores which can be stored. 
   */
  private final int MAX_SCORES = 10;
  /**
   * constraints - reference - Reference variable for the corresponding GridBagConstraints object.
   */
  private GridBagConstraints constraints = new GridBagConstraints();
  /**
   * fileName - final String - Stores the name of the highscore file to be read from and written to.
   */
  private final String fileName = "HighScores.pan";
  /**
   * fileHeader - final String - Stores the file header for all highscore files.
   */
  private final String fileHeader = "The Panda Company";
  /**
   * fileInput - String [] - An array of Strings, where each index is one line of input from the highscore file.
   */
  private String [] fileInput = new String[MAX_SCORES];
  /**
   * ranks - JLabel [] - An array of JLabel objects, where each index is a number from 1 to the maximum number of scores.
   */
  private JLabel[] ranks = new JLabel [MAX_SCORES];
  /**
   * names - JLabel [] - An array of JLabel object references, where each index is a JLabel object that stores the name of the player at that corresponding index.
   */
  private JLabel[] names = new JLabel [MAX_SCORES];
  /**
   * scores - JLabel [] - An array of JLabel object references, where the object at each index is the corresponding score (ranked in descending order).
   */
  private JLabel[] scores = new JLabel [MAX_SCORES];
  /**
   * levels - JLabel [] - An array of JLabel object references, where the object at each index stores the difficulty of the player at that index.
   */
  private JLabel[] levels = new JLabel [MAX_SCORES];
  /**
   * resetButton - reference - Reference variable for the corresponding JButton object. 
   */
  protected JButton printButton = new JButton ("Print Scores");
  /**
   * newName - String - Stores the name of the user. 
   */
  private String newName;
  /**
   * newScore - int - Represents the current score of the user.
   */
  private int newScore;
  /**
   * newLevel - String - Stores the name of the level the user just finished playing. 
   */
  private String newLevel;
  /**
   * menuButton - reference - References to the specified JButton object. 
   */
  protected JButton menuButton = new JButton ("Return to Menu");
  
  /**
   * The default class constructor. 
   * This constructor sets the layout manager of the panel, as well as its background colour. 
   * It then calls the appropriate methods to display the highscores to the user.
   * This constructor is accessed through the main menu. Its primary function is to display scores. 
   */
  public HighscorePanel()
  {
    setLayout(new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    displayFile();
  }
  
  /**
   * An overloaded constructor of the "HighscorePanel" class. 
   * This constructor is accessed automatically after one of two scenarios: the user gets to a gameover screen, or the
   * user beats the game and reaches the winning screen.
   * This constructor writes the new score to highscores file, and sorts it if necessary. 
   * 
   * @param newName String - Stores the user's name.
   * @param newScore int - Represents the user's score.
   * @param newLevel String - Stores the last difficulty the user played.
   */
  public HighscorePanel (String newName, int newScore, String newLevel)
  {
    this.newName = newName;
    this.newScore = newScore;
    this.newLevel = newLevel;
    
    writeFile(false);
  }
  
  /**
   * The "createLabels" method. It initializes all indices within each array of JLabels using a for loop.
   * 
   * @param temp String - Stores the text to be added to each index of ranks.
   * @param x int - For loop counter. 
   */
  private void createLabels()
  {
    String temp = "";
    for (int x = 0; x < MAX_SCORES; x++)
    {
      temp = ((Integer)(x+1)).toString() + ".";
      ranks [x] = new JLabel();
      names [x] = new JLabel();
      scores [x] = new JLabel("");
      levels [x] = new JLabel();
      
      ranks[x].setText (temp);
    }
  }
  
  /**
   * The "readFile" method. It reads in an existing highscores file and stores the information in an array of strings
   * within a try block. The while loop is used to continuously read through every line of the input file. 
   * 
   * @param input reference - Reference variable to the corresponding BufferedReader object. 
   * @param line String - Stores the current line of text being read.
   * @param index int - Represents the current index number being accessed in fileInput.
   * @param e reference - Reference variable for the corresponding IOException object. 
   * @exception If an error occurs while trying to access the file to read from. 
   */
  private void readFile()
  {
    try
    {
      BufferedReader input = new BufferedReader (new FileReader (fileName));
      String line = "";
      int index = 0;
      
      while (index < MAX_SCORES)
      {
        line = input.readLine();
        if (line == null)
          break;
        if (!line.equals (fileHeader) && !line.equals(""))
        {
          fileInput[index] = line;
          index++;
        }
      }//end: reading file into array
    }
    catch(IOException e)
    {
      System.out.println (e);
      writeFile(true);
    }
  }
  
  /**
   * The "assignLabels" method. It uses a loop to assign values to all indices of each JLabel array by splitting the 
   * Strings within fileInput into tokens. 
   * 
   * @param token reference - Reference variable to the corresponding StringTokenizer object.
   * @param index int - Represents the current index to be accessed in each array. 
   */
  private void assignLabels()
  {
    createLabels();
    StringTokenizer token;
    int index = 0;
    
    while (index < MAX_SCORES && fileInput[index] != null)
    {
      token = new StringTokenizer (fileInput[index]);
      names[index].setText(token.nextToken());
      scores[index].setText(token.nextToken());
      levels[index].setText(token.nextToken());
      index++;
    }//end: setting label text from input array
  }
  
  /**
   * The "fileExists" method. It attempts to read the first line of the highscore file to determine whether
   * or not the file has been created yet in a try block. 
   * 
   * @return Returns true if there is an existing file, returns false if no file is found. 
   * 
   */
  private boolean fileExists()
  {
    try
    {
      BufferedReader input = new BufferedReader (new FileReader (fileName));
      String testLine = input.readLine();
    }
    catch (IOException e)
    {
      return false;
    }
    return true;
  }
  
  /**
   * The "writeFile" method. It creates a highscores file which stores the top 10 scores in descending order. 
   * The try block is used while attempting to write to an output file, the conditional statements determine whether
   * a blank file should be written or if it will contain entries, and the for loop is used to output multiple
   * lines of text. 
   * 
   * @param empty boolean - Represents whether the file will contain scores, or just the file header. 
   * @param output reference - Reference variable to the corresponding PrintWriter object.
   * @param temp int - Represents the maximum value for the for loop's stop condition. 
   * @param x int - For loop counter.
   * @param e reference - Reference variable to the corresponding IOException object.
   * @param ex reference - Reference variable to the corresponding NumberFormatException object. 
   * @exception IOException If an error occurs during the process of reading from and writing to a file. 
   * @exception NumberFormatException If an invalid integer is being used. 
   */
  private void writeFile(boolean empty)
  {
    try
    {
      PrintWriter output;
      int temp = 0;
      int last = MAX_SCORES;
      boolean printed = false;
      
      if (empty)
      {
        output = new PrintWriter (new FileWriter (fileName));
        output.println (fileHeader);
      }
      else
      {
        if (!fileExists())
        {
          output = new PrintWriter (new FileWriter(fileName));
          output.println (fileHeader);
          output.println ();
          output.println (newName + " " + newScore + " " + newLevel);
        }
        else
        {
          readFile();
          assignLabels();
          output = new PrintWriter (new FileWriter (fileName));
          output.println (fileHeader);
          output.println ();
          for (int x = 0; x < last; x++)
          {
            if (!(scores[x].getText()).equals(""))
            {
              temp = Integer.parseInt(scores[x].getText());
              if (!printed && newScore > temp)
              {
                output.println (newName + " " + newScore + " " + newLevel);
                last --;
                printed = true;
              }
              output.println (fileInput[x]);
            }//end if: score exists
            else
            {
              if (x < last - 1 && !printed)
                output.println (newName + " " + newScore + " " + newLevel);
              break;
            }
          }//end for: print all scores
        }//end if: overwriting a file
      }
      output.close();
    }
    catch(IOException e)
    {
      System.out.println(e);
    }
    catch(NumberFormatException ex)
    {
      System.out.println (ex);
    }
  }
  
  /**
   * The "displayFile" method. It reads in the current highscores file and displays it by setting the appropriate JLabel 
   * objects to the current JPanel. The for loop is used to set multiple labels in columns. 
   * 
   * @param nameLabel reference - Reference variable to the corresponding JLabel object.
   * @param scoreLabel reference - Reference variable to the corresponding JLabel object.
   * @param levelLabel reference - Reference variable to the corresponding JLabel object.
   * @param titleLabel reference - Reference variable to the corresponding JLabel object. 
   * @param  y int - For loop counter. 
   */
  private void displayFile()
  {
    readFile();
    assignLabels();
    JLabel nameLabel = new JLabel ("Name");
    JLabel scoreLabel = new JLabel ("Score");
    JLabel levelLabel = new JLabel ("Level");
    JLabel titleLabel = new JLabel (new ImageIcon ("../Images/Highscores.png"));
    
    //set arrays of JLabels 
    for (int y = 0; y < MAX_SCORES; y++)
    {
      constraints.gridy = y+2;
      constraints.gridx = 0;  
      constraints.insets = new Insets (5, 10, 5, 10);
      add (ranks[y], constraints);
      
      constraints.insets = new Insets(5, 50, 5, 50);
      constraints.gridx = 1;
      add (names[y], constraints);
      
      constraints.gridx = 2;
      add (scores[y], constraints);
      
      constraints.gridx = 3;
      add (levels[y], constraints);
    }
    
    //set heading labels
    constraints.gridy = 1;
    constraints.gridx = 1;
    add (nameLabel, constraints);
    constraints.gridx = 2;
    add (scoreLabel, constraints);
    constraints.gridx = 3;
    add (levelLabel, constraints);
    
    //set title label
    constraints.gridy = 0;
    constraints.gridx = 0;
    constraints.gridwidth = 4;
    add (titleLabel, constraints);
    
    
    constraints.gridy = 12;
    constraints.gridx = 4;
    constraints.gridwidth = 1;
    constraints.fill = constraints.HORIZONTAL;
    add (printButton, constraints);
    constraints.gridy = 13;
    add (menuButton, constraints);
  }
  
  /**
   * The "printScores" method. It prints the current highscores file, formatted into three columns. 
   * The for loop is used to repeatedly print all score entries (10). 
   * 
   * @param printer reference - Reference variable for the corresponding Printer object.
   * @param x int - For loop counter.
   */
  protected void printScores()
  {
    Printer printer = new Printer (new Font ("Franklin Gothic Book", Font.PLAIN, 14));
    String temp = "";
    
    printer.println ("", "Elemental Shoot-Out", "");
    printer.println ("", fileHeader, "");
    printer.println();
    printer.println ("Name", "Score", "Level");
    printer.println();
    for (int x = 0; x < MAX_SCORES; x++)
    {
      temp = ranks[x].getText() + " " + names[x].getText();
      printer.println (temp, scores[x].getText(), levels[x].getText());
    }
    //printer.printImage();
    printer.printUsingDialog();
  }
  
  /**
   * The "resetScores" method. It deletes all existing scoring information and overwrites the current
   * highscores file with a blank one. 
   */
  protected void resetScores()
  {
    writeFile (true);
    displayFile();
  }
}