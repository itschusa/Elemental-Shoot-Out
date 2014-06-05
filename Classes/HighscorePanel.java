//JAVADOC
import java.awt.*;
import javax.swing.*;
import java.io.*;

/**
 * 
 * @author Chusa Nguyen
 * 
 * @version 1.1, June 4 2014. (Basic components.)
 */
public class HighscorePanel extends JPanel
{
  private final int NAME_COLUMN  = 13;
  private final int SCORE_COLUMN = 13;
  private final int LEVEL_COLUMN = 13;
  private final int BLANKS = 10;
  private final int MAX_SCORES = 10;
  private final String fileName = "HighScores.pan";
  private JLabel[] ranks = new JLabel[MAX_SCORES];
  private JTextField box = new JTextField(50);
  private GridBagConstraints constraints = new GridBagConstraints();
  
  public HighscorePanel()
  {
    setLayout(new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    setLabels();
    readScores();
    displayScores();
  }
  
  private void setLabels()
  {
    constraints = new GridBagConstraints();
    constraints.gridy = 0;
    String temp = "";
    for (int x = 0; x < MAX_SCORES; x++)
    {
      temp = " ";
      constraints.gridy = x;
      temp = temp + ((Integer)(x+1)).toString() + ". ";
      ranks[x] = new JLabel();
      ranks[x].setText(temp);
      add(ranks[x]);
    }
  }
  
  private void displayScores()
  {
    for (int x = 0; x < 10;  x++)
    {
      
    }
  }
  private void readScores()
  {
    try
    {
      BufferedReader input = new BufferedReader (new FileReader (fileName));
    }
    catch (IOException e)
    {
      writeScores();
      readScores();
    }
  }
  
  private void writeScores()
  {
    try
    {
      PrintWriter output = new PrintWriter (new FileWriter (fileName));
    }
    catch (IOException ex)
    {
    }
  }
  
  private void sortScores()
  {
  }
}