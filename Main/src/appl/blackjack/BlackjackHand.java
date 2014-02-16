package appl.blackjack;

import java.util.ArrayList;
import java.util.List;

import lib.cards.Card;
import lib.cards.Card.Rank;

public class BlackjackHand {

    private final List<BlackjackCard>      myCards = new ArrayList<BlackjackCard>();
    private final int                      mySplitLevel;
    private       boolean                  myComputed;
    private       int                      myScore;
    private       int                      myNumSoftAces;
    private       double                   myBet;
    private       boolean                  myDoubledDown;
    private       boolean                  myIsLeafHand;
    private       ArrayList<BlackjackHand> mySubHands;
    
    public BlackjackHand() {
        this(1.0, 0, true);
    }
    
    public BlackjackHand(double bet, int splitLevel, boolean isLeafHand) {
        myBet = bet;
        mySplitLevel = splitLevel;
        myDoubledDown = false;
        myIsLeafHand = isLeafHand;
        myComputed = false;
    }
    
    public int computeScore() {
        assert (getNumCards() >= 2);
        int value = 0;
        int numAces = 0;
        for (BlackjackCard card : myCards) {
            value += card.getValue();
            if (card.getRank() == Card.Rank.ACE) {
                numAces++;
            }
        }
        while ((value > 21) && (numAces > 0)) {
            value -= 10;
            numAces--;
        }
        myScore = value;
        
        return myScore;
    }

    private void addCard(BlackjackCard card) {
        myCards.add(card);
        myScore += card.getValue();
        if (card.getRank() == Rank.ACE) {
            myNumSoftAces++;
        }
        while ((myScore > 21) && (myNumSoftAces > 0)) {
                myScore -= 10;
                myNumSoftAces--;
        }
        myComputed = true;
    }

    
    public void hit(BlackjackCard card) {
        assert(myIsLeafHand);
        assert(!myDoubledDown);
        addCard(card);
    }
    
    public void doubleDown(BlackjackCard card) {
        assert(myIsLeafHand);
        myBet *= 2.0;
        myDoubledDown = true;
        addCard(card);
    }
    
    public BlackjackHand[] split() {
        assert (getNumCards() == 2);
        assert(myIsLeafHand);
        BlackjackHand[] hands = new BlackjackHand[2];
        hands[0] = new BlackjackHand();
        hands[1] = new BlackjackHand();
        // TODO: Finish implementation
        return hands;
    }
    
    public double valueAgainstDealer(BlackjackHand dealerHand) {
        // TODO: Modify for special situations like blackjack
        double value = 0.0;
        double score1 = computeScore();
        double score0 = dealerHand.computeScore();
        
        if ((score1 > 21) || ((score0 <= 21) && (score0 > score1))) {
            value = -1.0;
        }
        else if ((score1 <= 21) && ((score0 > 21) || (score1 > score0))) {
            if (isBlackjack()) {
                value = 1.5;
            }
            else {
                value = 1.0;
            }
        }
        else if (isBlackjack() && (!(dealerHand.isBlackjack()))) {
            value = 1.5;
        }
        else if (!(isBlackjack()) && (dealerHand.isBlackjack())) {
            value = -1.0;
        }
        return value * myBet;
    }

    public double valueAgainstPlayer(BlackjackHand playerHand) {
        return -playerHand.valueAgainstDealer(this);
    }
    
    public boolean isBlackjack() {
        return ((computeScore() == 21) && (getNumCards() == 2));
    }
    public List<BlackjackCard> getCards() {
        // TODO: Change to prevent modification of cards
        return myCards;
    }

    public int getMySplitLevel() {
        return mySplitLevel;
    }
    
    public int getNumCards() {
        return myCards.size();
    }
}
