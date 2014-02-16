package appl.blackjack;

import static org.junit.Assert.*;
import lib.cards.Card;

import org.junit.Test;

public class BlackjackHandTest {

    @Test
    public void test() {
        BlackjackHand hand;
        
        hand = new BlackjackHand();
        hand.hit(new BlackjackCard(15));
        hand.hit(new BlackjackCard(30));
        assertEquals(hand.computeScore(), 3 + 5);
        assertFalse(hand.canSplit());
        assertEquals(hand.getNumCards(), 2);
        
        hand.hit(new BlackjackCard(13));
        assertTrue(hand.hasSoftAce());
        assertEquals(hand.computeScore(), 19);
        assertFalse(hand.canSplit());
        assertEquals(hand.getNumCards(), 3);

        hand.hit(new BlackjackCard(40));
        assertTrue(hand.hasSoftAce());
        assertEquals(hand.computeScore(), 21);
        assertTrue(!hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 1.0);
        assertFalse(hand.canSplit());
        assertEquals(hand.getNumCards(), 4);
        
        hand.doubleDown(new BlackjackCard(51));
        assertFalse(hand.hasSoftAce());
        assertEquals(hand.computeScore(), 21);
        assertTrue(!hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 2.0);
        assertFalse(hand.canSplit());
        assertEquals(hand.getNumCards(), 5);

        hand = new BlackjackHand();
        hand.hit(new BlackjackCard(22));
        hand.hit(new BlackjackCard(27));
        assertEquals(hand.computeScore(), 12);
        assertFalse(hand.canSplit());
        assertTrue(!hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 1.0);
        assertEquals(hand.getNumCards(), 2);

        hand = new BlackjackHand();
        hand.hit(new BlackjackCard(0));
        hand.hit(new BlackjackCard(25));
        assertEquals(hand.computeScore(), 21);
        assertFalse(hand.canSplit());
        assertTrue(hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 1.0);
        assertEquals(hand.getNumCards(), 2);

        hand.hit(new BlackjackCard(42));
        assertFalse(hand.hasSoftAce());
        assertEquals(hand.computeScore(), 15);
        assertFalse(hand.canSplit());
        assertTrue(!hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 1.0);
        
        hand = new BlackjackHand();
        hand.hit(new BlackjackCard(9));
        hand.hit(new BlackjackCard(23));
        assertEquals(hand.computeScore(), 20);
        assertTrue(hand.canSplit());
        assertTrue(!hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 1.0);
        assertEquals(hand.getNumCards(), 2);
        
        BlackjackHand[] hands = hand.split(new BlackjackCard(51), new BlackjackCard(39));
        hand = hands[0];
        assertEquals(hand.computeScore(), 20);
        assertTrue(hand.canSplit());
        assertTrue(!hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 1.0);
        assertEquals(hand.getNumCards(), 2);
        hand = hands[1];
        assertEquals(hand.computeScore(), 21);
        assertFalse(hand.canSplit());
        assertTrue(hand.isBlackjack());
        assertTrue(!hand.isBust());
        assertTrue(hand.getBet() == 1.0);
        assertEquals(hand.getNumCards(), 2);
    }
}
