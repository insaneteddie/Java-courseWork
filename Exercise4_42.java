//Stephen Harte 09001409
import java.util.Scanner;
public class Exercise4_42
{
	public static void main(String [] arg)
	{
		Scanner in = new Scanner(System.in);
		int countNeg=0, neg=0, countPos=0, pos=0;
		int input = 1;
		while(input != 0)
		{
			System.out.print("Please enter a number: ");
			input = Integer.parseInt(in.nextLine());
			if(input >0)
			{
				countPos++;
				pos += input;
			}
			else if(input<0)
			{
				countNeg++;
				neg += input;
			}
		}
		System.out.println("Postive count: " +countPos+"\nPositive sum: "+pos+ "\nNegative count: "+countNeg+ "\nNegative Sum: "+neg);
		in.close();
	}
}