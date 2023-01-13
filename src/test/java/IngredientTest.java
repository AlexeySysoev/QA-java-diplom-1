import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTest {
    @Parameterized.Parameter()
    public IngredientType type;
    @Parameterized.Parameter(1)
    public String name;
    @Parameterized.Parameter(2)
    public float price;

    @Parameterized.Parameters(name = "IngredientType: {0}, name: {1}, price: {2}")
    public static Object[][] testParams() {
        return new Object[][]{
                {FILLING, "chicken", 200},
                {SAUCE, "pepper", 30}
        };
    }

    @Test
    public void createdIngredientObjectContainWrightFields() {
        Ingredient ingredient = new Ingredient(type, name, price);
        Assert.assertTrue(ingredient.getType() == type &&
                ingredient.getName().equals(name) &&
                ingredient.getPrice() == price);
    }
}
