import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalcTest {

    @Test
    public void testCalculateExpression1() {
        double result = Calculator.calculateExpression1(2.0);
        assertEquals(11.0, result, 0.001);
    }

    @Test
    public void testCalculateExpression2() {
        double result = Calculator.calculateExpression2(4.0, 2.0);
        assertEquals(3.0, result, 0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateExpression2WithZeroDenominator() {
        Calculator.calculateExpression2(3.0, 3.0); 
    }

    @Test
    public void testCalculateExpression3() {
        double result = Calculator.calculateExpression3(4.0, 3.0, 2.0);
        assertEquals(720.0, result, 0.001); 
    }

    @Test
    public void testCalculateExpression3WithZeroA() {
        double result = Calculator.calculateExpression3(0.0, 3.0, 2.0);
        assertEquals(1.0, result, 0.001); 
    }

    @Test
    public void testCalculateExpression3WithZeroX() {
        double result = Calculator.calculateExpression3(4.0, 0.0, 2.0);
        assertEquals(1.0, result, 0.001); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateExpression3WithZeroB() {
        Calculator.calculateExpression3(4.0, 3.0, 0.0);
    }
}
