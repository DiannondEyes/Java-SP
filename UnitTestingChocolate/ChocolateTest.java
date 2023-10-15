import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ChocolateTest {

    @Test
    public void testCalculateMaxChocolates() {
        int money = 30;     // Имеющиеся деньги
        int price = 5;      // Цена за шоколадку
        int wrap = 3;       // Количество обёрток для бонусной шоколадки

        int result = Chocolate.calculateMaxChocolates(money, price, wrap);

        assertEquals(8, result);
    }
}
