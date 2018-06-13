import java.util.*;

public class Hand implements Comparable<Hand> {
  private ArrayList<Card> hand = new ArrayList<Card>();
  public void draw(int n, PlayPile p) {
    hand.addAll(p.draw(n));
  }
  public Card getLastCard() {
    return hand.get(hand.size()-1);
  }
  public Card playCard() {
    System.out.println("Enter the position in your hand of the card you want to play");
    Scanner input = new Scanner(System.in);
    int cardToPlay = (int)(input.next().charAt(0))-48;
    if (cardToPlay<0) {
    	System.out.println("You entered too small a position...Playing the first card in your hand instead");
    	cardToPlay = 0;
    }
    if (cardToPlay>hand.size()-1) {
    	System.out.println("You entered too large a position...Playing the last card in your hand instead");
    	cardToPlay = hand.size()-1;
    }
    Card output = hand.get(cardToPlay);
    hand.remove(cardToPlay);
    return output;
  }
  public Card playCard(int n) {
	  Card output = hand.get(n);
	  hand.remove(n);
	  return output;
  }
  public Card playLastCard() {
	  System.out.println("Invalid play; attempting to play card you just drew");
	  Card output = hand.get(hand.size()-1);
	  hand.remove(hand.size()-1);
	  return output;
  }
  public void addCard(Card card) {
    hand.add(card);
  }
  public ArrayList<Card> dumpCards() {
	  ArrayList<Card> output = new ArrayList<Card>();
	  for (int i = 0; i<hand.size(); i++) {
		  Card temp = hand.get(i);
		  output.add(temp);
		  hand.remove(0);
	  }
	  return output;
  }
  public Card getCard(int n) {
	  return hand.get(n);
  }
  public int getSize() {
    return hand.size();
  }
  public String toString() {
	  String output = "";
	  for (int i = 0; i<hand.size(); i++) {
		  output+="("+i+") "+hand.get(i).toString()+" || ";
	  }
	  return output;
  }
  public int compareTo(Hand O) {
    if (this.getLastCard().getNumber()>O.getLastCard().getNumber())
      return 1;
    else if (this.getLastCard().getNumber()<O.getLastCard().getNumber())
      return -1;
    else
      return 0;
  }
}