import javax.swing.JOptionPane;
	public class Num1c
	{
		public static void main(String[]args)
		{
			int num;
			String entry;
			String err = "Your number was out of required range (1-100)";
			String err2 = "You did not enter a number, Please enter a number 1-100";
			String pattern1 = "[0-9]+";
			boolean number = false;
			entry = JOptionPane.showInputDialog(null,"Input a Number: ","Input",1);
			if (entry.matches(pattern1))
			  {
					num = Integer.parseInt(entry);
				  	number = true;
				  	if(num < 1 || num > 100)
					JOptionPane.showMessageDialog(null,err,"Error",1);
					else 
					JOptionPane.showMessageDialog(null,"Your number was :\t"+num,"Your Number",1);
				}
			else 
			JOptionPane.showMessageDialog(null,err2,"Error Invalid Entry",1);
		}
	}