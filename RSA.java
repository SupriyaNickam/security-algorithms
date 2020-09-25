import java.math.*;

public class RSA
{
    private BigInteger p;
    private BigInteger q;
    private BigInteger e;
    private BigInteger n;
    private BigInteger v;
    private BigInteger d;
    private BigInteger one = new BigInteger("1");

    public RSA()
    {

    }

    public RSA(BigInteger p, BigInteger q, BigInteger e)
    {
        this.p = new BigInteger(String.valueOf(p));
        this.q = new BigInteger(String.valueOf(q));
        this.e = new BigInteger(String.valueOf(e));
        this.n = p.multiply(q);
        this.v = (p.subtract(one)).multiply (q.subtract(one));
        this.d = e.modInverse(v);
    }

    public int[] toAscii(String text)
    {
        int[] message = new int[text.length()];
        int i;

        for( i = 0; i < text.length(); i++)
        {
            message[i] = text.charAt(i);
        }

        return message;
    }


    public int[] encrypt (int[] message)
    {
        int[] cipher = new int[message.length];
        int i;

        for( i = 0; i < message.length; i++)
        {
            cipher[i] = ((int) Math.pow(message[i], this.e.intValue())) % this.n.intValue();
        }

        return cipher;
    }

    public int[] decrypt (int[] cipher)
    {
        int[] message = new int[cipher.length];
        int i;

        for(i = 0; i < cipher.length; i++)
        {
            BigInteger power =  BigInteger.valueOf(cipher[i]).pow(this.d.intValue());
            message[i] = power.mod(this.n).intValue();
        }

        return message;
    }

}