public class Q3CreditCardCheck
{
  public static void main(String[]args)
  {
	int [] creditCard = {4, 9, 9, 9, 2, 2, 9, 8, 8, 0, 0, 9, 3, 4, 4, 6};
	String result = "Credit card number is ";
	boolean output = false;
	output = isValidCreditCardNumber(creditCard);
	if (output)
	  result += "valid";
	else
	  result += "invalid";
	System.out.println(result);
  }
     
  public static boolean isValidCreditCardNumber(int [] creditCardNumber)
  {
	int index, temp, sumOfOddPositionedDigits = 0, sumOfEvenPositionedDigits = 0;	 
	for (index = 0; index < creditCardNumber.length; index += 2)
    {
      temp = creditCardNumber[index] * 2;
      if (temp < 10) 
        sumOfOddPositionedDigits += temp;
      else               
        sumOfOddPositionedDigits += (temp % 10) + 1;
    } 
    for (index = 1; index < creditCardNumber.length; index += 2)
      sumOfEvenPositionedDigits += creditCardNumber[index];
    temp = (sumOfOddPositionedDigits + sumOfEvenPositionedDigits) % 10;
    
    if (temp == 0) 
      return true;
    else 
      return false;
  }
}	    