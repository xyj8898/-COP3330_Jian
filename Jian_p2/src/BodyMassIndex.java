
public class BodyMassIndex {
    double bmiNumber;
    String category;

    public BodyMassIndex(double height, double weight) {
        this.bmiNumber = calculateBodyMassIndex(height, weight);
        this.category = bmiCategory(height, weight);
    }

    public double calculateBodyMassIndex(double height, double weight) {
        double BMI;
        BMI = (703.0 * weight) / (height * height);
        return BMI;
    }

    public String bmiCategory(double height, double weight){
        String categoryForBMI;
        double bmi = calculateBodyMassIndex(height, weight);

        if (bmi < 18.5)
        {
            categoryForBMI = "Underweight";
        }
        else if (bmi <= 24.9)
        {
            categoryForBMI = "Normal weight";
        }
        else if (bmi >= 25 && bmi <= 29.9)
        {
            categoryForBMI = "Overweight";
        }
        else
        {
            categoryForBMI = "Obese";
        }
        return categoryForBMI;
    }
}
