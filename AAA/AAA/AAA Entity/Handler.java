import java.awt.Graphics;
import java.util.LinkedList;

/**
 * Handler has methods to tick and render every object in the LinkedList gameObject.
 * It also has methods to add various entities to the game or remove them from the game.
 * 
 * @author Justin C
 */
public class Handler
{
  LinkedList<GameObject> gameObject = new LinkedList<GameObject>();

  /**
   * Ticks every object in the linked list.
   */
  public void tick()
  {
    for (int i = 0; i < gameObject.size(); i++)
    {
      GameObject tempGameObject = gameObject.get(i);
      tempGameObject.tick();
    }
  }

  /**
   * Renders every object in the linked list to g.
   * 
   * @param g
   */
  public void render(Graphics g)
  {
    for (int i = 0; i < gameObject.size(); i++)
    {
      GameObject tempGameObject = gameObject.get(i);
      tempGameObject.render(g);
    }
  }

  /**
   * Adds the param gameObject to the field gameObject. 
   * 
   * @param gameObject
   */
  public void gameAddition(GameObject gameObject)
  {
    this.gameObject.add(gameObject);
  }

  /**
   * Removes the param gameObject from the field gameObject. 
   * 
   * @param gameObject
   */
  public void gameRemoval(GameObject gameObject)
  {
    this.gameObject.remove(gameObject);
  }

  /**
   * Clears all entities with the IDs given in the array id.
   * 
   * @param id
   */
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

  /**
   * Clears all entities with the given ID id.
   * 
   * @param id
   */
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

  /**
   * Clears all entities. 
   */
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
