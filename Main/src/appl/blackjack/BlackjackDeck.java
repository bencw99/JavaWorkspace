package appl.blackjack;

import lib.cards.Card;
import lib.cards.Deck;

public class BlackjackDeck extends Deck<BlackjackCard> {

    public BlackjackDeck(int numDecks) {
        super(numDecks);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected BlackjackCard getCard(int index) {
        return new BlackjackCard(new Card(index));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        BlackjackDeck deck = new BlackjackDeck(2);
        BlackjackCard card;
        // deck.shuffle();
        card = deck.deal();
        while (card != null) {
            System.out.format("%s of %s\n", card.getRank(), card.getSuit(), card.getValue());
            card = deck.deal();
        }
    }

}
