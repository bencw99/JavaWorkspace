package appl.cardcoingame;

import lib.cards.Card;
import lib.cards.StandardDeck;

public class CardCoinGame {

	public static final int BET_COIN = 0;
	public static final int BET_CARD = 1;
	public static final int BET_HEADS= 0;
	public static final int BET_TAILS = 1;
	public static final int BET_RED = 0;
	public static final int BET_BLACK = 1;
	
	private int          myStartingNumChips;
	private int          myNumDecks;
	private StandardDeck myDeck;
	private int          myDeckSize;
	private int          myTargetChips;
	private int          myNumChips;
	private int          myRedCount;
	private int          myBlackCount;
	private int          myNumRounds;

	
	public CardCoinGame() {
		this(6, 2);
	}
	
	public CardCoinGame(int starting_num_chips) {
		this(starting_num_chips, 2);
	}
	
	public CardCoinGame(int starting_num_chips, int num_decks) {
		this.myStartingNumChips = starting_num_chips;
		this.myNumDecks = num_decks;
		this.myDeck = new StandardDeck(this.myNumDecks);
		this.myDeckSize = myDeck.getNumCards();
		this.myTargetChips = starting_num_chips * 2;
	}
	
	public Object[] play() {
        int result;
		myNumChips = myStartingNumChips;
	    myRedCount = myDeckSize / 2;
	    myBlackCount = myDeckSize / 2;
	    myDeck.shuffle();
	    myNumRounds = 0;
	    while ((myNumChips > 0) && (myNumChips < myTargetChips)) {
	        result = playRound();
	        myNumRounds += 1;
	        myNumChips += result;
	    }
	    return (new Object[] { ((myNumChips > 0) ? 1 : 0), myNumRounds });
	}
	
	
	private Card getCard() {
		Card card = myDeck.deal();
		if (card == null) {
			myRedCount = myDeckSize / 2;
			myBlackCount = myDeckSize / 2;
			myDeck.shuffle();
			card = myDeck.deal();
		}
		return card;
	}

	private int playRound() {
        assert(myBlackCount >= 0);
        assert(myRedCount >= 0);
        int bet_size = 1;
        int bet;
        
        if (myBlackCount > myRedCount) {
        	bet = CardCoinGame.BET_BLACK;
            if (myRedCount == 0) {
            	bet_size = Math.min(myNumChips, myTargetChips - myNumChips);
            }
        }
        else {
        	bet = CardCoinGame.BET_RED;
        	if (myBlackCount == 0) {
        		bet_size = Math.min(myNumChips, myTargetChips - myNumChips);
        	}
        }
        Card card = getCard();
        if (card.isBlack()) {
            myBlackCount -= 1;
        }
        else {
        	myRedCount -= 1;
        }
        if (((card.isBlack()) && (bet == CardCoinGame.BET_BLACK)) ||
        	((card.isRed()) && (bet == CardCoinGame.BET_RED))) {
        	return bet_size;
        }
        else {
        	return -bet_size;
        }
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int j = 0; j < 51; j++) {
		    CardCoinGame game = new CardCoinGame(j);
		    int num_wins = 0;
		    int num_games = 0;
		    int sum_num_rounds = 0;
		    for (int i = 0; i < 1000000; i++) {
		    	Object[] obj = game.play();
		    	int result = (Integer) obj[0];
		    	int num_rounds = (Integer) obj[1];
		        num_wins += result;
		        sum_num_rounds += num_rounds;
		        num_games += 1;
		    }
		    System.out.format("%3d %8d %8d %7.5f %6.1f\n", j, num_wins, num_games, (double) num_wins / num_games, (double) sum_num_rounds / num_games);
		}

	}

}
