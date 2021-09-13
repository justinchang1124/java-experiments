
/**
 * 
 * This class was created from this:
 * http://www.dreamincode.net/forums/topic/343804-how-to-add-background-music-to-my-2d-platformer-game/
 * 
 */

import javax.sound.sampled.*;

public class Sound
{
  private Clip clip;

  public FloatControl gainControl;

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
  
  // Adds "change" decibels to the volume.
  public void volume(float change)
  {
    gainControl.setValue(gainControl.getValue() + change);
  }

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

  public void stop()
  {
    if (clip == null)
    {
      return;
    }
    clip.stop();
  }
}
