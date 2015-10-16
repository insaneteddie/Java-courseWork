//Stephen Harte 09001409
public class Exercise5_9
{
	public static void main(String [] args)
	{
		System.out.println("Feet\t\tMeters\t\t|\tMeters\t\tFeet\n______________________________________________________________________");
		double mCount = 20.0;
		for(double i= 1;i<=10;i++)
		{
			System.out.printf("%-16.1f",i);
			System.out.printf("%-10.3f",footToMeter(i));
			System.out.print("\t|\t");
			System.out.printf("%-16.1f",mCount);
			System.out.printf("%-10.3f",meterToFoot(mCount));
			System.out.println();
			mCount+=5;
		}
	}
	
	public static double footToMeter(double foot)
	{
		double meter = 0.305*foot;
		return meter;
	}
	
	public static double meterToFoot(double meter)
	{
		double foot = 3.279*meter;
		return foot;
	}
	
}