import java.util.*;

public class UNO {
  private static Hand determinePlayOrder(Hand h1, Hand h2, PlayPile p) {
	p.shuffle();
    h1.draw(1, p);
    System.out.println("You drew | "+h1.getLastCard().toString());
    h2.draw(1, p);
    System.out.println("The computer drew | "+h2.getLastCard().toString());
    if (h1.compareTo(h2)==0)
      determinePlayOrder(h1, h2, p);
    if (h1.compareTo(h2)<0)
      return h2;
    else
      return h1;
  }
  private static boolean gamePlay(boolean p1First, Hand h1, Hand h2, PlayPile p) {
	boolean gameOver = false;
    if (p1First) {
    	if (h1.getSize()==2) {
    		UNOCheck();
    		if (p.attemptPlay(h1.playCard(), h1, h2, false)) {
    			gameOver = true;
    			System.out.println("You won!");
    		}
    		else {
    			h1.draw(1, p);
    			p.attemptPlay(h1.playLastCard(), h1, h2, false);
    		}
    	}
    	//executes attempt play, if play is not valid, draws a card and attempts to play it.
    	else
    		if (p.attemptPlay(h1.playCard(), h1, h2, false)==false) {
    			h1.draw(1, p);
    			p.attemptPlay(h1.playLastCard(), h1, h2, false);
    		}
    }
    if (!p1First) {
    	if (h2.getSize()==2) {
    		if (AIPlay(h1, h2, p)) {
    			gameOver = true;
    			System.out.println("The computer won D:");
    		}
    		else {
    			h2.draw(1, p);
    			AIPlay(h1, h2, p);
    		}
    	}
    	else
    		if (AIPlay(h1, h2, p)==false) {
    			h2.draw(1, p);
    			AIPlay(h1, h2, p);
    		}
    }
    return gameOver;
  }
  public static boolean UNOCheck() {
    Scanner input = new Scanner(System.in);
    String temp = input.next();
    return (temp=="UNO!");
  }
  // AI Stuff
  public static boolean AIPlay(Hand h1, Hand h2, PlayPile p) {
	  //index of Valid Play
	  int a = 0;
	  for (int i = 0; i<h2.getSize(); i++) {
		  if (p.validPlay(h2.getCard(i)))
			  a = i;
	  }	  
	  return (p.attemptPlay(h2.playCard(a), h2, h1, true));
  }
  public static void main (String[] args) {
    boolean p1First = false;
    boolean gameOver = false;
    PlayPile p = new PlayPile();
    //h1 = human player, h2 = CPU
    Hand h1 = new Hand();
    Hand h2 = new Hand();
    double turn = 1;
    Hand temp = determinePlayOrder(h1, h2, p);
    if (temp.equals(h1)) {
      p1First = true;
      System.out.println("You go first.");
    }
    else
      System.out.println("The computer goes first.");
    System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    p.reset(h1, h2);
    h1.draw(5, p);
    h2.draw(5, p);
    p.initializeDiscardPile();
    while (!gameOver) {
    	System.out.print("Turn "+(int)(turn));
    	if (p1First)
    		System.out.println(", Your Turn...");
    	else
    		System.out.println(", Computer's Turn...");
    	System.out.println(p.toString());
    	if (p1First)
    		System.out.println("Your hand: "+h1.toString());
    	else
    		System.out.println("Computer's hand: "+h2.toString());
    	gameOver = gamePlay(p1First, h1, h2, p);
    	turn+=.5;
    	p1First = !p1First;
    	System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
  }
}