import java.io.Serializable;

public class SaveFile implements Serializable
{
  // This is if the unit has been unlocked.
  // 0 = locked, 1 = unlocked.
  private long unitUnlock[] = new long[Central.UNIT_NUMBER];
  // This counts the number of life upgrades.
  private long unitLife[] = new long[Central.UNIT_NUMBER];
  // This counts the number of speed upgrades.
  private long unitSpeed[] = new long[Central.UNIT_NUMBER];
  // This counts the number of range upgrades.
  private long unitRange[] = new long[Central.UNIT_NUMBER];
  // This counts the number of damage upgrades.
  private long unitDamage[] = new long[Central.UNIT_NUMBER];
  // This counts the number of training time upgrades.
  private long unitTime[] = new long[Central.UNIT_NUMBER];
  // This counts the number of upgrades to each skill.
  private long skill[] = new long[Central.SKILL_NUMBER];

  // This counts which achievements are unlocked and their degree.
  private long trophyUnlock[] = new long[Central.TROPHY_NUMBER];
  // This counts which medals are unlocked.
  // 0 = locked, 1 = unlocked.
  private long medalUnlock[] = new long[Central.MEDAL_NUMBER];
  // This is the number of wins in each game mode that the player has.
  // USA, Russia, China, Single Easy, Single Normal, Single Hard, Single Impossible.
  private long wins[] = new long[7];
  // This is the statistics of the game.
  private long stats[] = new long[14];

  // This is the amount of money the player has.
  private long money;
  // This is the amount of experience the player has.
  private long xp;
  // This is the game's current difficulty.
  // 0 = easy, 1 = normal, 2 = hard, 3 = impossible.
  private long difficulty;
  // This is the game mode.
  // 0 = Unlimited, 1 = Limited, 2 = Single Mode.
  private long gameMode;
  // This is the player's chosen country.
  // 0 = USA, 1 = Russia, 2 = China.
  private long country;
  // This is the player's current campaign level.
  private long level;
  // This is the stage of the current battle.
  private long field;
  // This is the amount of game life remaining.
  private long gameLife;

  public SaveFile()
  {
    setDefault();
  }

  public void setDefault()
  {
    for (int i = 0; i < Central.UNIT_NUMBER; i++)
    {
      setConstant(SAVE.unitUnlock, i, 0);
      setConstant(SAVE.unitLife, i, 0);
      setConstant(SAVE.unitSpeed, i, 0);
      setConstant(SAVE.unitRange, i, 0);
      setConstant(SAVE.unitDamage, i, 0);
      setConstant(SAVE.unitTime, i, 0);
    }

    for (int i = 0; i < Central.SKILL_NUMBER; i++)
    {
      setConstant(SAVE.skill, i, 0);
    }

    for (int i = 0; i < Central.TROPHY_NUMBER; i++)
    {
      setConstant(SAVE.trophyUnlock, i, 0);
    }

    for (int i = 0; i < Central.MEDAL_NUMBER; i++)
    {
      setConstant(SAVE.medalUnlock, i, 0);
    }

    for (int i = 0; i < 7; i++)
    {
      setConstant(SAVE.wins, i, 0);
    }

    for (int i = 0; i < 14; i++)
    {
      setConstant(SAVE.stats, i, 0);
    }

    setConstant(SAVE.money, 0, 0);
    setConstant(SAVE.xp, 0, 0);
    setConstant(SAVE.difficulty, 0, 0);
    setConstant(SAVE.gameMode, 0, 0);
    setConstant(SAVE.country, 0, 0);
    setConstant(SAVE.level, 0, 1);
    setConstant(SAVE.field, 0, 0);
    setConstant(SAVE.gameLife, 0, 0);
  }

  public void writeString()
  {

  }

  public long getConstant(SAVE save, int arrayNum)
  {
    long result = 0;

    if (save == SAVE.unitUnlock)
      result = unitUnlock[arrayNum];
    if (save == SAVE.unitLife)
      result = unitLife[arrayNum];
    if (save == SAVE.unitSpeed)
      result = unitSpeed[arrayNum];
    if (save == SAVE.unitRange)
      result = unitRange[arrayNum];
    if (save == SAVE.unitDamage)
      result = unitDamage[arrayNum];
    if (save == SAVE.unitTime)
      result = unitTime[arrayNum];
    if (save == SAVE.skill)
      result = skill[arrayNum];
    if (save == SAVE.trophyUnlock)
      result = trophyUnlock[arrayNum];
    if (save == SAVE.medalUnlock)
      result = medalUnlock[arrayNum];
    if (save == SAVE.wins)
      result = wins[arrayNum];
    if (save == SAVE.stats)
      result = stats[arrayNum];
    if (save == SAVE.money)
      result = money;
    if (save == SAVE.xp)
      result = xp;
    if (save == SAVE.difficulty)
      result = difficulty;
    if (save == SAVE.gameMode)
      result = gameMode;
    if (save == SAVE.country)
      result = country;
    if (save == SAVE.level)
      result = level;
    if (save == SAVE.field)
      result = field;
    if (save == SAVE.gameLife)
      result = gameLife;

    // System.out.print(result);
    return result;
  }

  public void setConstant(SAVE save, int arrayNum, long value)
  {
    if (save == SAVE.unitUnlock)
      unitUnlock[arrayNum] = value;
    if (save == SAVE.unitLife)
      unitLife[arrayNum] = value;
    if (save == SAVE.unitSpeed)
      unitSpeed[arrayNum] = value;
    if (save == SAVE.unitRange)
      unitRange[arrayNum] = value;
    if (save == SAVE.unitDamage)
      unitDamage[arrayNum] = value;
    if (save == SAVE.unitTime)
      unitTime[arrayNum] = value;
    if (save == SAVE.skill)
      skill[arrayNum] = value;
    if (save == SAVE.trophyUnlock)
      trophyUnlock[arrayNum] = value;
    if (save == SAVE.medalUnlock)
      medalUnlock[arrayNum] = value;
    if (save == SAVE.wins)
      wins[arrayNum] = value;
    if (save == SAVE.stats)
      stats[arrayNum] = value;
    if (save == SAVE.money)
      money = value;
    if (save == SAVE.xp)
      xp = value;
    if (save == SAVE.difficulty)
      difficulty = value;
    if (save == SAVE.gameMode)
      gameMode = value;
    if (save == SAVE.country)
      country = value;
    if (save == SAVE.level)
      level = value;
    if (save == SAVE.field)
      field = value;
    if (save == SAVE.gameLife)
      gameLife = value;
  }
  
}
