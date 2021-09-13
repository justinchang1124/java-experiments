public class SaveManager
{
  private static final String SAVE_NAME = "/Game.sav";
  private static final String FOLDER_NAME = "/BGSaves";
  private static SaveFile gameSave;
  
  public static void saveAllConstants()
  {
    SaveCreator.save(gameSave, FOLDER_NAME, SAVE_NAME);
  }

  public static void loadAllConstants()
  {
    gameSave = SaveCreator.load(FOLDER_NAME, SAVE_NAME);
    
    for (int i = 0; i < Central.UNIT_NUMBER; i++)
    {
      Central.unitUnlock[i] = gameSave.getConstant(SAVE.unitUnlock, i);
      Central.unitLife[i] = gameSave.getConstant(SAVE.unitLife, i);
      Central.unitSpeed[i] = gameSave.getConstant(SAVE.unitSpeed, i);
      Central.unitRange[i] = gameSave.getConstant(SAVE.unitRange, i);
      Central.unitDamage[i] = gameSave.getConstant(SAVE.unitDamage, i);
      Central.unitTime[i] = gameSave.getConstant(SAVE.unitTime, i);
    }
    
    for (int i = 0; i < Central.SKILL_NUMBER; i++)
    {
      Central.skill[i] = gameSave.getConstant(SAVE.skill, i);
    }
    
    for (int i = 0; i < Central.TROPHY_NUMBER; i++)
    {
      Central.trophyUnlock[i] = gameSave.getConstant(SAVE.trophyUnlock, i);
    }
    
    for (int i = 0; i < Central.MEDAL_NUMBER; i++)
    {
      Central.medalUnlock[i] = gameSave.getConstant(SAVE.medalUnlock, i);
    }
    
    for (int i = 0; i < 7; i++)
    {
      Central.wins[i] = gameSave.getConstant(SAVE.wins, i);
    }
    
    for (int i = 0; i < 14; i++)
    {
      Central.stats[i] = gameSave.getConstant(SAVE.stats, i);
    }
    
    Central.money = gameSave.getConstant(SAVE.money, 0);
    Central.xp = gameSave.getConstant(SAVE.xp, 0);
    Central.difficulty = gameSave.getConstant(SAVE.difficulty, 0);
    Central.gameMode = gameSave.getConstant(SAVE.gameMode, 0);
    Central.country = gameSave.getConstant(SAVE.country, 0);
    Central.level = gameSave.getConstant(SAVE.level, 0);
    Central.field = gameSave.getConstant(SAVE.field, 0);
    Central.gameLife = gameSave.getConstant(SAVE.gameLife, 0);
  }
  
  public static void doesThisExist()
  {
    // Creates a file if none exists.
    if (SaveCreator.load(FOLDER_NAME, SAVE_NAME) == null)
    {
      gameSave = new SaveFile();
      SaveCreator.save(gameSave, FOLDER_NAME, SAVE_NAME);
    }
  }

  public void tick()
  {
    for (int i = 0; i < Central.UNIT_NUMBER; i++)
    {
      gameSave.setConstant(SAVE.unitUnlock, i, Central.unitUnlock[i]);
      gameSave.setConstant(SAVE.unitLife, i, Central.unitLife[i]);
      gameSave.setConstant(SAVE.unitSpeed, i, Central.unitSpeed[i]);
      gameSave.setConstant(SAVE.unitRange, i, Central.unitRange[i]);
      gameSave.setConstant(SAVE.unitDamage, i, Central.unitDamage[i]);
      gameSave.setConstant(SAVE.unitTime, i, Central.unitTime[i]);
    }
    
    for (int i = 0; i < Central.SKILL_NUMBER; i++)
    {
      gameSave.setConstant(SAVE.skill, i, Central.skill[i]);
    }
    
    for (int i = 0; i < Central.TROPHY_NUMBER; i++)
    {
      gameSave.setConstant(SAVE.trophyUnlock, i, Central.trophyUnlock[i]);
    }
    
    for (int i = 0; i < Central.MEDAL_NUMBER; i++)
    {
      gameSave.setConstant(SAVE.medalUnlock, i, Central.medalUnlock[i]);
    }
    
    for (int i = 0; i < 7; i++)
    {
      gameSave.setConstant(SAVE.wins, i, Central.wins[i]);
    }
    
    for (int i = 0; i < 14; i++)
    {
      gameSave.setConstant(SAVE.stats, i, Central.stats[i]);
    }
    
    gameSave.setConstant(SAVE.money, 0, Central.money);
    gameSave.setConstant(SAVE.xp, 0, Central.xp);
    gameSave.setConstant(SAVE.difficulty, 0, Central.difficulty);
    gameSave.setConstant(SAVE.gameMode, 0, Central.gameMode);
    gameSave.setConstant(SAVE.country, 0, Central.country);
    gameSave.setConstant(SAVE.level, 0, Central.level);
    gameSave.setConstant(SAVE.field, 0, Central.field);
    gameSave.setConstant(SAVE.gameLife, 0, Central.gameLife);
  }
}
