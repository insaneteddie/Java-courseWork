//Stephen Harte 09001409
import java.util.Scanner;
public class Exercise5_5
{
	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Please enter a numer:");
		double num1 = Double.parseDouble(in.nextLine());
		System.out.print("Please enter a numer:");
		double num2 = Double.parseDouble(in.nextLine());
		System.out.print("Please enter a numer:");
		double num3 = Double.parseDouble(in.nextLine());
		double maxNumber = num1;
		if(num2 > maxNumber)
			maxNumber = num2;
		if(num3 > maxNumber)
			maxNumber = num3;
		System.out.println("The largest number you entered was "+ maxNumber);
		in.close();
	}
}