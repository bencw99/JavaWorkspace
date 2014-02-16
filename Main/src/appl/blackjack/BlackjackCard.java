/**
 * 
 */
package appl.blackjack;

import java.util.HashMap;

import lib.cards.Card;

/**
 * @author axwang
 *
 */
public class BlackjackCard extends Card {

    final int myValue;
    
    static final HashMap<Card.Rank, Integer> ourValueMap = new HashMap<Card.Rank, Integer>();
    
    static {
        ourValueMap.put(Card.Rank.TWO,    2);
        ourValueMap.put(Card.Rank.THREE,  3);
        ourValueMap.put(Card.Rank.FOUR,   4);
        ourValueMap.put(Card.Rank.FIVE,   5);
        ourValueMap.put(Card.Rank.SIX,    6);
        ourValueMap.put(Card.Rank.SEVEN,  7);
        ourValueMap.put(Card.Rank.EIGHT,  8);
        ourValueMap.put(Card.Rank.NINE,   9);
        ourValueMap.put(Card.Rank.TEN,   10);
        ourValueMap.put(Card.Rank.JACK,  10);
        ourValueMap.put(Card.Rank.QUEEN, 10);
        ourValueMap.put(Card.Rank.KING,  10);
        ourValueMap.put(Card.Rank.ACE,   11);
    }
    
    
    
    /**
     * @param index
     */
    public BlackjackCard(int index) {
        super(index);
        myValue = ourValueMap.get(this.getRank());

        // TODO Auto-generated constructor stub
    }

    /**
     * @param suit
     * @param rank
     */
    public BlackjackCard(Suit suit, Rank rank) {
        super(suit, rank);
        myValue = ourValueMap.get(rank);
    }
    
    public BlackjackCard(Card card) {
        this(card.getSuit(), card.getRank());
    }

    public int getValue() {
        return myValue;
    }
}
