
/**
 * @author Justin Chang
 * 
 *         The only modifiable class in the AAA grouping.
 *         The purpose of this class is to provide a sanitized and properly ordered
 *         implementation of the methods of the other classes.
 * 
 *         Commands that are not shown:
 *         utilizing a sheet to make a sprite
 */

import java.awt.Graphics;

public class Central extends TempCentral
{
  // Initializers:
  // Width of the screen.
  public static final int SCREEN_WIDTH = 1000;

  // Height of the screen.
  public static final int SCREEN_HEIGHT = 1000;

  // Number of properties that each value has.
  // x, y, z, velX, velY, velZ are treated separately.
  public static final int PROPERTY = 1;

  // All key and mouse inputs.
  private int keyCode;

  private int xM;

  private int yM;

  private int clicks;

  private int button;

  private int wheelRotation;

  private int eventMouse;

  private int eventKeyboard;

  // All sounds.

  // All backgrounds and sprites.
  public static final String b_g_files[] = {"/black_screen.png"};

  public static final String sprites[] = {"/sprite_sheet.png", "/sprite_sheet_v2.png"};

  // All classes.
  private Saver saver;

  private GetImage getImage;

  private Handler handler;
  
  private MenuCredit menuCredit;
  private MenuHelp menuHelp;
  private MenuMedal menuMedal;
  private MenuStat menuStat;
  private MenuTrophy menuTrophy;
  private MenuOptions menuOptions;
  private Battle battle;
  private OpenMain openMain;
  private Preview preview;
  private Campaign campaign;
  private Single single;
  private SetupSkill setupSkill;
  private SetupUnit setupUnit;

  // All constants.
  public static final int WIDTH = 1000, HEIGHT = 1000;
  public static final int UNIT_NUMBER = 50;
  public static final int SKILL_NUMBER = 15;
  public static final int MEDAL_NUMBER = 14;
  public static final int TROPHY_NUMBER = 14;

  public static STATE state = STATE.OpenMain;
  public static boolean isPaused;

  // This is if the unit has been unlocked.
  // 0 = locked, 1 = unlocked.
  public static long unitUnlock[] = new long[UNIT_NUMBER];
  // This counts the number of life upgrades.
  public static long unitLife[] = new long[UNIT_NUMBER];
  // This counts the number of speed upgrades.
  public static long unitSpeed[] = new long[UNIT_NUMBER];
  // This counts the number of range upgrades.
  public static long unitRange[] = new long[UNIT_NUMBER];
  // This counts the number of damage upgrades.
  public static long unitDamage[] = new long[UNIT_NUMBER];
  // This counts the number of training time upgrades.
  public static long unitTime[] = new long[UNIT_NUMBER];
  // This counts the number of upgrades to each skill.
  public static long skill[] = new long[SKILL_NUMBER];

  // This counts which achievements are unlocked and their degree.
  public static long trophyUnlock[] = new long[TROPHY_NUMBER];
  // This counts which medals are unlocked.
  // 0 = locked, 1 = unlocked.
  public static long medalUnlock[] = new long[MEDAL_NUMBER];
  // This is the number of wins in each game mode that the player has consecutively.
  // USA, Russia, China, Single Easy, Single Normal, Single Hard, Single Impossible.
  public static long wins[] = new long[7];
  // This is the statistics of the game.
  public static long stats[] = new long[14];

  // This is the amount of money the player has.
  public static long money;
  // This is the amount of experience the player has.
  public static long xp;
  // This is the game's current difficulty.
  // 0 = easy, 1 = normal, 2 = hard, 3 = impossible.
  public static long difficulty;
  // This is the game mode.
  // 0 = Unlimited, 1 = Limited, 2 = Single Mode.
  public static long gameMode;
  // This is the player's chosen country.
  // 0 = USA, 1 = Russia, 2 = China.
  public static long country;
  // This is the player's current campaign level.
  public static long level = 1;
  // This is the stage of the current battle.
  public static long field;
  // This is the amount of game life remaining.
  public static long gameLife;

  // The place where all initialization occurs in the program.
  public void initialize()
  {
    // Final memory allocation of saves.
    saver = new Saver("/Saves", "/Game", new int[] {5}, new int[] {5}, new int[] {5}, new int[] {5});
    saver.doesThisExist(1);
    saver.load(1);
    // saver.deleteSaveFile(1);
    getImage = new GetImage(SCREEN_WIDTH, SCREEN_HEIGHT, b_g_files, sprites);
    handler = new Handler();
    menuCredit = new MenuCredit();
    menuHelp = new MenuHelp();
    menuMedal = new MenuMedal();
    menuStat = new MenuStat();
    menuTrophy = new MenuTrophy();
    menuOptions = new MenuOptions();
    battle = new Battle();
    openMain = new OpenMain();
    preview = new Preview();
    campaign = new Campaign();
    single = new Single();
    setupSkill = new SetupSkill();
    setupUnit = new SetupUnit();
  }

  // All tick methods.
  public void tick(long frameCount)
  {
    saver.tick();
    handler.tick();
    if (state == STATE.MenuCredit)
      menuCredit.tick();
    if (state == STATE.MenuHelp)
      menuHelp.tick();
    if (state == STATE.MenuMedal)
      menuMedal.tick();
    if (state == STATE.MenuStat)
      menuStat.tick();
    if (state == STATE.MenuTrophy)
      menuTrophy.tick();
    if (state == STATE.MenuOptions)
      menuOptions.tick();
    if (state == STATE.Battle && !isPaused)
    {
      battle.tick();
    }
    if (state == STATE.OpenMain)
      openMain.tick();
    if (state == STATE.Preview)
      preview.tick();
    if (state == STATE.Campaign)
      campaign.tick();
    if (state == STATE.Single)
      single.tick();
    if (state == STATE.SetupSkill)
      setupSkill.tick();
    if (state == STATE.SetupUnit)
      setupUnit.tick();
    // System.out.println(frameCount);
  }

