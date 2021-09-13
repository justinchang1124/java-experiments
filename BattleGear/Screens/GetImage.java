import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class GetImage extends Screen
{
  private BufferedImage 
  back_menu_credit, 
  back_menu_help, 
  back_menu_medal,
  back_menu_stat,
  back_menu_trophy,
  back_menu_options,
  back_open_main,
  back_preview,
  back_campaign,
  back_single,
  back_setup_skill,
  back_setup_unit;
  public static int fontNum;
  public static String fontChoice;

  public void load()
  {
    SetImage setImage = new SetImage();
    Sprite ss;

    ss = new Sprite(setImage.load("/black_screen.png"));
    back_menu_credit = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);

    ss = new Sprite(setImage.load("/black_screen.png"));
    back_menu_help = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_menu_medal = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_menu_stat = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_menu_trophy = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_menu_options = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_open_main = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_preview = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_campaign = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_single = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_setup_skill = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
    
    ss = new Sprite(setImage.load("/black_screen.png"));
    back_setup_unit = ss.grabImage(1, 1, 1000, 1000, 1000, 1000);
  }
  
  public void tick()
  {
    
  }

  // Renders the background for each state.
  public void render(Graphics g)
  {
    // Don't forget the battle backgrounds!
    if (Central.state == STATE.Battle)
      g.drawImage(back_open_main, 0, 0, null);
    if (Central.state == STATE.MenuCredit)
      g.drawImage(back_menu_credit, 0, 0, null);
    if (Central.state == STATE.MenuHelp)
      g.drawImage(back_menu_help, 0, 0, null);
    if (Central.state == STATE.MenuMedal)
      g.drawImage(back_menu_medal, 0, 0, null);
    if (Central.state == STATE.MenuStat)
      g.drawImage(back_menu_stat, 0, 0, null);
    if (Central.state == STATE.MenuTrophy)
      g.drawImage(back_menu_trophy, 0, 0, null);
    if (Central.state == STATE.MenuOptions)
      g.drawImage(back_menu_options, 0, 0, null);
    if (Central.state == STATE.OpenMain)
      g.drawImage(back_open_main, 0, 0, null);
    if (Central.state == STATE.Preview)
      g.drawImage(back_preview, 0, 0, null);
    if (Central.state == STATE.Campaign)
      g.drawImage(back_campaign, 0, 0, null);
    if (Central.state == STATE.Single)
      g.drawImage(back_single, 0, 0, null);
    if (Central.state == STATE.SetupSkill)
      g.drawImage(back_setup_skill, 0, 0, null);
    if (Central.state == STATE.SetupUnit)
      g.drawImage(back_setup_unit, 0, 0, null);
  }
}
