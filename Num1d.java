import javax.swing.JOptionPane;
	public class Num1d
	{
		public static void main(String[]args)
		{
			int num;
			String entry;
			String err = "Your number was out of required range (1-100)";
			String err2 = "You did not enter a number, Please enter a number 1-100";
			String pattern1 = "[0-9]+";
			boolean number = false;
			if(args.length !=1) {
				System.out.println("Usage: java Num1d number");
			}
			else {	
				entry = args[0];
			if (entry.matches(pattern1))
			  {
					num = Integer.parseInt(entry);
				  	number = true;
				  	if(num < 1 || num > 100)
					System.out.println(err);
					else 
					System.out.println("Your number was : "+num);
				}
			else
			System.out.println(err2);
		}
	}
	}