  // All render methods.
  public void render(Graphics g, long frameCount)
  {
    getImage.render(g, 0);
    if (state == STATE.MenuCredit)
      menuCredit.render(g);
    if (state == STATE.MenuHelp)
      menuHelp.render(g);
    if (state == STATE.MenuMedal)
      menuMedal.render(g);
    if (state == STATE.MenuStat)
      menuStat.render(g);
    if (state == STATE.MenuTrophy)
      menuTrophy.render(g);
    if (state == STATE.MenuOptions)
      menuOptions.render(g);
    if (state == STATE.Battle)
      battle.render(g);
    if (state == STATE.OpenMain)
      openMain.render(g);
    if (state == STATE.Preview)
      preview.render(g);
    if (state == STATE.Campaign)
      campaign.render(g);
    if (state == STATE.Single)
      single.render(g);
    if (state == STATE.SetupSkill)
      setupSkill.render(g);
    if (state == STATE.SetupUnit)
      setupUnit.render(g);

    Draw.fps((int) frameCount, g);
  }

  // Detects if the mouse is over a certain point.
  public boolean mouseOver(int x, int y, int width, int height)
  {
    if (xM > x && xM < x + width && yM > y && yM < y + height)
    {
      return true;
    }
    return false;
  }

  // Manages the keyboard.
  public void eventKeyboardOutput(int eventDescription, int keyboardCode)
  {
    keyCode = keyboardCode;
    eventKeyboard = eventDescription;
    saver.save(1);
  }

  // Manages the mouse.
  public void eventMouseOutput(int eventDescription, int xMouse, int yMouse, int buttonNum, int clickCount)
  {
    eventMouse = eventDescription;
    xM = xMouse;
    yM = yMouse;
    button = buttonNum;
    clicks = clickCount;
    int midX1 = (SCREEN_WIDTH - 200) / 2;

    if (clickCount > 0 && eventMouse == 3)
    {
//      System.out.println(country);
//      System.out.println(difficulty);
//      System.out.println(gameMode);
      if (state == STATE.MenuCredit)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
      }
      if (state == STATE.MenuHelp)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
      }
      if (state == STATE.MenuMedal)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
      }
      if (state == STATE.MenuStat)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
      }
      if (state == STATE.MenuTrophy)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
      }
      if (state == STATE.MenuOptions)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
      }
      if (state == STATE.Battle)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          isPaused = !isPaused;
        }
      }
      if (state == STATE.OpenMain)
      {
        if (mouseOver(10, 150, 200, 64))
        {
          state = STATE.Campaign;
        }
        
        if (mouseOver(midX1, 750, 200, 64))
        {
          SaveManager.saveAllConstants();
          System.exit(1);
        }
        
        if (mouseOver(10, 250, 200, 64))
        {
          state = STATE.Single;
          gameMode = 2;
        }

        if (mouseOver(10, 350, 200, 64))
        {
          state = STATE.MenuHelp;
        }

        if (mouseOver(10, 450, 200, 64))
        {
          state = STATE.MenuOptions;
        }

        if (mouseOver(10, 550, 200, 64))
        {
          state = STATE.MenuCredit;
        }

        if (mouseOver(780, 350, 200, 64))
        {
          state = STATE.MenuMedal;
        }

        if (mouseOver(780, 450, 200, 64))
        {
          state = STATE.MenuStat;
        }

        if (mouseOver(780, 550, 200, 64))
        {
          state = STATE.MenuTrophy;
        }
      }
      if (state == STATE.Preview)
      {

      }
      if (state == STATE.Campaign)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
        if (level <= 1)
        {
          if (mouseOver(200, 330, 200, 64))
          {
            Campaign.highlight1 = 0;
          }
          if (mouseOver(400, 330, 200, 64))
          {
            Campaign.highlight1 = 1;
          }
          if (mouseOver(600, 330, 200, 64))
          {
            Campaign.highlight1 = 2;
          }
          if (mouseOver(100, 430, 200, 64))
          {
            Campaign.highlight2 = 0;
          }
          if (mouseOver(300, 430, 200, 64))
          {
            Campaign.highlight2 = 1;
          }
          if (mouseOver(500, 430, 200, 64))
          {
            Campaign.highlight2 = 2;
          }
          if (mouseOver(700, 430, 200, 64))
          {
            Campaign.highlight2 = 3;
          }
          if (mouseOver(250, 530, 200, 64))
          {
            Campaign.highlight3 = 0;
          }
          if (mouseOver(550, 530, 200, 64))
          {
            Campaign.highlight3 = 1;
          }
          if (mouseOver(230, 250, 500, 64))
          {
            country = Campaign.highlight1;
            difficulty = Campaign.highlight2;
            gameMode = Campaign.highlight3;
            state = STATE.Battle;
          }
        }
        else
        {
          
        }
      }
      if (state == STATE.Single)
      {
        if (mouseOver(midX1, 850, 200, 64))
        {
          state = STATE.OpenMain;
        }
      }
      if (state == STATE.SetupSkill)
      {

      }
      if (state == STATE.SetupUnit)
      {

      }
    }
  }

  // Manages the mouse wheel.
  public void eventWheelOutput(int wRotation)
  {
    wheelRotation = wRotation;
  }

  public static void main(String[] args)
  {
    new Loader(SCREEN_WIDTH, SCREEN_HEIGHT, 60, 1, "Battle Gear");
  }
}
