import javax.swing.JOptionPane;
	public class Num1
	{
		public static void main(String[]args)
		{
			int num;
			num = Integer.parseInt(JOptionPane.showInputDialog(null,"Input a Number: ","Input",1));
			JOptionPane.showMessageDialog(null,"Your number was :\t"+num,"Your NUmber",1);
		}
	}