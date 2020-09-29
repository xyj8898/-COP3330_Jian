import java.util.ArrayList;
import java.util.Scanner;

public class App {
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
        Scanner in =  new Scanner(System.in);
        System.out.println("Do you have more input? Please press Y for Yes and N for No.");
        return in.next().equals("Y");
    }

    public static double getUserHeight(){
      Scanner in = new Scanner(System.in);
      double inches;
      System.out.println("Please enter your height in inches: ");
      inches = in.nextDouble();
      in.nextLine();
      while (inches < 0)
      {
          System.out.println("You've entered a negative number, please enter your height in inches: ");
          inches = in.nextDouble();
          in.nextLine();
      }
      return inches;
    }

    public static double getUserWeight(){
        Scanner in = new Scanner(System.in);
        double pounds;
        System.out.println("Please enter your weight in pounds: ");
        pounds = in.nextDouble();
        in.nextLine();
        while (pounds < 0)
        {
            System.out.println("You've entered a negative number, please enter your weight in pounds: ");
            pounds = in.nextDouble();
            in.nextLine();
        }
        return pounds;
    }

    public static void displayBmiInfo(BodyMassIndex bmi){
        System.out.printf("User's BMI info: %.1f and category %s\n", bmi.bmiNumber, bmi.category);
    }

    public static void displayBmiStatistics(ArrayList<BodyMassIndex> bmiData){
        int i, length = bmiData.size();
        double average, sum = 0;
        for (i = 0; i < length; i++)
        {
            //sum += (Double) bmiData.get(i);
        }
        average = sum/length;
        System.out.println("Average BMI: " + average);
    }
}

