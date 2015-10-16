//Stephen Harte 09001409


import java.util.*;

import javax.swing.*;

public class Exercise3_8 
{

	public static void main(String[] args) 
	{
		//variable initialisation
		String sorted = "Sorted Numbers are: ";
		String temp = "";
		int a = 0,b = 0,c = 0;
		//pattern for regular-expression.
		String pattern = "[0-9]*";
		//Strings for Display
		String numberOne = "Please enter your first Number:";
		String numberTwo = "Please enter your second Number:";
		String numberThree = "Please enter your third Number:";
		
		temp = JOptionPane.showInputDialog(null,numberOne,"Enter Number",1);
		
		if(temp.matches(pattern))
			a = Integer.parseInt(temp);
		
		temp = JOptionPane.showInputDialog(null,numberTwo,"Enter Number",1);
		
		if(temp.matches(pattern))
			b = Integer.parseInt(temp);
		
		temp = JOptionPane.showInputDialog(null,numberThree,"Enter Number",1);
		
		if(temp.matches(pattern))
			c = Integer.parseInt(temp);
	
		ArrayList<Integer> numArray = new ArrayList<Integer>();
		numArray.add(a);
		numArray.add(b);
		numArray.add(c);
		Collections.sort(numArray, Collections.reverseOrder());
		for(int i = 0; i < numArray.size(); i++)
		{
			sorted += numArray.get(i) + " ";
		}
		JOptionPane.showMessageDialog(null,sorted,"Sorted Numbers",-1);
	}
}
	
