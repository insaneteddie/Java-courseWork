//Stephen Harte 09001409

import java.util.*;

public class Exercise1_3 {

	public static void main(String[] args) {
		 displayMenu();
		
	}

	public static void displayMenu(){
		int choice;
		Scanner in = new Scanner(System.in);
		String menu = "1. Display Pattern. \n";
		menu += "2. Display Table. \n";
		menu += "0. Exit.";
		String patternNumber = "[0-9]+";
		
		System.out.println(menu);
		String userInput = in.nextLine();
		if(userInput.matches(patternNumber)){
			choice = Integer.parseInt(userInput);
		
			displaySelection(choice);
		}
		else
			displaySelection(4);
		 in.close();
	}
	
	public static void displaySelection(int choice){
		switch(choice){
			case 0:
				System.exit(0);
				break;
			case 1:
				displayJava();
				displayMenu();
				break;
			case 2:
				displayTable();
				displayMenu();
				break;
			default:
				System.out.println("Error Invalid Selection");
				displayMenu();
				break;	
		}
	}
	
	public static void displayJava(){
		String javaPattern;
		
		javaPattern  = "    J    A    V     V    A    \n";
		javaPattern += "    J   A A    V   V    A A   \n";
		javaPattern += "J   J  AAAAA    V V    AAAAA   \n";
		javaPattern += " J J  A     A    V    A     A  \n";
		
		System.out.println(javaPattern);
		
	}
	
	public static void displayTable()
	{
		System.out.println("a\ta*2\ta*3\t");
		System.out.println("1\t2  \t3  \t");
		System.out.println("2\t4  \t6  \t");
		System.out.println("3\t6  \t9  \t");
		System.out.println("4\t8  \t12 \t");
	}	
}
