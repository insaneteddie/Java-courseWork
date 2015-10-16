import javax.swing.JOptionPane;
public class Q1b
{
  public static void main(String [] args)
  {
    int number;
    String errorMessage = "\nwhich is not in the desired range of 1 to 100";
    number = Integer.parseInt(JOptionPane.showInputDialog(null,"Please enter a number"));
    if (number < 1 || number > 100)
      JOptionPane.showMessageDialog(null, "You entered the number: " + number + errorMessage); 
    else
      JOptionPane.showMessageDialog(null, "You entered the number: " + number); 
  }
}