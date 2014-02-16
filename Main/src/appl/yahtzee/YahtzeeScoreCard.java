package appl.yahtzee;

public class YahtzeeScoreCard {

    // There are thirteen scoring boxes: six in the Upper Section and seven in the Lower Section.  For a box in the Upper Section, the
    // score is the total of the dice with the given number.  For a box in the Lower Section, the scores are as follows:
    //   3 of a kind:    total of 5 dice
    //   4 of a kind:    total of 5 dice
    //   Full House:     25 points
    //   Small Straight: 30 points
    //   Large Straight: 40 points
    //   Yahtzee:        50 points
    //   Chance:         total of 5 dice
    
    // Each of the thirteen scoring baxes can be filled at most once.  A bonus of 35 points is awarded if the score for the Upper Section
    // is 63 or more.  A yahtzee bonus of 100 points is awarded for each yahtzee beyond the first yahtzee, provided that 0 has not been
    // entered 0 in the yahtzee box.  In addition, for a bonus yahtzee, one scores either the appropriate total in the Upper Section or,
    // the appropriate box in the upper section is already filled, any unfilled box in the Lower Section.

    public static enum Box {
        ONES, TWOS, THREES, FOURS, FIVES, SIXES,
        THREE_OF_A_KIND, FOUR_OF_A_KIND, FULL_HOUSE, SMALL_STRAIGHT, LARGE_STRAIGHT, YAHTZEE, CHANCE;
        
        public boolean isUpperSection() {
            return (ordinal() <= 5);
        }
    }

    static final int FULL_HOUSE_SCORE     =  25;
    static final int SMALL_STRAIGHT_SCORE =  30;
    static final int LARGE_STRAIGHT_SCORE =  40;
    static final int YAHTZEE_SCORE        =  50;
    static final int UPPER_SECTION_BONUS  =  35; 
    static final int YAHTZEE_BONUS        = 100; 
    
    Integer[] myBoxes;
    int[] myUpperSection = new int[6]; 
    int myUpperSectionBonus = 0;
    int myYahtzeeBonus = 0;
    
    public YahtzeeScoreCard() {
        myBoxes = new Integer[Box.values().length];
        for (int i = 0; i < myBoxes.length; i++) {
            myBoxes[i] = null;
        }
    }

    public void fillBox(Box box, YahtzeeRoll roll) {
        assert(myBoxes[box.ordinal()] == null);
        if (roll.isYahtzee() && (box != Box.YAHTZEE)) {
            assert((myBoxes[Box.YAHTZEE.ordinal()] != null) && (myBoxes[Box.YAHTZEE.ordinal()] != 0));
            assert(box.isUpperSection() || (myBoxes[roll.getValue(0)] == null));
            myYahtzeeBonus += YAHTZEE_BONUS;
        }
        int points;
        if (box.isUpperSection()) {
            int[] histogram = roll.computeHistogram();
            int count = histogram[box.ordinal()];
            points = count * box.ordinal();
        }
        else if (box.equals(Box.THREE_OF_A_KIND)) {
            assert(roll.isFourOfAKind());
            points = roll.computeSumOfDice();
        }
        else if (box.equals(Box.FOUR_OF_A_KIND)) {
            assert(roll.isFourOfAKind());
            points = roll.computeSumOfDice();
        }
        else if (box.equals(Box.FULL_HOUSE)) {
            assert(roll.isFullHouse());
            points = FULL_HOUSE_SCORE; 
        }
        else if (box.equals(Box.SMALL_STRAIGHT)) {
            assert(roll.isSmallStraight());
            points = SMALL_STRAIGHT_SCORE; 
        }
        else if (box.equals(Box.LARGE_STRAIGHT)) {
            assert(roll.isSmallStraight());
            points = LARGE_STRAIGHT_SCORE; 
        }
        else if (box.equals(Box.YAHTZEE)) {
            assert(roll.isYahtzee());
            points = YAHTZEE_SCORE;
        }
        else if (box.equals(Box.CHANCE)) {
            assert(roll.isYahtzee());
            points = roll.computeSumOfDice();
        }
        else {
            assert(false);
            points = 0;
        }
        myBoxes[box.ordinal()] = points;
    }
    
    public int score() {
        int score = 0;
        for (Integer box : myBoxes) {
            score += box;
        }
        score += myUpperSectionBonus;
        score += myYahtzeeBonus;

        return score;
    }

    public static void main(String[] args) {
        //YahtzeeScoreCard card = new YahtzeeScoreCard();
    }
}
