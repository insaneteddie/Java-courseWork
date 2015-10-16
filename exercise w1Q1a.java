import javax.swing.JOptionPane;
public class Q1a
{
  public static void main(String [] args)
  {
    int number;
    number = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter a number"));
    JOptionPane.showMessageDialog(null, "You entered the number: " + number); 
  }
}