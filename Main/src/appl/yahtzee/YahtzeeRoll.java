package appl.yahtzee;

import java.util.Arrays;
import java.util.List;
import lib.dice.Die;

public class YahtzeeRoll {
    
    private final Die[] myDice;
    
    
    public YahtzeeRoll() {
        myDice = new Die[5];
        for (int i = 0; i < 5; i++) {
            myDice[i] = new Die();
        }
    }

    public YahtzeeRoll(List<Integer> diceValues) {
        assert(diceValues.size() == 5);
        myDice = new Die[5];
        for (int i = 0; i < 5; i++) {
            myDice[i] = new Die(6, diceValues.get(i));
        }
    }
    
    public void roll() {
        roll(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5}));    
    }
    
    public void roll(List<Integer> diceToRoll) {
        for (int i : diceToRoll) {
            myDice[i].roll();
        }
    }
    
    public int getValue(int i) {
        return myDice[i].getValue();
    }

    public boolean isYahtzee() {
        boolean result = true;
        for (int i = 0; i < 4; i++) {
            if (myDice[i].getValue() != myDice[i+1].getValue()) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    /**
     * 
     * @return A histogram where element i gives the number of dice showing (i+1).
     */
    public int[] computeHistogram() {
        int[] histogram = new int[6];
        for (Die die : myDice) {
            histogram[die.getValue()-1]++;
        }
        return histogram;
    }

    public int computeSumOfDice() {
        int sum = 0;
        for (Die die : myDice) {
            sum += die.getValue();
        }
        return sum;
    }

    private boolean hasNOfAKind(int n) {
        int[] histogram = computeHistogram();
        for (int i = 0; i < 6; i++) {
            if (histogram[i] >= n) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isFourOfAKind() {
        return hasNOfAKind(4);
    }

    public boolean isThreeOfAKind() {
        return hasNOfAKind(3);
    }
    
    public boolean isFullHouse() {
        int[] histogram = computeHistogram();
        boolean hasTwo = false;
        boolean hasThree = false;
        for (int i = 0; i < 6; i++) {
            if (histogram[i] == 2) {
                hasTwo = true;
            }
            else if (histogram[i] == 3) {
                hasThree = true;
            }
        }
        return (hasTwo && hasThree);        
    }

    private int lengthOfLongestRun() {
        int[] histogram = computeHistogram();
        int maxLenRun = 0;
        int lenRun = 0;
        for (int i = 0; i < 6; i++) {
            if (histogram[i] > 0) {
                lenRun++;
                if (lenRun > maxLenRun) {
                    maxLenRun = lenRun;
                }
            }
        }
        return maxLenRun;        
    }
    
    public boolean isSmallStraight() {
        return lengthOfLongestRun() >= 4;
    }

    public boolean isLargeStraight() {
        return lengthOfLongestRun() >= 5;
    }
    
    public String toString() {
        String str = Integer.toString(myDice[0].getValue());
        for (int i = 1; i < 5; i++) {
            str += " " + myDice[i].getValue();
        }
        return str;
    }
}
