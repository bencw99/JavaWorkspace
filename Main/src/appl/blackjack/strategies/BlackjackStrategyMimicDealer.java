package appl.blackjack.strategies;

import appl.blackjack.BlackjackHand;

public class BlackjackStrategyMimicDealer extends BlackjackStrategy {

    int myThreshold;
    
    public BlackjackStrategyMimicDealer() {
        this(17);
    }

    public BlackjackStrategyMimicDealer(int threshold) {
        myThreshold = threshold;
    }

    
    @Override
    public Action decide(BlackjackHand hand0) {
        if (hand0.computeScore() >= myThreshold) {
            return Action.STAND;
        }
        else {
            return Action.HIT;
        }
    }
}
