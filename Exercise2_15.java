//Stephen Harte 09001409

import javax.swing.*;
public class Exercise2_15
{

	public static void main(String[] args)
	{
		String empName = "";
		int hoursWorked= 0;
		String temp = "";
		double hourlyRate = 0.00;
		double fedTaxRate = 0.00;
		double stTaxRate = 0.00;
		//boolean for validation
		boolean valid;
		
		//Strings for the JOption Pane.
		String displayMenuName = "Please enter emnployee's name:";
		String displayMenuHours = "Enter the numbers of hours worked:";
		String displayMenuRate = "Enter the hourly rate:";
		String displayMenuFedRate = "Enter the Federal Tax With-holding Rate (eg: 20 for 20%)";
		String displayMenuStateRate = "Enter the State Tax With-holding Rate (eg: 20 for 20%)";
		String errorMessage = "Error invalid input type";
		
		temp = JOptionPane.showInputDialog(null,displayMenuName,"Enter Name",1);
		valid = isValidString(temp);
		
		if(valid)
		{
			empName = temp;
		}
		else
		{
			JOptionPane.showMessageDialog(null,errorMessage,"Error",1);
		}
		temp = JOptionPane.showInputDialog(null,displayMenuHours,"Enter Hours",1);
		valid = isValidNumber(temp);
		
		if(valid)
		{
			hoursWorked = Integer.parseInt(temp);
		}
		else
		{
			JOptionPane.showMessageDialog(null,errorMessage,"Error",1);
		}
		
		temp = JOptionPane.showInputDialog(null,displayMenuRate,"Enter Pay Rate",1);
		
		valid = isValidDouble(temp);
		
		if(valid)
		{
			hourlyRate = Double.parseDouble(temp);
		}
		else
		{
			JOptionPane.showMessageDialog(null,errorMessage,"Error",1);
		}
		
		temp = JOptionPane.showInputDialog(null,displayMenuFedRate,"Enter Pay Rate",1);
		valid = isValidDouble(temp);
		
		if(valid)
		{
			fedTaxRate = Double.parseDouble(temp)/100;
		}
		else
		{
			JOptionPane.showMessageDialog(null,errorMessage,"Error",1);
		}
		
		temp = JOptionPane.showInputDialog(null,displayMenuStateRate,"Enter Pay Rate",1);
		valid = isValidDouble(temp);
		
		if(valid)
		{
			stTaxRate = Double.parseDouble(temp)/100;
		}
		else
		{
			JOptionPane.showMessageDialog(null,errorMessage,"Error",1);
		}
		
		
		displayResults(empName,hoursWorked,hourlyRate,fedTaxRate,stTaxRate);
		//System.out.println(empName + " " + hoursWorked + " " + hourlyRate + " " + fedTaxRate + " " + stTaxRate);just for testing.
	}
		public static void displayResults(String name, int hours, double hourlyRate, double fedRate, double stateRate)
		{
			
			String displayName = "Employee Name: "+ name + "\n";
			String displayHours = "Hours Worked: "+ (hours) +"\n";
			String displayRate = "Pay Rate: $"+ hourlyRate+ "\n";
			double grossPay = (hourlyRate * hours);
			double fedPay = (grossPay * fedRate);
			double statePay = (grossPay * stateRate);
			double netPay = (grossPay - (fedPay + statePay));
			String displayGross = "Gross Pay:$" + grossPay+ "\n";
			String displayDeductions = "Deductions: \n     Federal With-Holding Rate(" + (fedRate * 100)+ "%):      $" + fedPay + "\n     State With-Holding Rate("+(stateRate * 100)+"%):            $" + statePay + "\n     Total Deductions:                                    $" + (fedPay + statePay)+"\n"; 
			String displayNet = "NetPay:                                                           $"+ netPay;
			
			
			JOptionPane.showMessageDialog(null, displayName + displayHours + displayRate + displayGross + displayDeductions + displayNet,"Results",-1 );
			
		}
		public static boolean isValidString(String temp)
		{
			boolean valid = false;
			String pattern = "[A-Za-z]*";
			
			if(temp.matches(pattern))
			{
				valid = true;
				return valid;
			}
			else
				return valid;
		}
		
		public static boolean isValidDouble(String temp)
		{
			boolean valid = false;

			String pattern = "[0-9]*[/.0-9]*";
			
			if(temp.matches(pattern))
			{
				valid = true;
				return valid;
			}
			else
				return valid;
		}
		public static boolean isValidNumber(String temp)
		{
			boolean valid = false;
			
			String pattern = "[0-9]{2}";
			
			if(temp.matches(pattern))
			{
				valid = true;
				return valid;
			}
			else
				return valid;
		}
			
}
		


