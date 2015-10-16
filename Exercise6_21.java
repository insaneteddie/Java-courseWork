//Stephen Harte 09001409

import java.util.Scanner;

public class Exercise6_21
{
	public static void main(String [] args)
	{
		Scanner in = new Scanner(System.in);
		int balls, slots;
		System.out.print("\nEnter the number of balls to drop: ");
		balls = Integer.parseInt(in.nextLine());
		System.out.print("Enter the number of slots in the bean machine: ");
		slots = Integer.parseInt(in.nextLine());
		System.out.println();
		runBallDrop(balls, slots);
		in.close();
	}
	
	static void runBallDrop(int balls, int slots)
	{
		int holes[] = new int[slots];
		double chance = 0;
		int hole = 0;
		if(slots >= 1 || balls >1)
		{
			int nail = slots-1;
			for(int i=0;i<balls;i++)
			{
				hole = 0;
				for(int j=0;j<nail;j++)
				{
					chance = Math.random();
					if (chance >=0.5)
					{
						hole++;
						System.out.print("R");
					}
					else
					{
						System.out.print("L");
					}
				}
				holes[hole]++;
				System.out.println();
			}
			printArray(holes);
		}
		else
		{
			System.out.println("You have entered an invalid number of slots/balls\nPlease enter a number greater than 1\n\n");
		}
	}
	
	static void printArray(int[] slots)
	{
		System.out.println("\n");
		for(int i = 0;i<slots.length;i++)
		{
			System.out.println("Slot "+ (i+1) + ": "+slots[i]);
		}
	}
}