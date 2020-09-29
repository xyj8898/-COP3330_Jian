import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {
    @Test
    public void testCalculateBodyMassIndex() {
        BodyMassIndex c = new BodyMassIndex(62.0, 104.8);
        assertEquals(19.1, c.calculateBodyMassIndex(62.0, 104.8));
    }

    @Test
    public void testBodyMassIndexBMICategory() {
        double bmi = 18.1;
        BodyMassIndex c = new BodyMassIndex(62.0, 104.8);
        assertEquals("Underweight", c.bmiCategory(bmi));
        bmi = 19.1;
        assertEquals("Normal weight", c.bmiCategory(bmi));
        bmi = 24.9;
        assertEquals("Normal weight", c.bmiCategory(bmi));
        bmi = 25;
        assertEquals("Overweight", c.bmiCategory(bmi));
        bmi = 29.9;
        assertEquals("Overweight", c.bmiCategory(bmi));
        bmi = 32;
        assertEquals("Obese", c.bmiCategory(bmi));
    }
}