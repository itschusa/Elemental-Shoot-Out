import java.awt.*;
import javax.swing.*;

/**
 * The "InstructionsPanel" class which is (by extension) a JPanel object. It displays the instructions of Elemental Shoot-Out. 
 * 
 * @author Chusa Nguyen
 * @version 1.0, May 25 2014. (Created the panel and added instructions.)
 */
public class InstructionsPanel extends JPanel
{
  /**
   * The constructor. It sets the layout manager of the panel, its background colour, and calls the appropriate methods for setting content to the panel. 
   */
  public InstructionsPanel()
  {
    setLayout(new GridBagLayout());
    setBackground(new Color(0,0,0,0));
    setText();
  }
  /**
   * The "setText" method which adds text (the program's gameplay instructions) in the form of a JLabel to the panel.
   * 
   * @param text - reference - References to the specified JLabel object.
   */
  private void setText()
  {
    JLabel text = new JLabel();
    text.setText ("<html>How to Play: <br> This program's gameplay is controlled through keyboard input using the arrow keys. <br>Left arrow key: move left. <br>Right arrow key: move right. <br> Up arrow key: shoot. <br><br>Game Level Objectives: <br> The goal of each level is to clear the targets at the top of the map. Each level is cleared when all the targets have been cleared, <br>but if you run out of bubbles in your inventory, the game will end. To win the game, you must play through <br>all three levels. Scores are based on the number of targets cleared.<br><br>1. Easy - Nuclear Reactions <br>Using a combination of fusion and fission bubbles, clear all the targets. Some targets favour fusion, other fission. Figure out the best to use!<br><br>2. Medium - Acids, Bases, and Neutralization<br>Shoot the appropriate number of OH ions at each target to add bases to your inventory. Watch out for the acid clouds!<br>The clouds will absorb your OH bubbles if they get hit! For full points, neutralize the acid clouds with your bases. <br><br>3. Difficult - Ionic Compounds <br>Based on the given charge of the target cations, shoot them with the approriate number of anions to make a compound. <br><br>To return to the main menu, navigate the menu bar or press F1.</html>");
    add(text);
  }
  
}