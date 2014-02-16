package appl.yahtzee;

import java.util.Arrays;
import java.util.List;

public class YahtzeeTurn {

    YahtzeeRoll myRoll;
    int myRollCount;
    
    public YahtzeeTurn() {
        myRoll = new YahtzeeRoll();
        myRollCount = 1;
    }
    
    public void roll() {
        roll(Arrays.asList(new Integer[]{0, 1, 2, 3, 4, 5}));    
    }
    
    public void roll(List<Integer> diceToRoll) {
        assert(myRollCount < 3);
        myRoll.roll(diceToRoll);
        myRollCount++;
    }
}
