import java.util.Scanner;
public class Histogram
{
  public static void main(String [] args)
  {
	int rangeFrequencies[] = new int[10];  
	Scanner in = new Scanner(System.in);
	int howMany, aRandomNumber;
	String result = "";
    System.out.print("Enter a number in the range 1 to 300: \t");
    howMany = Integer.parseInt(in.nextLine());
	if (howMany < 1 || howMany > 300)     
	  result = "You must enter a number in the range 1 to 300";
    else
	{ 
	  for (int i = 0; i < howMany; i++)
	  {
	    aRandomNumber = (int) ((Math.random() * 100) + 1);
		rangeFrequencies[(aRandomNumber - 1)/10]++;
      }
      
	  for (int i = 0; i < rangeFrequencies.length; i++)
	  {
		if (i == 0) 
		  result += " ";
		
		result += ((i * 10) + 1) + " to " + ((i + 1) * 10) + "\t\t";
		
		if (rangeFrequencies[i] > 0)
		{
		  for (int j = 0; j < rangeFrequencies[i]; j++)
		    result += "*";
		  result += " (" + rangeFrequencies[i] + ")";
		}
		result += "\n";
      }
	}  
    System.out.print(result);
  }
}

