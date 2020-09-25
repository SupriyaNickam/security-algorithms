
import java.util.Scanner;
import java.util.stream.IntStream;

public class RC4
{
    public static void main(String args[])
    {
        int[] P;
        int i = 0; 
        int n;
        char choice = 'y';
        int option = 0;

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
                get_cipher(P, n, 8);
            }
            else if(option == 2)
            {
                System.out.println("Enter "+n+ " digits lesser than 256:");
                for(i = 0; i < n; i++)
                    P[i] = sc.nextInt();
                System.out.println();
                get_cipher(P, n, 256);
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

    public static void get_cipher(int[] plaintext, int n, int bit)
    {
        int[] cipher = new int[n];
        int[] S = IntStream.range(0, bit).toArray();
        int[] K = {1, 2, 3, 6};
        int[] T = new int[bit];

        int i = 0; 
        int j = 0;
        int k = 0;
        int t = 0;
        int temp;

        for(i = 0; i < bit; i++)
        {
			T[i] = K[i % K.length];
        }

        for( i = 0; i < bit; i++ )
        {
            j = (j + S[i] + T[i]) % bit; 

            temp = S[i];
            S[i] = S[j];
            S[j] = temp;            
        }

        System.out.println("The initial permutation is:");
        for(i = 0; i < S.length; i++)
            System.out.print(" "+S[i]);
        System.out.println();

        i = 0;
        while(i < n)
        {
            i = (i + 1) % bit;
            j = (j + S[i]) % bit;

            temp = S[i];
            S[i] = S[j];
            S[j] = temp;  

            t = (S[i] + S[j]) % bit;
            k = S[t]; 
            //System.out.println(k);

            cipher[i - 1] = plaintext[i - 1] ^ k;
        }

        System.out.println("The cipher text is:");
        for(i = 0; i < cipher.length; i++)
            System.out.print(" "+cipher[i]);
        System.out.println();

    }

   
}