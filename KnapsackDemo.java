import java.util.Scanner;

public class KnapsackDemo {
    public static void main(String[] args) 
    {
        int sik[];
        int n;
        int m;
        int message[];

        char choice = 'y';

        Scanner sc = new Scanner(System.in);
        Knapsack ks;

        do
        {
            System.out.println("Enter modulus n: "); 
            n = sc.nextInt();
            System.out.println("Enter multipier m: "); 
            m = sc.nextInt();

            System.out.println("Enter size of super increasing sequence: ");
            int size = sc.nextInt();
            sik = new int[size];
            System.out.println("Enter "+size+" values for super increasing sequence (ascending order): "); 
            for(int i = 0; i < size; i++)
                sik[i] = sc.nextInt();

            System.out.println("Enter size of message: ");
            size = sc.nextInt();
            message = new int[size];
            System.out.println("Enter "+size+" binary bits of your message: "); 
            for(int i = 0; i < size; i++)
                message[i] = sc.nextInt();


            ks = new Knapsack(sik, n, m, message);

            int[] public_key = ks.general_knapsack();
            System.out.println("Public key is: "); 
            for(int i = 0; i < public_key.length; i++)
            {
                System.out.print(public_key[i]+ " ");
            }
            

            int[] cipher = ks.get_cipher(public_key);
            System.out.println();
            System.out.println("The cipher is: "); 
            for(int i = 0; i < cipher.length; i++)
            {
                System.out.print(cipher[i]+ " ");
            }

            int[] decrypted = ks.decrypt(cipher);
            System.out.println();
            System.out.println("The decrypted code is: "); 
            for(int i = 0; i < decrypted.length; i++)
            {
                System.out.print(decrypted[i]+ " ");
            }
            
            int[][] decoded = ks.verify(decrypted);
            System.out.println();
            System.out.println("The decoded binary equivalent is: "); 
            for(int i = 0; i < decoded.length; i++)
            {
                for(int j = 0; j < decoded[i].length; j++)
                     System.out.print(decoded[i][j]+ " ");
            } 
            

            System.out.println();
            System.out.println("Would you like to continue? (y/n)");
            choice = sc.next().charAt(0);

        }while(choice == 'y');
    }
}
