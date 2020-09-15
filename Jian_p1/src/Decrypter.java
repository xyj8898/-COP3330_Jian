public class Decrypter
{
    public String decrypt(String input)
    {
        int i, val;
        char temp;
        String decrypted  = "", ans = "";

        decrypted += input.charAt(2);
        decrypted += input.charAt(3);
        decrypted += input.charAt(0);
        decrypted += input.charAt(1);

        for (i = 0; i < 4; i++)
        {
            val = reverseModulusOperator(decrypted.charAt(i));
            if (val > 0)
            {
                val -= 7;
                ans += val;
            }
            else
                System.out.println("An error occurred");
        }

        return ans;
    }

    public int reverseModulusOperator(int value)
    {
        if (value == 0)
            return 10;
        if (value == 1)
            return 11;
        if (value == 2)
            return 12;
        if (value == 3)
            return 13;
        if (value == 4)
            return 14;
        if (value == 5)
            return 15;
        if (value == 6)
            return 16;
        if (value == 7)
            return 7;
        if (value == 8)
            return 8;
        if (value == 9)
            return 9;
        return -1;
    }
}
