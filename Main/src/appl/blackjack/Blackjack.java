package appl.blackjack;

import appl.blackjack.strategies.BlackjackStrategy;
import appl.blackjack.strategies.BlackjackStrategyMimicDealer;
import appl.blackjack.strategies.BlackjackStrategyMimicRandom;

public class Blackjack {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BlackjackDeck deck = new BlackjackDeck(10);
        deck.shuffle();
        
        BlackjackStrategy strategy0 = new BlackjackStrategyMimicDealer(17);
        BlackjackStrategy strategy1 = new BlackjackStrategyMimicRandom(17);

        int numWin0 = 0;
        int numWin1 = 0;
        double dollars0 = 0.0;
        double dollars1 = 0.0;
        int numGames = 0;
        int numBust1 = 0;
        for (int i = 0; i < 10000000; i++) {
            if (deck.getNumCardsLeft() <= 52) {
                deck.shuffle();
            }

            BlackjackHand hand0 = new BlackjackHand();
            hand0.hit(deck.deal());
            hand0.hit(deck.deal());
            BlackjackStrategy.Action action;
            action = strategy0.decide(hand0);
            while (action != BlackjackStrategy.Action.STAND) { 
                if (action == BlackjackStrategy.Action.DOUBLE) {
                    hand0.doubleDown(deck.deal());
                }
                else if (action == BlackjackStrategy.Action.SPLIT) {
                    hand0.split();
                }
                else {
                    assert(action == BlackjackStrategy.Action.HIT); 
                    hand0.hit(new BlackjackCard(deck.deal()));
                }
                action = strategy0.decide(hand0);
            }

            BlackjackHand hand1 = new BlackjackHand();
            hand1.hit(deck.deal());
            hand1.hit(deck.deal());
            
            action = strategy1.decide(hand1);
            while (action != BlackjackStrategy.Action.STAND) {
                if (action == BlackjackStrategy.Action.DOUBLE) {
                    hand1.doubleDown(deck.deal());
                }
                else if (action == BlackjackStrategy.Action.SPLIT) {
                    hand1.split();
                }
                else {
                    assert(action == BlackjackStrategy.Action.HIT); 
                    hand1.hit(new BlackjackCard(deck.deal()));
                }
                action = strategy1.decide(hand0);
            }
            int score0 = hand0.computeScore();
            int score1 = hand1.computeScore();
            
            numGames++;
            double value = hand1.valueAgainstDealer(hand0);
            double value1 = hand0.valueAgainstPlayer(hand1);
            assert (value == -value1);
            assert (Math.abs(value + value1) < 0.0000000000000001);
            
            if (value < 0) {
                numWin0++;
                dollars0 += -value;
            }
            else if (value > 0) {
                numWin1++;
                dollars1 += value;
            }

            if (score1 > 21) {
                numBust1++;
            }

        }
        System.out.format("%d %.2f %d %.2f %.2f %d %d\n", numWin0, dollars0, numWin1, dollars1, dollars1 - dollars0, 
                                                          numGames, numBust1);
    }
}
