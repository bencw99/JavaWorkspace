package lib.cards;

import java.util.ArrayList;
import java.util.Random;


abstract public class Deck<T extends Card> {

    private int myNumRawDecks;
	private ArrayList<T> myCards; 
	private int myCurIndex;
    private Random myRandom;

	/**
	 * 
	 * @param numRawDecks Number of basic, "raw" decks to be used in this deck, where a raw deck is the set with one of each type
     * of card being used.
	 */
    public Deck(int numRawDecks) {
	    myNumRawDecks = numRawDecks;
        myCards = new ArrayList<T>();
        // new T[Card.NUM_CARDS * numDecks];
		for (int i = 0; i < numRawDecks; i++) {
			for (int j = 0; j < Card.NUM_CARDS; j++) {
				myCards.add(getCard(j));
			}
		}
        assert (myCards.size() == T.NUM_CARDS * numRawDecks); 
		myCurIndex = 0;
	    myRandom = new Random();
	}
	
    /**
     * Creates a deck with a given random seed.  This constructor can be used when one wants a repeatable sequence of cards.
     * 
     * @param numRawDecks Number of basic, "raw" decks to be used in this deck, where a raw deck is the set with one of each type
     * of card being used. 
     * @param randomSeed Seed to be used in the random number generator.
     */
	public Deck(int numRawDecks, long randomSeed) {
        this(numRawDecks);
	    myRandom.setSeed(randomSeed);
	}
	
	abstract protected T getCard(int index);
	
    /**
     * Swaps card at position i with card at position j
     * 
     * @param i integer 0 <= i < cards.length
     * @param j integer 0 <= j < cards.length
     */
    private void swap(int i, int j) {
        T tempCard = myCards.get(i);
        myCards.set(i, myCards.get(j));
        myCards.set(j, tempCard);
    }
    
    /**
     * Shuffle the entire deck.
     */
    public void shuffle() {
        shuffle(myCards.size() - 1);
	}

    /**
     * Shuffle the deck (including dealt cards) from the top of the deck to end_idx, moving the remainder of the deck (the part
     * after end_idx) to the top.  This is intended to be used when the deck is partially dealt, and one wants to shuffle and 
     * re-used the part of the deck that has already been dealt.
     * 
     * @param end_idx last card to be shuffled
     */
	public void shuffle(int end_idx) {
        assert (end_idx >= 0 && end_idx < myCards.size());
	    
	    for (int i = 0; i <= end_idx; i++) {
	        swap(i, i + myRandom.nextInt(end_idx + 1 - i));
	    }
	    
	    cut(end_idx + 1);
	    myCurIndex = 0;
	}

	/**
	 * Cuts the deck, moving the subdeck beginning at card idx up to the top of the deck (and moving the subdeck from card 0
	 * to card idx - 1 to the bottom of the deck.
	 * 
	 * @param idx index of card to be moved to the top of the deck.
	 */
	public void cut(int idx) {
        assert (idx >= 0 && idx <= myCards.size());
        
        ArrayList<T> tempCards = new ArrayList<T>();
        for (int i = 0; i < myCards.size(); i++) {
            tempCards.add(myCards.get((idx + i) % myCards.size()));
        }
        
        myCards = tempCards;
	}

	/**
	 * Reset the deck to start dealing from the top of the deck (without shuffling), thus putting all dealt cards back in the
	 * deck.
	 */
	public void reset() {
	    myCurIndex = 0;
	}
	
	/**
	 * Deal the next card from the top of the remaining (undealt) deck,
	 * 
	 * @return the top card from the remaining (undealt part of the) deck (as of the time of the call).
	 */
	public T deal() {
        T card;
        if (myCurIndex < myCards.size()) {
			card = myCards.get(myCurIndex);
			myCurIndex += 1;
		}
		else {
			card = null;
		}
        return card;
	}
	
	/**
	 * 
	 * @return Number of raw decks in this deck.
	 */
	public int getNumRawDecks() {
        return myNumRawDecks;
    }

	/**
	 * 
	 * @return Number of cards in the overall deck (including both dealt and undealt cards).
	 */
	public int getNumCards() {
		return myCards.size();
	}
	
	/**
	 * 
	 * @return Number of undealt cards remaining in the deck.
	 */
	public int getNumCardsLeft() {
		return myCards.size() - myCurIndex;
	}
	

}
