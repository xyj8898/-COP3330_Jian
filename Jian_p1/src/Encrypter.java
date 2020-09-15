public class Encrypter
{
    public String encrypt(String input)
    {
        int i;
        char temp;
        String encrypted  = "", ans = "";

        for (i = 0; i < 4; i++)
        {
            encrypted += encryptDigits(input.charAt(i));
        }

        ans += encrypted.charAt(2);
        ans += encrypted.charAt(3);
        ans += encrypted.charAt(0);
        ans += encrypted.charAt(1);

        return ans;
    }

    public int encryptDigits (int value)
    {
        int i;

        value = ((value + 7) % 10);

        return value;
    }

}
