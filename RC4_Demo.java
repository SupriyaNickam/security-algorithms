
import java.util.Scanner;

public class RC4_Demo
{
    public static void main(String args[])
    {
        int[] P;
        int i = 0; 
        int n;
        char choice = 'y';
        int option = 0;

        RC4 rc;
        Scanner sc = new Scanner(System.in);

        do
        {
            System.out.println("Enter size of plaintext: ");
            System.out.println();
            n = sc.nextInt();

            P = new int[n];

            System.out.println("Enter your option: ");
            System.out.println("1. 8 x 3: ");
            System.out.println("2. 8 x 8: ");           
            System.out.println();

            option = sc.nextInt();

            if(option == 1)
            {
                System.out.println("Enter "+n+ " digits lesser than 8:");
                for(i = 0; i < n; i++)
                    P[i] = sc.nextInt();
                System.out.println();
                rc = new RC4(P, n, 8);
                int[] cipher = rc.get_cipher();

                System.out.println("The cipher text is:");
                for(i = 0; i < cipher.length; i++)
                    System.out.print(" "+cipher[i]);
                System.out.println();
            }
            else if(option == 2)
            {
                System.out.println("Enter "+n+ " digits lesser than 256:");
                for(i = 0; i < n; i++)
                    P[i] = sc.nextInt();
                System.out.println();
                rc = new RC4(P, n, 256);
                int[] cipher = rc.get_cipher();

                System.out.println("The cipher text is:");
                for(i = 0; i < cipher.length; i++)
                    System.out.print(" "+cipher[i]);
                System.out.println();
            }
            else
            {
                System.out.println("Invalid input.");
            }

            System.out.println();
            System.out.println("Do you wish to continue? (y/n): ");

            choice = sc.next().charAt(0);

        }while(choice == 'y');

    }    
   
}