import java.math.*;
import java.util.Arrays;

public class Knapsack {
    
    private int[] sik;
    private int[] message;
    private int n;
    private int m;

    public Knapsack()
    {

    }

    public Knapsack(int[] sik, int n, int m, int[] message)
    {
        this.n = n;
        this.m = m;
        this.sik = sik.clone();
        this.message = message.clone();
    }

    public int[] general_knapsack()
    {
        int[] public_key = new int[this.sik.length];
        for(int i = 0; i < this.sik.length; i++)
        {
            public_key[i] = (sik[i] * m) % n;
        }

        return public_key;
    }

    public int inverse_component()
    {
        BigInteger mod;
        mod = BigInteger.valueOf(this.m).modInverse(BigInteger.valueOf(this.n));

        return mod.intValue();
    }

    public int[][] split()
    {
        int k = 0;
        int[][] split = new int[this.sik.length][this.sik.length];

        for(int i = 0; i < this.sik.length; i++)
        {
           for(int j = 0; j < this.sik.length; j++)
           {
               split[i][j] = message[k];
               k++;
           }
        }

        return split;
   
    }

    public int[] get_cipher(int[] public_key)
    {
        int[][] split = split();
        int[] cipher = new int[public_key.length];
        int sum;

        for(int i = 0; i < split.length; i++)
        {
            sum = 0;
            for(int j = 0; j < split[i].length; j++)
            {
                sum = sum + ((split[i][j] == 1) ? public_key[j] : 0);
            }
            cipher[i] = sum;
        }

        return cipher;
    }

    public int[] decrypt(int[] cipher)
    {
        int[] message = new int[cipher.length];
        int inverse = inverse_component();
        for(int i = 0; i < cipher.length; i++)
        {
            message[i] = (cipher[i] * inverse) % n;
        }

        return message;
    }

    public int[][] verify(int[] decrypted)
    {
        int[][] binary = new int[this.sik.length][this.sik.length];
        int j, num;

        for(int i = 0; i < decrypted.length; i++)
        {            
            num = decrypted[i];
            while(num != 0)
            {
                for(j = this.sik.length - 1; j >= 0; j--)
                {                  
                    if(num >= this.sik[j])
                    {
                        num = num - this.sik[j];
                        binary[i][j] = 1;                        
                    }       
                    else
                        binary[i][j] = 0;                             
                }
            }
        }

        return binary;
    }
    



}
