import java.util.Scanner;

public class WWW {
	public static void main(String[] args){
	    //Get input from user
	    System.out.println(":E monster hungry!");
	    Scanner x = new Scanner(System.in);
	    System.out.print("feed nibble :open_mouth: ");
	    //char w = x.next().charAt(0);
	    // Check whether the input is a letter or dgit
	    int max = 12;// we set a max for the number of itterations the code will run

	    char w = x.next().charAt(0);
	    String www = " ";

	    for (int count = 2; (Character.isLetter(w) || Character.isDigit(w)) && count <= max; count++){
	      if (w >='0' && w <= '9'){
	    	System.out.println("What w sees: " + w);
	        System.out.println("nom nom nom");
	      }
	      else if (w >='A' && w <= 'Z'){
	    	System.out.println("What w sees: " + w);
	        System.out.println("YUM!");
	      }
	      else if (w >='a' && w <= 'z'){
	    	System.out.println("What w sees: " + w);
	        System.out.println("yum!");
	      }
	      else{
	          System.out.println("barrffff :o" + w);
	        }
	      System.out.print("feed nibble :open_mouth: ");
	      
	      www += w;
	      w = x.next().charAt(0);
	      
	      if((count % 3 == 0) && count >= 12){
	        System.out.println("\t Here are the last three chars you gave me: " + www.substring(9,12) + " "
	        + "\n\t monster want variety!");
	      }
	      else if((count % 3 == 0) && count >= 9){
	        System.out.println("\t Here are the last three chars you gave me: " + www.substring(6,9)
	        + "\n\t monster want variety!");
	      }
	      else if(count % 3 == 0 && count >=  6){
	        System.out.println("\t Here are the last three chars you gave me: " + www.substring(3,6)
	        + "\n\t monster want variety!");
	      }
	      else if(count % 3 == 0 && count <= 3){
	        System.out.println("\t Here are the last three chars you gave me: " + w + www
	        + "\n\t monster want variety!");

	      }



	    }
	    // After the code runs for 12 iterations, the program will end
	    System.out.println("I LEAVE :(");

	}
}
