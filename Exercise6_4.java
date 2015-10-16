//Stephen Harte 09001409
import java.util.Scanner;
public class Exercise6_4
{
	public static void main(String [] arg)
	{
		Scanner in = new Scanner(System.in);
		int passCount = 0, count = 0, failCount = 0;
		int input = 0;
		int p = 40;
		while(input != -1 && count < 100)
		{
			count++;
			System.out.print("Please enter a score: ");
			input = Integer.parseInt(in.nextLine());
			if(input >= p)
			{
				passCount++;
			}
			else if(input < p && input!=-1)
			{
				failCount++;
			}
		}
		System.out.println("Number of passing Scoress: " +(passCount)+"\nNumber of scored that are less than the passing score: "+failCount);
		in.close();
	}
}