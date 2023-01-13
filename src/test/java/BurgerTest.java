import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;
import tools.ExpReceipt;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    @Mock
    Ingredient ingredient;

    @Test
    //Проверяем, что метод addIngredient добавляет в объект в лист
    public void checkAddIngredientAddedNewIngredientToList() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.ingredients.size() != 0);
    }

    @Test
    //Проверяем, что метод addIngredient вызывается один раз
    public void checkAddIngredientAddedNewIngredientToListOneTime() {
        Burger burger = Mockito.mock(Burger.class);
        burger.addIngredient(ingredient);
        Mockito.verify(burger, Mockito.times(1)).addIngredient(ingredient);
    }

    @Test
    //Проверяем, что метод removeIngredient удаляет запись из листа
    public void checkRemoveIngredientRemoveDataFromList() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        int size = burger.ingredients.size();
        burger.removeIngredient(0);
        Assert.assertEquals(size - 1, burger.ingredients.size());
    }

    @Mock
    Ingredient ingredient1;
    Ingredient ingredient2;

    @Test
    //Проверка корректной работы moveIngredient
    public void checkMoveIngredientCorrectReplace() {
        Burger burger = new Burger();
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        Assert.assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Mock
    private Bun bun;

    @Test
    //Проверка корректности расчета цены со стабами
    public void checkGetPriceReturnWrightPrice() {
        float expPrice = 460f;
        Burger burger = new Burger();
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(130f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.getPrice() == expPrice);
    }

    @Mock
    Bun testBun;

    @Test
    //Проверка корректности вывода рецепта бургера
    public void checkGetReceiptContainAllIngredients1() {
        //Собрать бургер, сделать лист ингридиентов для проверки, сверить с результатом метода
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.setBuns(testBun);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("mustard");
        Mockito.when(testBun.getName()).thenReturn("test bun");
        //Делаем пецепт ожидаемого бургера
        List<String> testIngredients = new ArrayList<>();
        testIngredients.add("test bun");
        testIngredients.add("mustard");
        ExpReceipt expBurger = new ExpReceipt();
        Assert.assertTrue(expBurger.buildExpReceipt(testIngredients, 0).equals(burger.getReceipt()));
    }
}
