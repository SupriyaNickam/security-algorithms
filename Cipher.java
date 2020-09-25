
import java.util.*;

public class Cipher
{
    public static void main(String[] args) 
    {
        int choice = 1, option;
        Scanner sc = new Scanner(System.in);
        
        do
        {
            System.out.println();
            System.out.println("Enter your option: ");
            System.out.println("1. Substitution ");
            System.out.println("2. Double transposition ");
            System.out.println("3. One-Time pad ");
            System.out.println("4. Codebook ");
            System.out.println("5. Exit ");
            System.out.println();

            option = sc.nextInt();

            switch(option)
            {
                case 1: substitution();
                    break;
                case 2: transposition();
                    break;
                case 3: one_time_pad();
                    break;
                case 4: codebook();
                    break;
                default:
                    choice = 0;
            }

        } while(choice == 1);

        sc.close();
    }

    public static void substitution()
    {
        Scanner sc = new Scanner(System.in);
        String plain_text = "";
        StringBuilder cipher_text = new StringBuilder();
        int i, j;

        char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] key = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};
        
        System.out.println("Enter your plaintext: ");
        plain_text = sc.nextLine();
        
        for (i = 0; i < plain_text.length(); i++)
        {
            j = 0;
            while (j < alphabets.length)
            {
                if( plain_text.charAt(i) == alphabets[j])                   //Compare the character in plaintext to the alphabet array
                {
                    cipher_text.append(key[j]);                             //Substitute corresponding character and build string
                    break;
                }
                j++;
            }
        }

        System.out.println("The ciper text is: "+ cipher_text);
        sc.close();
        
    }

    public static void transposition()
    {
        Scanner sc = new Scanner(System.in);
        String plain_text = "";
        int i, j, k = 0;
        char[][] initial_matrix = new char[4][4];
        char[][] column_transpose = new char[4][4];
        char[][] cipher_matrix = new char[4][4];

        int[] column_key = {2, 0, 3, 1};
        int[] row_key = {3, 1, 2, 0};

        System.out.println("Enter your plaintext (less than 12 characters):");          //Because it's a 4 x 4 matrix
        plain_text = sc.nextLine();

        if(plain_text.length() > 16)
            System.out.println("Invalid input.");
        else
        {
            for( i = 0; i < 4; i++)
            {
                for(j= 0; j < 4; j++)
                {
                    initial_matrix[i][j] = plain_text.charAt(k);                        //Arrange plaintext in 4 x 4 format
                    k++;
                }
            }

        /*  for( i = 0; i < 4; i++)
            {
                for(j= 0; j < 4; j++)
                {
                    System.out.print(initial_matrix[i][j]);
                }
                System.out.print(" ");
            }
        */

            for( i = 0; i < 4; i++)
            {
                for(j= 0; j < 4; j++)
                {
                    column_transpose[i][j] = initial_matrix[i][column_key[j]];         //transpose with respect to columns (1)
                }
            }

        /*  for( i = 0; i < 4; i++)
            {
                for(j= 0; j < 4; j++)
                {
                    System.out.print(column_transpose[i][j]);
                }
                System.out.print(" ");
            }
        */

            for( i = 0; i < 4; i++)
            {
                for(j= 0; j < 4; j++)
                {
                    cipher_matrix[i][j] = column_transpose[row_key[i]][j];            //transpose with respect to rows (2)
                }
            }

            System.out.println("The cipher is: ");
            for( i = 0; i < 4; i++)
            {
                for(j= 0; j < 4; j++)
                {
                    System.out.print(cipher_matrix[i][j]);
                }
                System.out.print(" ");
            }
        }

        sc.close();

    }

    public static void one_time_pad()
    {
        Scanner sc = new Scanner(System.in);
        String plain_text = "";
        StringBuilder cipher_text = new StringBuilder();
        int i,j;

        char[] alphabets = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        char[] key = {'Q','W','E','R','T','Y','U','I','O','P','A','S','D','F','G','H','J','K','L','Z','X','C','V','B','N','M'};

        System.out.println("Enter your plaintext: ");
        plain_text = sc.nextLine();

        for (i = 0; i < plain_text.length(); i++)
        {
            j = 0;
            while (j < alphabets.length)
            {
                if( plain_text.charAt(i) == alphabets[j])
                {
                    cipher_text.append(" "+(alphabets[j] ^ key[j]));                    //plain text is xor-ed with key and appended to cipher
                    break;
                }
                j++;
            }
        }

        System.out.println("The ciper text is: "+ cipher_text);
        sc.close();
    }

    public static void codebook()
    {
        Hashtable<String, Integer> codebook = new Hashtable<String, Integer>(); 
        StringTokenizer st;
        String plain_text = " ";
        StringBuilder cipher_text = new StringBuilder();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your plaintext: ");
        plain_text = sc.nextLine();
        st = new StringTokenizer(plain_text);

        codebook.put("north", 1171);
        codebook.put("south", 4487);
        codebook.put("gate", 2020);
        codebook.put("wall", 0320);
        codebook.put("meet", 1971);
        codebook.put("noon", 6008);
        codebook.put("at", 5881);

        while(st.hasMoreTokens())
        {
            cipher_text.append(" "+codebook.get(st.nextToken()));       //Fetch value from key-value pair in hash table and appended
        }

        System.out.println("Cipher text is: "+cipher_text);
        sc.close();

    }
}