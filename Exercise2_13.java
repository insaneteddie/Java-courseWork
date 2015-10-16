//Stephen Harte 09001409

import javax.swing.*;

public class Exercise2_13 {

	public static void main(String[] args) {
		
			double interestRate = (0.05/12.0);//5% annual interest rate
			double finalAmount = 0.00;
			String displayText = "Please enter monthly savings amount: ";
			int noOfMonths = 6;
			String userInput;
			String patternNumber = "[0-9]+";
			double amountSaved;
			double accBalance = 0.00;
			
			String displaySavings = "After the sixth month the account value is : ";
			userInput = JOptionPane.showInputDialog(null,displayText,"Enter Amount",1);
			
			if(!userInput.matches(patternNumber)){
				JOptionPane.showMessageDialog(null,"Error invalid Input","Error",1);
				
			}
			else
			{
			amountSaved = Double.parseDouble(userInput);
			
			for(int i = 0; i < noOfMonths; i++)
			{
				accBalance = (amountSaved + accBalance ) * (1.0 + interestRate);
				finalAmount = accBalance;
			}
			
				JOptionPane.showMessageDialog(null, displaySavings +"$"+ finalAmount,"Savings",1);
			}
			
				
			
			
	}

}
