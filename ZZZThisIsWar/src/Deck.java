/**
 * Creates a mutable deck of cards.
 * 
 * @author Justin C
 */
public class Deck
{
  /**
   * The array of cards represented in a deck.
   */
  public Card[] ordering = new Card[52];

  /**
   * Constructs a deck that has the standard order of 52 cards and shows Unicode by default.
   */
  public Deck()
  {
    for (int i = 0; i < 13; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        ordering[4 * i + j] = new Card(i + 2, j, true);
      }
    }
  }

  /**
   * Constructs a deck that has the standard order of 52 cards.
   * 
   * @param showUnicode
   * Whether the deck shows Unicode.
   */
  public Deck(boolean showUnicode)
  {
    for (int i = 0; i < 13; i++)
    {
      for (int j = 0; j < 4; j++)
      {
        ordering[4 * i + j] = new Card(i + 2, j, showUnicode);
      }
    }
  }

  /**
   * Constructs a deck by copying another deck.
   * 
   * @param other
   * Deck to be copied.
   */
  public Deck(Deck other)
  {
    ordering = new Card[other.ordering.length];

    for (int i = 0; i < other.ordering.length; i++)
    {
      this.ordering[i] = other.ordering[i];
    }
  }

  /**
   * Returns a String representation of the deck.
   */
  public String toString()
  {
    String target = "";

    for (int i = 0; i < ordering.length; i++)
    {
      target += ordering[i];

      if (i < ordering.length - 1)
      {
        target += ", ";
      }

      if (i % 4 == 3)
      {
        target += "\n";
      }
    }

    return target;
  }

  /**
   * Shuffles the deck.
   * 
   * @param switches
   * The number of switches that take place between 2 selected cards.
   */
  public void shuffle(int switches)
  {
    for (int i = 0; i < switches; i++)
    {
      int card1 = (int) ( ordering.length * Math.random() );
      int card2 = (int) ( ordering.length * Math.random() );

      while (card1 == card2)
      {
        card2 = (int) ( ordering.length * Math.random() );
      }

      exchange(card1, card2);
    }
  }

  /**
   * Exchanges 2 given cards in a deck.
   * 
   * @param a
   * The first card.
   * @param b
   * The second card.
   */
  public void exchange(int a, int b)
  {
    if (a < 0 || b < 0 || a > ordering.length - 1 || b > ordering.length - 1)
    {
      System.err.println("Index out of bounds for exchange");
      return;
    }

    Card temp = new Card(ordering[a]);
    ordering[a] = new Card(ordering[b]);
    ordering[b] = new Card(temp);
  }

  /**
   * Reverses the order of the deck.
   */
  public void reverse()
  {
    Card[] reversed = new Card[ordering.length];

    for (int i = 0; i < ordering.length; i++)
    {
      reversed[ordering.length - 1 - i] = ordering[i];
    }

    for (int i = 0; i < ordering.length; i++)
    {
      ordering[i] = reversed[i];
    }
  }

  /**
   * Draws a card from the back of the deck.
   * 
   * @return
   * The drawn card.
   */
  public Card drawCardBack()
  {
    int oldLength = ordering.length;
    Card drawnCard = new Card(ordering[oldLength - 1]);
    Card[] restOfDeck = new Card[oldLength - 1];

    // Stores the first oldLength-1 cards.
    for (int i = 0; i < restOfDeck.length; i++)
    {
      restOfDeck[i] = ordering[i];
    }

    ordering = new Card[oldLength - 1];

    // Replaces the cards into a newly sized array "ordering".
    for (int i = 0; i < restOfDeck.length; i++)
    {
      ordering[i] = restOfDeck[i];
    }

    return drawnCard;
  }

  /**
   * Draws a card from the front of the deck.
   * 
   * @return
   * The drawn card.
   */
  public Card drawCardFront()
  {
    int oldLength = ordering.length;
    Card drawnCard = new Card(ordering[0]);
    Card[] restOfDeck = new Card[oldLength - 1];

    // Stores the last oldLength-1 cards.
    for (int i = 0; i < restOfDeck.length; i++)
    {
      restOfDeck[i] = ordering[i + 1];
    }

    ordering = new Card[oldLength - 1];

    // Replaces the cards into a newly sized array "ordering".
    for (int i = 0; i < restOfDeck.length; i++)
    {
      ordering[i] = restOfDeck[i];
    }

    return drawnCard;
  }

  /**
   * Adds a card to the back of the deck.
   * 
   * @param other
   * The card to be added.
   */
  public void addCardBack(Card other)
  {
    int oldLength = ordering.length;
    Card[] newDeck = new Card[oldLength + 1];

    // Stores the first oldLength cards.
    for (int i = 0; i < oldLength; i++)
    {
      newDeck[i] = ordering[i];
    }

    newDeck[oldLength] = new Card(other);

    ordering = new Card[oldLength + 1];

    // Replaces the cards into a newly sized array "ordering".
    for (int i = 0; i < newDeck.length; i++)
    {
      ordering[i] = newDeck[i];
    }
  }

  /**
   * Adds a card to the front of the deck.
   * 
   * @param other
   * The card to be added.
   */
  public void addCardFront(Card other)
  {
    int oldLength = ordering.length;
    Card[] newDeck = new Card[oldLength + 1];

    // Stores the first oldLength cards.
    for (int i = 0; i < oldLength; i++)
    {
      newDeck[i + 1] = ordering[i];
    }

    newDeck[0] = new Card(other);

    ordering = new Card[oldLength + 1];

    // Replaces the cards into a newly sized array "ordering".
    for (int i = 0; i < newDeck.length; i++)
    {
      ordering[i] = newDeck[i];
    }
  }

  /**
   * Removes all cards from the deck.
   */
  public void empty()
  {
    int x = ordering.length;
    for (int i = 0; i < x; i++)
    {
      drawCardBack();
    }
  }
}