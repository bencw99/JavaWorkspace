package appl.blackjack.strategies;

import java.util.Random;

import appl.blackjack.BlackjackHand;

public class BlackjackStrategyMimicRandom extends BlackjackStrategyMimicDealer {
    
    Random myRandom = new Random();

    public BlackjackStrategyMimicRandom() {
        super();
    }

    public BlackjackStrategyMimicRandom(int threshold) {
        super(threshold);
    }

    @Override
    public Action decide(BlackjackHand hand0) {
        // TODO Auto-generated method stub
        if (hand0.computeScore() >= myThreshold) {
            return Action.STAND;
        }
        else if (myRandom.nextInt(10) > 8) {

            return Action.DOUBLE;
        }
        else if (myRandom.nextInt(10) > 8) {
            return Action.SPLIT;
        }
        else {
            return Action.HIT;
        }
    }

}
