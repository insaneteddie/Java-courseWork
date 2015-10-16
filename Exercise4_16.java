//Stephen Harte 09001409
import java.util.Scanner;

public class Exercise4_16
{
    public static void main(String[] args)
    {
        System.out.print("Enter a positive number: ");
        Scanner in = new Scanner (System.in);
        int number = in.nextInt();
        int numbEntered = number;
        int count;
        for (int i = 2; i<=(number); i++) {
            count = 0;
            while (number % i == 0) {
                number /= i;
                count++;
            }

            if (count != 0) 
            {
                System.out.println("Prime Factor of "+ numbEntered +"  : "+i);
            }
            in.close();
        }
    }
}