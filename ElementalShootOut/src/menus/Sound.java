package menus;

import java.applet.*;
import java.io.*;
import java.net.*;

/**
 * The "Sound" class is able to play sound files such as .wav and .mid files.
 * TO BE INTEGRATED INTO PROGRAM (WIP)
 * 
 * @author WolfCoder of dreamincode.net
 * @author Anita Huynh ("Wanderlust.midi")
 * @author Chusa Nguyen (Modifications)
 * @version 1.0, January 07 2006. (Original)
 * @version 2.0, May 19 2014. (Removed outer class, removed creation of Applet window.)
 * @version 2.1, June 10 2014. (More JavaDoc)
 */
public class Sound
{
  /**
   * song - reference - Refers to AudioClip that contains the sound to be played.
   */
  private AudioClip song;
  /**
   * songPath - reference - Location of the sound.
   */
  private URL songPath;
  /**
   * songName - reference - Refers to the String object representation of the name of the song to be played. 
   */
  private String songName = "Wanderlust.mid";
  
  /**
   * The class constructor. It loads an audio clip by looking in the specified path for the specified file name. 
   * 
   * @param c - Class - The Class object.
   * @param e - IOException - Catches the IOException if song is not found.
   * @exception IOException If an error occurs while trying to read from the specified file. 
   */
  public Sound ()
  {
    try
    {
      Class c = getClass();
      songPath = new URL(c.getResource(songName),songName);
      song = Applet.newAudioClip(songPath);
    }
    catch (IOException e)
    {
    }
  }
  
  /**
   * Loops the sound that is in the AudioClip.
   */
  public void playSound ()
  {
    song.loop();
  }
  
  /**
   * Stops the sound that is being looped by playSound().
   */
  public void stopSound()
  {
    song.stop();
  }
  
  /**
   * Plays the sound in the AudioClip once.
   */
  public void playSoundOnce()
  {
    song.play();
  }
}
