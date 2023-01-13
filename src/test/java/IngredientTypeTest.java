import org.junit.Assert;
import org.junit.Test;
import praktikum.IngredientType;

public class IngredientTypeTest {
    @Test
    public void selectSauceIngredientFromEnumIsCorrect() {
        Assert.assertEquals(IngredientType.valueOf("SAUCE"), IngredientType.SAUCE);
    }

    @Test
    public void selectFillingIngredientFromEnumIsCorrect() {
        Assert.assertEquals(IngredientType.valueOf("FILLING"), IngredientType.FILLING);
    }
}
