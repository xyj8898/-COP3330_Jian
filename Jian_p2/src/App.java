import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static Scanner in =  new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<BodyMassIndex> bmiData = new ArrayList<BodyMassIndex>();

        while (moreInput()) {
            double height = getUserHeight();
            double weight = getUserWeight();

            BodyMassIndex bmi = new BodyMassIndex(height, weight);
            bmiData.add(bmi);

            displayBmiInfo(bmi);
        }

        displayBmiStatistics(bmiData);
    }

    public static boolean moreInput(){
        char response;
        System.out.println("Do you have more input? Please press Y for Yes or a N for No.");
        response = in.next().charAt(0);

        if (!Character.isLetter(response))
        {
            System.out.println("Letters only, please either enter a Y for Yes or a N for No");
            response = in.next().charAt(0);
        }
        response = Character.toUpperCase(response);

        return response == 'Y';
    }

    public static double getUserHeight(){
      double inches;
      System.out.println("Please enter your height in inches: ");
      inches = in.nextDouble();
      in.nextLine();
      while (inches < 0)
      {
          System.out.println("Positive numbers only, please enter your height in inches: ");
          inches = in.nextDouble();
          in.nextLine();
      }
      return inches;
    }

    public static double getUserWeight(){
        double pounds;
        System.out.println("Please enter your weight in pounds: ");
        pounds = in.nextDouble();
        in.nextLine();
        while (pounds < 0)
        {
            System.out.println("Positive numbers only, please enter your weight in pounds: ");
            pounds = in.nextDouble();
            in.nextLine();
        }
        return pounds;
    }

    public static void displayBmiInfo(BodyMassIndex bmi){
        System.out.printf("User's BMI info: %.1f and BMI Category: %s\n", bmi.bmiNumber, bmi.category);
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        int i, length = bmiData.size();
        double average, sum = 0;
        Object[] arrayOfBMIs = bmiData.toArray();

       for (i = 0; i < length; i++)
        {
            sum += bmiData.get(i).bmiNumber;
        }

        average = sum/length;
        System.out.printf("Average BMI: %.1f\n", average);
    }
}
