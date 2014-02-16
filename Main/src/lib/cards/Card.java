package lib.cards;

public class Card {

	public static enum Suit {
	    SPADES, HEARTS, DIAMONDS, CLUBS	
	}

	public static enum Rank {
		TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,
		JACK, QUEEN, KING, ACE
	}
		
    public static final int NUM_CARDS = Suit.values().length * Rank.values().length;
    
    private Suit suit;
    private Rank rank;
	
	public Card(int index) {
		int num_ranks = Rank.values().length; 
		this.suit = Suit.values()[index / num_ranks];
		this.rank = Rank.values()[index % num_ranks];
	}
	
	public Card(Suit suit, Rank rank) {
		this.suit = suit;
		this.rank = rank;
	}

	public static final Card makeInstance(int index) {
	    return new Card(index);
	}
	
	public final Suit getSuit() {
		return suit;
	}

	public final Rank getRank() {
		return rank;
	}
	
	public final boolean isBlack() {
		return ((suit == Suit.SPADES) || (suit == Suit.CLUBS));
	}

	public final boolean isRed() {
		return ((suit == Suit.HEARTS) || (suit == Suit.DIAMONDS));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Card card = new Card(Suit.SPADES, Rank.TWO);
		System.out.println("Printing card");
		System.out.format("%s of %s", card.rank, card.suit);
		System.out.format("%s, %s", Rank.TWO, Rank.values()[2]);
	}
}
