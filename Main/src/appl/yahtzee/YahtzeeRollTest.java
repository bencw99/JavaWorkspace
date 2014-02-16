package appl.yahtzee;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class YahtzeeRollTest {

    @Test
    public void test() {
        YahtzeeRoll roll
        = new YahtzeeRoll(Arrays.asList(new Integer[]{1, 1, 1, 1, 1}));
        assertTrue(roll.isYahtzee());
        assertTrue(roll.isFourOfAKind());
        assertTrue(roll.isThreeOfAKind());
        assertFalse(roll.isFullHouse());
        assertFalse(roll.isSmallStraight());
        assertFalse(roll.isLargeStraight());
        System.out.println(roll);
        System.out.println("Yahtzee: "        + roll.isYahtzee());
        System.out.println("4 Kind: "         + roll.isFourOfAKind());
        System.out.println("3 Kind: "         + roll.isThreeOfAKind());
        System.out.println("Full house: "     + roll.isFullHouse());
        System.out.println("Small Straight: " + roll.isSmallStraight());
        System.out.println("Large Straight: " + roll.isLargeStraight());

        roll = new YahtzeeRoll(Arrays.asList(new Integer[]{6, 1, 6, 6, 6}));
        assertFalse(roll.isYahtzee());
        assertTrue(roll.isFourOfAKind());
        assertTrue(roll.isThreeOfAKind());
        assertFalse(roll.isFullHouse());
        assertFalse(roll.isSmallStraight());
        assertFalse(roll.isLargeStraight());
        System.out.println(roll);
        System.out.println("Yahtzee: "        + roll.isYahtzee());
        System.out.println("4 Kind: "         + roll.isFourOfAKind());
        System.out.println("3 Kind: "         + roll.isThreeOfAKind());
        System.out.println("Full house: "     + roll.isFullHouse());
        System.out.println("Small Straight: " + roll.isSmallStraight());
        System.out.println("Large Straight: " + roll.isLargeStraight());

        roll = new YahtzeeRoll(Arrays.asList(new Integer[]{2, 3, 3, 3, 2}));
        assertFalse(roll.isYahtzee());
        assertFalse(roll.isFourOfAKind());
        assertTrue(roll.isThreeOfAKind());
        assertTrue(roll.isFullHouse());
        assertFalse(roll.isSmallStraight());
        assertFalse(roll.isLargeStraight());
        System.out.println(roll);
        System.out.println("Yahtzee: "        + roll.isYahtzee());
        System.out.println("4 Kind: "         + roll.isFourOfAKind());
        System.out.println("3 Kind: "         + roll.isThreeOfAKind());
        System.out.println("Full house: "     + roll.isFullHouse());
        System.out.println("Small Straight: " + roll.isSmallStraight());
        System.out.println("Large Straight: " + roll.isLargeStraight());
        
        roll = new YahtzeeRoll(Arrays.asList(new Integer[]{6, 2, 5, 4, 3}));
        assertFalse(roll.isYahtzee());
        assertFalse(roll.isFourOfAKind());
        assertFalse(roll.isThreeOfAKind());
        assertFalse(roll.isFullHouse());
        assertTrue(roll.isSmallStraight());
        assertTrue(roll.isLargeStraight());
        System.out.println(roll);
        System.out.println("Yahtzee: "        + roll.isYahtzee());
        System.out.println("4 Kind: "         + roll.isFourOfAKind());
        System.out.println("3 Kind: "         + roll.isThreeOfAKind());
        System.out.println("Full house: "     + roll.isFullHouse());
        System.out.println("Small Straight: " + roll.isSmallStraight());
        System.out.println("Large Straight: " + roll.isLargeStraight());

        roll = new YahtzeeRoll(Arrays.asList(new Integer[]{4, 2, 5, 4, 3}));
        assertFalse(roll.isYahtzee());
        assertFalse(roll.isFourOfAKind());
        assertFalse(roll.isThreeOfAKind());
        assertFalse(roll.isFullHouse());
        assertTrue(roll.isSmallStraight());
        assertFalse(roll.isLargeStraight());
        System.out.println(roll);
        System.out.println("Yahtzee: "        + roll.isYahtzee());
        System.out.println("4 Kind: "         + roll.isFourOfAKind());
        System.out.println("3 Kind: "         + roll.isThreeOfAKind());
        System.out.println("Full house: "     + roll.isFullHouse());
        System.out.println("Small Straight: " + roll.isSmallStraight());
        System.out.println("Large Straight: " + roll.isLargeStraight());
    }

}
