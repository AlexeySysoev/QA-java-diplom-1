import org.junit.Assert;
import org.junit.Test;
import praktikum.Bun;

public class BunTest {
    //проверить  метод getName
    @Test
    public void checkGetNameReturnWrightName() {
        Bun bun = new Bun("tasty", 245.123123f);
        Assert.assertEquals("tasty", bun.getName());
    }

    //проверить метод getPrice
    @Test
    public void checkGetPriceReturnWrightPrice() {
        Bun bun = new Bun("any", 15.123f);
        Assert.assertEquals(15.123f, bun.getPrice(), 0.00001);
    }
}
