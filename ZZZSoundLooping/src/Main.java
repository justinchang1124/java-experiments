
public class Main
{
  
  private static EasySound music;
  
  

  public static void main(String[] args)
  {
    
    music = new EasySound("O_S_Music.wav");
    music.loop();
    while (true)
    {
    System.out.println(1);
    }

  }

}
