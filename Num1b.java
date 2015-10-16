import javax.swing.JOptionPane;
	public class Num1b
	{
		public static void main(String[]args)
		{
			int num;
			num = Integer.parseInt(JOptionPane.showInputDialog(null,"Input a Number: ","Input",1));
			if(num < 1 || num > 100)
			JOptionPane.showMessageDialog(null,"Your Number was out of range (1-100)","Error",1);
			JOptionPane.showMessageDialog(null,"Your number was :\t"+num,"Your NUmber",1);
		}
	}