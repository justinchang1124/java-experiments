import java.io.File;
import javax.sound.sampled.*;

public class EasySound
{

  private Clip clip;
  AudioInputStream audioInputStream = null;

  public EasySound(String fileName)
  {

    File soundFile = new File(fileName);
    
    try
    {
      audioInputStream = AudioSystem.getAudioInputStream(soundFile);
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

  }
  
  public void loop()
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
