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
    public void checkAddIngredientAddedNewIngredientToList(){
        Burger burger = new Burger();
        Ingredient ingredient = Mockito.mock(Ingredient.class);
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.ingredients.size()!=0);
    }
    @Test
    //Проверяем, что метод addIngredient вызывается один раз
    public void checkAddIngredientAddedNewIngredientToListOneTime(){
        Burger burger = Mockito.mock(Burger.class);
        burger.addIngredient(ingredient);
        Mockito.verify(burger, Mockito.times(1)).addIngredient(ingredient);
    }
    @Test
    //Проверяем, что метод removeIngredient удаляет запись из листа
    public void checkRemoveIngredientRemoveDataFromList(){
        Burger burger = new Burger();
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        int size = burger.ingredients.size();
        burger.removeIngredient(0);
        Assert.assertEquals(size-1, burger.ingredients.size());
    }
    @Test
    //Проверка корректной работы moveIngredient
    public void checkMoveIngredientCorrectReplace(){
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE,"mustard", 100);
        Ingredient ingredient2 = new Ingredient(IngredientType.SAUCE,"hot pepper", 50);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        String expected = burger.ingredients.get(0).getName();
        burger.moveIngredient(0,1);
        Assert.assertEquals(expected, burger.ingredients.get(1).getName());
    }
    @Mock
    private Bun bun;
    @Test
    //Проверка корректности расчета цены со стабами
    public void checkGetPriceReturnWrightPrice(){
        float expPrice = 460f;
        Burger burger = new Burger();
        Mockito.when(bun.getPrice()).thenReturn(100f);
        Mockito.when(ingredient.getPrice()).thenReturn(130f);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient);
        Assert.assertTrue(burger.getPrice()==expPrice);
    }
    @Test
    //Проверка корректности вывода рецепта бургера
    public void checkGetReceiptContainAllIngredients(){
        //Собрать бургер, сделать лист ингридиентов для проверки, сверить с результатом метода
        float price = 100;
        List<String> testIngrs = new ArrayList<>();
        testIngrs.add("test bun");
        testIngrs.add("test mustard");
        testIngrs.add("test cheese");
        Burger burger = new Burger();
        Bun bun = new Bun(testIngrs.get(0),price);
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, testIngrs.get(1),price));
        burger.addIngredient(new Ingredient(IngredientType.FILLING, testIngrs.get(2),price));
        burger.setBuns(bun);
        //Делаем пецепт ожидаемого бургера
        ExpReceipt expBurger = new ExpReceipt();
        Assert.assertTrue(expBurger.buildExpReceipt(testIngrs, price).equals(burger.getReceipt()));
    }
}
/*
Мокайте методы бургера через стабы.
Не мокайте методы других классов через моки
(сколько раз метод был вызван, какого типа аргументы передаются и тд)
* */