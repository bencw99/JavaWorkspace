package lib.dice;

import java.util.Random;

public class Die {

    private final  int    myNumSides;
    private        int    myValue;
    private static Random ourRandom = new Random();
	
    public Die() {
        this(6);
    }
    
    public Die(int numSides) {
        this(numSides, ourRandom.nextInt(numSides) + 1);
    }
    
    public Die(int numSides, int value) {
        myNumSides = numSides;
        myValue = value;
    }

    public final int roll() {
        myValue = ourRandom.nextInt(myNumSides) + 1;
        return myValue;   
    }

    public final int getValue() {
        return myValue;
    }

    public final int getNumSides() {
        return myNumSides;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Die die = new Die();
        System.out.println(die.getValue());
		die.roll();
        System.out.println(die.getValue());
        System.out.println(die.roll());
	}
}
