package lib.cards;

public class StandardDeck extends Deck<Card> {

    public StandardDeck(int numDecks) {
        super(numDecks);
    }

    @Override
    protected Card getCard(int index) {
        return new Card(index);
    }

}
