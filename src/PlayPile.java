import java.util.*;

public class PlayPile {
  private ArrayList<Card> deck = new ArrayList<Card>();
  private ArrayList<Card> discardPile = new ArrayList<Card>();
  private String currentColor;
  public PlayPile() {
    for (int i = 1; i<=7; i++) {
      deck.add(new Card(i, "Red"));
      deck.add(new Card(i, "Yellow"));
      deck.add(new Card(i, "Blue"));
      deck.add(new Card(i, "Green"));
    }
    deck.add(new Card(8, "Red"));
    deck.add(new Card(8, "Blue"));
    deck.add(new Card(9, "Yellow"));
    deck.add(new Card(9, "Green"));
    for (int a = 0; a<4; a++)
      deck.add(new Card(10, ""));
  }
  public void shuffle() {
	  Collections.shuffle(deck);
  }
  public ArrayList<Card> draw(int n) {
	if (deck.size()==0) {
		deck.addAll(discardPile);
		discardPile.clear();
		discardPile.trimToSize();
		this.initializeDiscardPile();
	}
    ArrayList<Card> output = new ArrayList<Card>();
    for (int i = 0; i<n; i++) {
      output.add(deck.get(deck.size()-1));
      deck.remove(deck.size()-1);
    }
    deck.trimToSize();
    return output;
  }
  boolean validPlay(Card card) {
    if (discardPile.size()==0 || card.getNumber()==10 || card.getColor()==currentColor || card.getNumber()==discardPile.get(discardPile.size()-1).getNumber())
      return true;
    else
      return false;
  }
  private void draw1Check(Card card, Hand enemyHand) {
    if (card.getNumber()==8) {
      System.out.println(card.getMessage());
      enemyHand.draw(1, this);
    }
  }
  private void draw2Check(Card card, Hand enemyHand) {
    if (card.getNumber()==9) {
      System.out.println(card.getMessage());
      enemyHand.draw(2, this);
    }
  }
  private void wildCheck(Card card, int n) {
    if (card.getNumber()==10 && n==0) {
      System.out.println(card.getMessage());
      card.changeColor();
    }
    if (card.getNumber()==10 && n>0) {
      System.out.println(card.getMessage());
      card.changeColor(n);  
    }
  }
  public boolean attemptPlay(Card card,Hand yourHand, Hand enemyHand, boolean AI) {
    if (validPlay(card) && !AI) {
        draw1Check(card, enemyHand);
        draw2Check(card, enemyHand);
        wildCheck(card, 0);
        discardPile.add(card);
        currentColor = card.getColor();
        return true;
    }
    else 
    	if (validPlay(card) && AI) {
    		draw1Check(card, enemyHand);
            draw2Check(card, enemyHand);
            wildCheck(card, (int)(Math.random()+4));
            discardPile.add(card);
            currentColor = card.getColor();
            return true;
    	}
    	else {
    		yourHand.addCard(card);
    		return false;
    	}
  }
  public String toString() {
	  if (discardPile.size()==0)
		  return "Discard Pile: "+'\n'+"NONE";
	  else
		  return "Discard Pile: "+'\n'+discardPile.get(discardPile.size()-1).toString();
  }
  public void reset(Hand h1, Hand h2) {
	deck.addAll(h1.dumpCards());
	deck.addAll(h2.dumpCards());
    deck.addAll(discardPile);
    discardPile.clear();
    Collections.shuffle(deck);
  }
  public void initializeDiscardPile() {
	  discardPile.addAll(this.draw(1));
	  if (discardPile.get(discardPile.size()-1).getNumber()>7)
		  initializeDiscardPile();
	  this.currentColor=discardPile.get(discardPile.size()-1).getColor();
  }
}