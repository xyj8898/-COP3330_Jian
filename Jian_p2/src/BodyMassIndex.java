public class BodyMassIndex {
    double bmiNumber;
    String category = "";
    public BodyMassIndex(double height, double weight) {
        this.bmiNumber = calculateBodyMassIndex(height, weight);
        this.category = bmiCategory(bmiNumber);
    }

    public static double calculateBodyMassIndex(double height, double weight) {
        double BMI;
        BMI = (703.0 * weight) / (height * height);
        return BMI;
    }

    public static String bmiCategory(double bmi){
        String category = "";
        if (bmi < 18.5)
        {
            category = "Underweight";
        }
        else if (bmi <= 24.9)
        {
            category = "Normal weight";
        }
        else if (bmi >= 25)
        {
            if (bmi <= 29.9)
                category= "Overweight";
        }
        else
        {
            category = "Obese";
        }
        return category;
    }

}
