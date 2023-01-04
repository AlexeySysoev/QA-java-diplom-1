import org.junit.Assert;
import org.junit.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;

public class IngredientTest {
    @Test
    public void getPriceReturnValidPrice(){
        float price = 200f;
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "chicken",200);
        Assert.assertTrue(price == ingredient.getPrice());
    }
    @Test
    public void getNameReturnValidName(){
        String name = "fish";
        Ingredient ingredient = new Ingredient(IngredientType.FILLING,"fish",150);
        Assert.assertTrue(ingredient.getName().equals(name));
    }
    @Test
    public void getTypeReturnValidIngredientType(){
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "pepper", 30);
        Assert.assertEquals(IngredientType.SAUCE,ingredient.getType());
    }
}
