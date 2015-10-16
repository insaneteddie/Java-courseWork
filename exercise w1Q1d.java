import javax.swing.JOptionPane;
public class Q1d
{
  public static void main(String [] args)
  {
	String result;
	String errorMessage1 = "You must enter one command-line argument";
	String errorMessage2 = "You have entered too many command-line arguments";
	String errorMessage3 = "\nUsage: java Q1d number";
	String errorMessage4 = "which is not in the desired range of 1 to 100";
	String errorMessage5 = "the format of this value is invalid";
	String outputMessage1 = "which is a valid number";
	String input, pattern = "[0-9]{1,3}";
	int number;  
	if (args.length < 1)
	  result = errorMessage1 + errorMessage3;
	else if (args.length > 1)
	  result = errorMessage2 + errorMessage1 + errorMessage3;
	else
	{
      input = args[0];
      result = "You entered the value: " + input + "\n";
      if (input.matches(pattern))
	  {  
	    number = Integer.parseInt(input);		
	    if (number < 1 || number > 100)
	      result += errorMessage4;
	    else
	   	  result += outputMessage1;
      }
	  else
	    result += errorMessage5;
    }	    
    JOptionPane.showMessageDialog(null, result); 
  }
}
