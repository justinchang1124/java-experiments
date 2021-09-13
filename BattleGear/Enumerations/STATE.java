
public enum STATE
{
  MenuCredit(),
  MenuHelp(),
  MenuMedal(),
  MenuStat(),
  MenuTrophy(),
  MenuOptions(),
  
  // Main menu.
  OpenMain(),
  
  // Preview before any battle.
  Preview(),
  
  // Select difficulty.
  Campaign(),
  Single(),
  
  SetupUnit(),
  SetupSkill(),
  
  // The actual battle.
  Battle();
}
