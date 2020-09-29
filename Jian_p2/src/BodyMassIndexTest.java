import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

class BodyMassIndexTest {
    private BodyMassIndex bodyMassIndex;

    @BeforeEach
    public void init(){
        bodyMassIndex = new BodyMassIndex(62.0, 102.8);
    }

    @Test
    public void testCalculateBodyMassIndex() {
        double bmi = bodyMassIndex.calculateBodyMassIndex(62.0, 102.8);
        BigDecimal bmiToTheTenthsPlace = new BigDecimal(bmi).setScale(1, RoundingMode.HALF_EVEN);
        bmi = bmiToTheTenthsPlace.doubleValue();
        assertEquals(18.8, bmi);
    }

    @Test
    public void testBodyMassIndexBMICategoryUnderweight() {
        double height = 62.0;
        double weight = 98.2;
        assertEquals("Underweight", bodyMassIndex.bmiCategory(height, weight));
    }

    @Test
    public void testBodyMassIndexBMICategoryNormalWeightLowerBound() {
        double height = 62.5;
        double weight = 102.8;
        assertEquals("Normal weight", bodyMassIndex.bmiCategory(height, weight));
    }

    @Test
    public void testBodyMassIndexBMICategoryNormalWeightUpperBound() {
        double height = 63.3;
        double weight = 142;
        assertEquals("Normal weight", bodyMassIndex.bmiCategory(height, weight));
    }

    @Test
    public void testBodyMassIndexBMICategoryOverweightLowerBound() {
        double height = 77.2;
        double weight = 212;
        assertEquals("Overweight", bodyMassIndex.bmiCategory(height, weight));
    }

    @Test
    public void testBodyMassIndexBMICategoryOverweightUpperBound() {
        double height = 49.6;
        double weight = 104.8;
        assertEquals("Overweight", bodyMassIndex.bmiCategory(height, weight));
    }

    @Test
    public void testBodyMassIndexBMICategoryObese() {
        double height = 52.8;
        double weight = 119;
        assertEquals("Obese", bodyMassIndex.bmiCategory(height, weight));
    }
}