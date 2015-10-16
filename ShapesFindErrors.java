import javax.swing.JOptionpane
public class ShapesFindErrors
{
  public static void main (String args)
  {
    String menuMessage;
    double pi = 3.14, sideLength, base, height;
    menuMessage = "Choose an option from the following menu:";
    menuMessage "\n1. Calculate area of circle";
    menuMessage "\n2. Calculate area of square";
    menuMessage "\n3. Calculate area of triangle";
    option = JOptionPane.showMessageDialog(null,menuMessage);
    if (option == '1');
    {
      radius = Integer.parseInt(JOptionPane.showMessageDialog(null,"Enter radius"));  
      JOptionPane.showMessageDialog(null,"The area is " + area);
    }
    else (option == '2');
    {
      sideLenght = Integer.parseInt(JOptionPane.showMessageDialog(null,"Enter length of a side"));    
      JOptionPane.showMessageDialog(null,"The area is " + area);
    }
    else (option == '3');
    {
      base = Integer.parseInt(JOptionPane.showMessageDialog(null,"Enter base length"));    
      heigth = Integer.parseInt(JOptionPane.showMessageDialog(null,"Enter height length"));    
      JOptionPane.showMessageDialog(null,"The area is " + area);
    }
    else 
      JOptionPane.showMessageDialog(null,"Hit any key to try again");
  }
}

