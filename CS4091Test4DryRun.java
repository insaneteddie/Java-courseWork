public class CS4091Test4DryRun                                        
{                                                                     
  public static void main(String[] args)                              
  {                                                                   
    int a = 9, b = 3, c = 2, d = 8;                                   
    d = a++ + c * ++a / b % d + 3 + a;                                
    System.out.println(++a + "\t" + b++ + "\t" + --c + "\t" + d--);   
    a = a + b++ * c + 9 - d++;                                        
    System.out.println(a + "\t" + b-- + "\t" + --c + "\t" + d + 2);   
	c += 2 + 3 * 4;                                                      
    System.out.println(a / 3 + 3 * 1 - 2 + "\t");                     
    d = b * c + (2 % 4);                                              
    System.out.println(a + "\t" + b + "\t" + ++c + "\t" + --d);       
  }                                                                   
}                                                                     
                                                                      