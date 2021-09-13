/**
 * Creates an immutable card that acts as one of the 52 cards in a standard deck.
 * 
 * @author Justin C
 */
public class Card
{
  public static final char[] unicodeCards = {'♥', '♦', '♠', '♣'};

  public static final char[] charCards = {'H', 'D', 'S', 'C'};

  private boolean showUnicode;

  private int rank;

  private int suit;

  /**
   * Generates a card that has a random rank & suit and shows Unicode by default.
   */
  public Card()
  {
    this((int) ( 13 * Math.random() + 2 ), (int) ( 4 * Math.random() ), true);
  }

  /**
   * Generates a card that has a random rank & suit.
   * @param showUnicode
   * Decides if the card will show Unicode.
   */
  public Card(boolean showUnicode)
  {
    this((int) ( 13 * Math.random() + 2 ), (int) ( 4 * Math.random() ), showUnicode);
  }

  /**
   * Generates a card that shows Unicode by default.
   * @param rank
   * Rank of the card to be created.
   * @param suit
   * Suit of the card to be created.
   */
  public Card(int rank, int suit)
  {
    this(rank, suit, true);
  }

  /**
   * Generates a card.
   * @param rank
   * Rank of the card to be created.
   * @param suit
   * Suit of the card to be created.
   * @param showUnicode
   * Decides whether the card will show Unicode.
   */
  public Card(int rank, int suit, boolean showUnicode)
  {
    this.rank = rank;
    this.suit = suit;
    this.showUnicode = showUnicode;
  }

  /**
   * Generates a card by copying another card.
   * @param other
   * Card to be copied.
   */
  public Card(Card other)
  {
    this(other.rank, other.suit, other.showUnicode);
  }

  /**
   * Gets the rank of a card.
   * @return
   * The rank of the card.
   */
  public String getRank()
  {
    return "" + rank;
  }

  /**
   * Gets the suit of a card.
   * @return
   * The suit of the card.
   */
  public char getSuit()
  {
    if (showUnicode)
      return unicodeCards[suit];
    return charCards[suit];
  }

  /**
   * Returns a String representation of the card.
   */
  public String toString()
  {
    String target = "";

    if (rank < 10)
      target += "0";

    return target + rank + " Of " + getSuit();
  }
}
