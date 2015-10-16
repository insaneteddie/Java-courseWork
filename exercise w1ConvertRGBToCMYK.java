public class ConvertRGBToCMYK
{
  public static void main(String [] args)
  {
	String errorMessage1 = "Incorrect number of command-line arguments supplied.";
	String errorMessage2 = "\nUsage: java ConvertRGBToCMYK redValue greenValue blueValue";
	String errorMessage3 = "Each input value must be in the range 0 to 225 inclusive.";
	String result, pattern = "[0-9]{1,3}";
	int red, green, blue;
    double white, cyan, magenta, yellow, black;
    double temp1, temp2, temp3;
	if (args.length < 1 || args.length > 3)
	  result = errorMessage1 + errorMessage2;
	else if (args[0].matches(pattern) && args[1].matches(pattern) && args[2].matches(pattern))
	{
	   red = Integer.parseInt(args[0]);	
	   green = Integer.parseInt(args[1]);
	   blue = Integer.parseInt(args[2]);
	   if (red > 255 || green > 255 || blue > 255)
	     result = errorMessage3;
	   else
	   {
	     if (red == 0 && green == 0 && blue == 0)
	     {
		   cyan = magenta = yellow = 0;
		   black = 1;
         }
	     else
	     {	    
		   temp1 = red/255.0;
		   temp2 = green/255.0;
		   temp3 = blue/255.0;
		 
		   // get max of temp1, temp2 and temp3 and assign this to white
		   white = temp1;
		   if (temp2 > white) white = temp2;
		   if (temp3 > white) white = temp3;
		 
		   cyan = (white - temp1)/white;
		   magenta = (white - temp2)/white;
		   yellow = (white - temp3)/white;
		   black = 1 - white;
		   
		   cyan    = Math.round(cyan    * 100)/100.0;
		   magenta = Math.round(magenta * 100)/100.0;
		   yellow  = Math.round(yellow  * 100)/100.0;
		   black   = Math.round(black   * 100)/100.0;
         }  
	     result = "cyan:\t\t" + cyan + "\nmagenta:\t" + magenta + "\nyellow:\t\t" + yellow + "\nblack:\t\t" + black;
      }	
    }
	else
	  result = errorMessage3;
	System.out.println(result);
  }
}
