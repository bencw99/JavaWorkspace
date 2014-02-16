package lib.cards;

import static org.junit.Assert.*;

import java.util.Random;

import lib.cards.Card.Rank;
import lib.cards.Card.Suit;

import org.junit.Test;

public class DeckTest {

    Random random = new Random();

    public class TestDeck extends Deck<Card> {
        public TestDeck(int index) {
            super(index);
        }

        @Override
        protected Card getCard(int index) {
            return new Card(index);
        }
    }
    
    private void checkOrderedDeck(TestDeck deck, int startSuitIdx, int startRankIdx) {
        int curSuitIdx = startSuitIdx;
        int curRankIdx = startRankIdx;
        int numSuits = Suit.values().length;
        int numRanks = Rank.values().length;
        Card card;
        int numCardsLeft = deck.getNumCards();
        while ((card = deck.deal()) != null) {
            assertEquals(card.getSuit(), Suit.values()[curSuitIdx]);
            assertEquals(card.getRank(), Rank.values()[curRankIdx]);
            curRankIdx = (curRankIdx + 1) % numRanks;
            if (curRankIdx == 0) {
                curSuitIdx = (curSuitIdx + 1) % numSuits;
            }            
            numCardsLeft--;
            assertEquals(numCardsLeft, deck.getNumCardsLeft());
        }
    }
    
    private void checkShuffledDeck(TestDeck deck) {
        int numSuits = Suit.values().length;
        int numRanks = Rank.values().length;
        assertTrue(deck.getNumCards() == deck.getNumRawDecks() * numSuits * numRanks);
        int cardCounts[] = new int[numSuits * numRanks];
        Card card;
        int numCardsLeft = deck.getNumCards();
        while ((card = deck.deal()) != null) {
            cardCounts[card.getSuit().ordinal() * numRanks + card.getRank().ordinal()]++;
            numCardsLeft--;
            assertEquals(numCardsLeft, deck.getNumCardsLeft());
        }
        for (int i : cardCounts) {
            assertEquals(i, deck.getNumRawDecks());
        }
    }
    
    @Test
    public void test() {
        TestDeck deck = new TestDeck(1);
        checkOrderedDeck(deck, 0, 0);
        deck = new TestDeck(2);
        checkOrderedDeck(deck, 0, 0);
        deck = new TestDeck(10);
        for (int i = 0; i < deck.getNumCards() + 1; i++) {
            deck.cut(i);
            checkOrderedDeck(deck, i / Rank.values().length, i % Rank.values().length);
        }
        for (int i = 0; i < 10000; i++) {
            deck = new TestDeck(10);
            deck.shuffle();
            checkShuffledDeck(deck);
        }
        for (int i = 0; i < 10000; i++) {
            deck = new TestDeck(1);
            for (int j = 0; j < deck.getNumCards(); j++) {
                deck.shuffle(j);
                checkShuffledDeck(deck);
                deck.shuffle(random.nextInt(deck.getNumCards()));
                checkShuffledDeck(deck);
                deck.reset();
                deck.cut(random.nextInt(deck.getNumCards()));
                checkShuffledDeck(deck);
            }
        }
        
    }

}
