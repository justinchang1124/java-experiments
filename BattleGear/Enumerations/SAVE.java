
public enum SAVE
{
  // This is if the unit has been unlocked.
  // 0 = locked, 1 = unlocked.
  unitUnlock(),
  // This counts the number of life upgrades.
  unitLife(),
  // This counts the number of speed upgrades.
  unitSpeed(),
  // This counts the number of range upgrades.
  unitRange(),
  // This counts the number of damage upgrades.
  unitDamage(),
  // This counts the number of training time upgrades.
  unitTime(),
  // This counts the number of upgrades to each skill.
  skill(),

  // This counts which achievements are unlocked and their degree.
  trophyUnlock(),
  // This counts which medals are unlocked.
  // 0 = locked, 1 = unlocked.
  medalUnlock(),
  // This is the number of wins in each game mode that the player has.
  // USA, Russia, China, Single Easy, Single Normal, Single Hard, Single Impossible.
  wins(),
  // This is the statistics of the game.
  stats(),

  // This is the amount of money the player has.
  money(),
  // This is the amount of experience the player has.
  xp(),
  // This is the game's current difficulty.
  // 0 = easy, 1 = normal, 2 = hard, 3 = impossible.
  difficulty(),
  // This is the game mode.
  // 0 = Unlimited, 1 = Limited, 2 = Single Mode.
  gameMode(),
  // This is the player's chosen country.
  // 0 = USA, 1 = Russia, 2 = China.
  country(),
  // This is the player's current campaign level.
  level(),
  // This is the stage of the current battle.
  field(),
  // This is the amount of game life remaining.
  gameLife();
}
