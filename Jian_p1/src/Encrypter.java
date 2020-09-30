public class Encrypter
{
    Encrypter(){
    }

    public String encrypt(String input)
    {
        int i, value;
        String encrypted  = "", ans = "";

        for (i = 0; i < 4; i++)
        {
            value = Integer.parseInt(String.valueOf(input.charAt(i)));
            encrypted += encryptDigits(value);
        }

        ans = switchDigits(encrypted);
        return ans;
    }

    public int encryptDigits (int value)
    {
        int digit;
        digit = (value + 7) % 10;
        return digit;
    }

    public String switchDigits(String in){
        String swappedDigits = "";

        swappedDigits += in.charAt(2);
        swappedDigits += in.charAt(3);
        swappedDigits += in.charAt(0);
        swappedDigits += in.charAt(1);

        return swappedDigits;
    }

}
