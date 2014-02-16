package appl.blackjack.strategies;

import appl.blackjack.BlackjackCard;
import appl.blackjack.BlackjackHand;
import appl.blackjack.strategies.BlackjackStrategy.Action;

public class BlackjackStrategyWizardOfOddsWizard extends BlackjackStrategy {

    // Implements the Wizard Strategy from wizardofodds.com
    
    @Override
    public Action decide(BlackjackHand hand, BlackjackCard dealerUpCard) {
        if ((dealerUpCard.getValue() >= 2) && (dealerUpCard.getValue() <= 6)) {
            if (hand.canSplit()) {
                if ((hand.getCards().get(0).getValue() == 2) || 
                        (hand.getCards().get(0).getValue() == 3) || 
                        (hand.getCards().get(0).getValue() == 6) || 
                        (hand.getCards().get(0).getValue() == 7) || 
                        (hand.getCards().get(0).getValue() == 9) || 
                        (hand.getCards().get(0).getValue() == 8) || 
                        (hand.getCards().get(0).getValue() == 11)) {
                    return Action.SPLIT;
                }
            }
            if (hand.hasSoftAce()) {
                if (hand.computeScore() <= 15) {
                    return Action.HIT;
                }
                else if (hand.computeScore() <= 18) {
                    return Action.DOUBLE;
                }
                else {
                    return Action.STAND;
                }
            }
            else {
                if (hand.computeScore() <= 8) {
                    return Action.HIT;
                }
                else if (hand.computeScore() <= 9) {
                    return Action.DOUBLE;
                }
                else if (hand.computeScore() <= 11) {
                    if (hand.computeScore() > dealerUpCard.getValue()) {
                        return Action.DOUBLE;
                    }
                    else {
                        return Action.HIT;
                    }
                }
                else {
                    return Action.STAND;
                }

            }
        }
        else {
            assert((dealerUpCard.getValue() >= 7) && (dealerUpCard.getValue() <= 11));
            if (hand.canSplit()) {
                if ((hand.getCards().get(0).getValue() == 2) || 
                    (hand.getCards().get(0).getValue() == 3) || 
                    (hand.getCards().get(0).getValue() == 6) || 
                    (hand.getCards().get(0).getValue() == 7) || 
                    (hand.getCards().get(0).getValue() == 9)) {
                    return Action.SPLIT;
                }
            }            
            if (hand.hasSoftAce()) {
                if (hand.computeScore() <= 18) {
                    return Action.HIT;
                }
                else {
                    return Action.STAND;
                }
            }
            else {
                if (hand.computeScore() <= 9) {
                    return Action.HIT;
                }
                else if (hand.computeScore() <= 11) {
                    if (hand.computeScore() > dealerUpCard.getValue()) {
                        return Action.DOUBLE;
                    }
                    else {
                        return Action.HIT;
                    }
                }
                else if (hand.computeScore() <= 16) {
                    return Action.HIT;
                }
                else {
                    return Action.STAND;
                }
            }
        }
    }
}
