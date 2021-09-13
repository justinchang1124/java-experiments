
/**
 * @author Justin Chang
 * 
 *         Ticks and renders every object in the linked list when said methods are called.
 *         Also has methods to add, remove, and clear various entities in the game.
 * 
 */

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler
{
  // Creates a linked list with all game objects in it.
  LinkedList<GameObject> gameObject = new LinkedList<GameObject>();

  // Updates the properties of all objects every tick.
  public void tick()
  {
    for (int i = 0; i < gameObject.size(); i++)
    {
      GameObject tempGameObject = gameObject.get(i);
      tempGameObject.tick();
    }
  }

  // Renders all objects.
  public void render(Graphics g)
  {
    for (int i = 0; i < gameObject.size(); i++)
    {
      GameObject tempGameObject = gameObject.get(i);
      tempGameObject.render(g);
    }
  }

  // Add and remove objects.
  public void gameAddition(GameObject gameObject)
  {
    this.gameObject.add(gameObject);
  }

  public void gameRemoval(GameObject gameObject)
  {
    this.gameObject.remove(gameObject);
  }

  // Special functions that clear all non-excluded characters.
  public void gameClear(ID[] id)
  {
    boolean included = false;

    for (int i = 0; i < gameObject.size(); i++)
    {
      GameObject tempGameObject = gameObject.get(i);

      for (int j = 0; j < id.length; j++)
      {
        if (tempGameObject.getID() == id[j])
        {
          included = true;
        }
      }

      if (!included)
      {
        this.gameRemoval(tempGameObject);
        i--;
      }

      included = false;
    }
  }

  public void gameClear(ID id)
  {
    for (int i = 0; i < gameObject.size(); i++)
    {
      GameObject tempGameObject = gameObject.get(i);
      if (tempGameObject.getID() != id)
      {
        this.gameRemoval(tempGameObject);
        i--;
      }
    }
  }

  public void gameClear()
  {
    for (int i = 0; i < gameObject.size(); i++)
    {
      GameObject tempGameObject = gameObject.get(i);
      this.gameRemoval(tempGameObject);
      i--;
    }
  }
}
