import java.math.BigInteger;
import java.util.Scanner;

public class RSA_demo {
    public static void main(String[] args) 
    {
        int option;
        BigInteger p, q, e;
        String plaint_text;
        int[] cipher, message;
        char choice = 'y';

        Scanner sc = new Scanner(System.in);
        RSA rsa;

        do
        {
            System.out.println("Enter first distinct prime number p: "); 
            p = sc.nextBigInteger();
            System.out.println("Enter second distinct prime number q: "); 
            q = sc.nextBigInteger();
            System.out.println("Enter a small odd integer e: ");
            e = sc.nextBigInteger();

            rsa = new RSA(p, q, e);

            System.out.println();
            System.out.println("Enter your option: ");
			System.out.println("1.Encrypt. ");
            System.out.println("2.Decrypt. ");
            option = sc.nextInt();

            switch(option)
            {
                case 1: 
                    sc.nextLine();
                    System.out.println("Enter message to encrypt: "); 
                    plaint_text = sc.nextLine();
                    cipher = rsa.encrypt(rsa.toAscii(plaint_text.toUpperCase()));

                    System.out.println("The cipher is: ");
                    for(int i = 0; i < cipher.length; i ++)
                    {
                        System.out.print(cipher[i] + " ");
                    }
                    break;
                
                case 2:
                    System.out.println("Enter size of cipher: ");
                    int n = sc.nextInt();
                    System.out.println("Enter cipher to decrypt: "); 
                    cipher = new int[n];
                    for(int i = 0; i < n; i++)
                        cipher[i] = sc.nextInt();
                    
                    message = rsa.decrypt(cipher);

                    System.out.println("The message is: ");
                    for(int i = 0; i < message.length; i++)
                    {
                        System.out.print((char)message[i] + " ");
                    }
                    break;

                default:
                    break;
            }
            
            System.out.println();
            System.out.println("Would you like to continue? (y/n)");
            choice = sc.next().charAt(0);
			
        }while(choice == 'y');
    }
}
