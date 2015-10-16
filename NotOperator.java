public class NotOperator
{
  public static void main(String [] args)
  {
    
	  int x = 10;
      boolean flag = true;
    
      if (x != 2)
        System.out.println("The value of x is not equivalent to 2");
      
      if (!(x == 2)) // if NOT x is equivalent to 2 i.e. if x is not equivalent to 2
        System.out.println("The value of x is not equivalent to 2");
   
      /* while these are equivalent in terms of what they achieve, the first 
         approach is easier to read.  Also, note the additional bracketing
         required in the second approach */
 
      if (!flag) // equivalent to writing if (flag == false)
        System.out.println("The value of flag is equivalent to false");
      // reads if not flag
      // flag at this point is set to true 
      // if not true is asking if the variable flag is not true, otherwise known as false
      // since flag is set to true this condition will fail and the statement will not execute
      
      if (flag) // equivalent to writing if (flag == true)
         System.out.println("The value of flag is equivalent to true");
  }
}