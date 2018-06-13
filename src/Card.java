import java.util.*;

public class Card implements Comparable<Card>{
  private int number;
  private String color;
  private String[] names = {"Big Bird", "Zoe", "Cookie Monster", "Elmo", "Baby Bear", "Rosita", "Grover", "Ernie and Bert", "Oscar the Grouch", "Monster"};
  private String[] messages = {"**The other player draws a card**", "**The other player draws 2 cards**", "**Select a color for this wild card. The pile color becomes that color**"};
  public Card(int number, String color) {
    this.number = number;
    this.color = color;
  }
  public String changeColor() {
    if (this.number==10) {
      System.out.println("Choose a color"+'\n'+"1) Red, 2) Yellow, 3) Blue, 4) Green");
      Scanner input = new Scanner(System.in);
      int a = input.nextInt();
      switch (a) {
        case 1: this.color = "Red";
                break;
        case 2: this.color = "Yellow";
                break;
        case 3: this.color = "Blue";
                break;
        case 4: this.color = "Green";
                break;
        default:System.out.println("Please enter a correct digit");
        		this.changeColor(); 
        		break;
      }
    }
    return this.color;
  }
  public String changeColor(int n) {
	  if (this.number==10) {
		  switch (n) {
	        case 1: this.color = "Red";
            	break;
	        case 2: this.color = "Yellow";
            	break;
	        case 3: this.color = "Blue";
            	break;
	        case 4: this.color = "Green";
            	break;
	        default: break;
		  }
	  }
	  return this.color;
  }
  public int getNumber() {
    return this.number;
  }
  public String getColor() {
	return this.color;
  }
  public String getName() {
    return names[this.number-1];
  }
  public String getMessage() {
    if (this.number>7)
      return messages[this.number-8];
    else
      return "";
  }
  public String toString() {
	  if (this.getNumber()==8) {
		  return this.getName()+": "+this.getColor()+" Draw 1 ";
	  }
	  if (this.getNumber()==9) {
		  return this.getName()+": "+this.getColor()+" Draw 2 ";
	  }
	  if (this.getNumber()==10) {
		  return this.getName()+": "+this.getColor()+" Wild Card";
	  }
	  return this.getName()+": "+this.getColor()+" #"+this.getNumber();
  }
  public int compareTo(Card O) {
    if (this.getNumber()>O.getNumber())
      return 1;
    else if (this.getNumber()<O.getNumber())
      return -1;
    else
      return 0;
  }
}