//Stephen Harte 09001409
import java.util.Scanner;
public class Exercise4_25
{
	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Enter a number to get the sum of the series of:  ");
		double x = Double.parseDouble(in.nextLine());
		double sum = 0;
		for (int i = 0;i<=x;i++)
		{
			for (int j=1;j<=i;j++)
			{
				sum += j;
			}
		}
		System.out.println("\n"+sum);
		in.close();
	}
}