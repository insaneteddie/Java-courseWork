import javax.swing.JOptionPane;
public class Q1c
{
  public static void main(String [] args)
  {
	String input, pattern = "[0-9]{1,3}", result = "You entered the value: ";
	String errorMessage1 = "which is not in the desired range of 1 to 100";
	String errorMessage2 = "the format of this value is invalid";
	String outputMessage1 = "which is a valid number";
	int number;
	input = JOptionPane.showInputDialog(null,"Please enter a number");
	result += input + "\n";
	if (input.matches(pattern))
	{
      number = Integer.parseInt(input);		
	  if (number < 1 || number > 100)
	    result += errorMessage1;
	  else
	   	result += outputMessage1;
    }   	
	else
	  result += errorMessage2;
    JOptionPane.showMessageDialog(null, result); 
  }
}
