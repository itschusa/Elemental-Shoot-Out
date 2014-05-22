/**
 * The CurrentPlayer class stores the score of the player.
 * 
 * @author Anqi Wu
 * @version 1.0, May 21 2014.
 */
public class CurrentPlayer
{
  //user points
  private int points;
  
  //add points
  public void addPoints (int add)
  {
    points += add;
  }
  
  //remove points
  public void removePoints (int remove)
  {
    points -= remove;
    if (points < 0)
      points = 0;
  }
  
  //return points
  public int getCurrentPoints ()
  {
    return points;
  }
}