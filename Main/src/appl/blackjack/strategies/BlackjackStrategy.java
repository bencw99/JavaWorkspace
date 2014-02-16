package appl.blackjack.strategies;

import appl.blackjack.BlackjackHand;

public abstract class BlackjackStrategy {

    public enum Action {
        STAND, HIT, DOUBLE, SPLIT, SURRENDER
    }

    public abstract Action decide(BlackjackHand hand0);    
    
}