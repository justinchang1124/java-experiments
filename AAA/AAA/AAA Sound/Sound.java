
/*
 * Attribution:
 * http://www.dreamincode.net/forums/topic/343804-how-to-add-background-music-to-my-2d-platformer-
 * game/
 */

import javax.sound.sampled.*;

/**
 * Creates a clip that can be played, looped, stopped, and have its volume changed.
 * 
 * @author Justin C
 */
public class Sound
{
  private Clip clip;

  public FloatControl gainControl;

  /**
   * Constructs a Clip clip and a FloatControl gainControl.
   * 
   * @param path
   * The path of the file in the form "/song_name.wav".
   */
  public Sound(String path)
  {
    try
    {
      AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(path));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
      gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  /**
   * Adds "change" decibels to the volume of the clip.
   * 
   * @param change
   */
  public void volume(float change)
  {
    gainControl.setValue(gainControl.getValue() + change);
  }

  /**
   * Takes a boolean and loops "clip" if that boolean is false.
   * 
   * @param soundOff
   */
  public void loop(boolean soundOff)
  {
    if (!soundOff)
    {
      try
      {
        if (clip != null)
        {
          new Thread()
          {

            public void run()
            {
              synchronized (clip)
              {
                clip.setFramePosition(0);
                clip.loop(Clip.LOOP_CONTINUOUSLY);
              }
            }
          }.start();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * Takes a boolean and plays "clip" if that boolean is false.
   * 
   * @param soundOff
   */
  public void play(boolean soundOff)
  {
    if (!soundOff)
    {
      try
      {
        if (clip != null)
        {
          new Thread()
          {
            public void run()
            {
              synchronized (clip)
              {
                clip.stop();
                clip.setFramePosition(0);
                clip.start();
              }
            }
          }.start();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  /**
   * Stops the current clip.
   */
  public void stop()
  {
    if (clip == null)
    {
      return;
    }
    clip.stop();
  }
}
