public class Decrypter
{
    Decrypter(){
    }

    public String decrypt(String input)
    {
        int i, val, digit;
        String decrypted  = "", ans = "";

        decrypted = unSwapDigits(input);

        for (i = 0; i < 4; i++)
        {
            digit = Integer.parseInt(String.valueOf(decrypted.charAt(i)));
            val = reverseModulusOperator(digit);
            ans += subtractSevenFromDigits (val);
        }

        return ans;
    }

    public String unSwapDigits(String in){
        String unSwappedDigits = "";

        unSwappedDigits += in.charAt(2);
        unSwappedDigits += in.charAt(3);
        unSwappedDigits += in.charAt(0);
        unSwappedDigits += in.charAt(1);

        return unSwappedDigits;
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

    public int subtractSevenFromDigits(int digit){
        int newDigit;
        if (digit >= 7)
        {
            digit -= 7;
            return digit;
        }
        else
        {
            System.out.println("An error occurred");
            return -1;
        }
    }
}
