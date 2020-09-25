
import java.util.stream.IntStream;

public class RC4
{
    private int[] plaintext;
    private int n;
    private int bit;

    public RC4(int[] plaintext, int n, int bit)
    {
        this.plaintext = plaintext;
        this.n = n;
        this.bit = bit;
    }
    
    public int[] get_cipher()
    {
        int[] cipher = new int[this.n];
        int[] S = IntStream.range(0, this.bit).toArray();
        int[] K = {1, 2, 3, 6};
        int[] T = new int[this.bit];

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

        return cipher;

    }

   
}