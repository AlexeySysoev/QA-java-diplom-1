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

import java.util.ArrayList;
import java.util.List;
@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    //setBuns ?? как это проверять и надо ли?
    //addIngredient - Мокать Ingredient?
    //removeIngredient - проверить лист ingredients на удаление итема
    //moveIngredient - проверить ingredients на смену индекса
    //getPrice - проверить расчет цены (возможные моки - bun и ingredient)
    //getReceipt - проверить правильность рецепта (возможные моки - bun и ingredient)
//    @Mock
//    Ingredient ingredient;
    @Test
    //Проверяем, что метод addIngredient добавляет в лист правильный объект
    public void checkAddIngredientAddedNewIngredientToList(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"mustard", 100);
        burger.addIngredient(ingredient);
        List<Ingredient> expIngredient = new ArrayList<>();
        expIngredient.add(ingredient);
        Assert.assertEquals(expIngredient,burger.ingredients);
    }

    @Test
    //Проверяем, что метод addIngredient вызывается один раз
    public void checkAddIngredientAddedNewIngredientToListOneTime(){
        Burger burger = Mockito.mock(Burger.class);
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"mustard", 100);
        burger.addIngredient(ingredient);
        Mockito.verify(burger, Mockito.times(1)).addIngredient(ingredient);
    }

    @Test
    //Проверяем, что метод removeIngredient удаляет запись из листа
    public void checkRemoveIngredientRemoveDataFromList(){
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"mustard", 100);
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
    @Test
    //Проверка корректности расчета цены
    public void checkGetPriceReturnWrightPrice(){
        Burger burger = new Burger();
        //Bun bun = Mockito.mock(Bun.class);
        //Mockito.when(bun.getPrice()).thenReturn(200F);
        Bun bun = new Bun("test bun",100F);
        burger.setBuns(bun);
        Ingredient ingredient1 = new Ingredient(IngredientType.SAUCE,"mustard", 100);
        Ingredient ingredient2 = new Ingredient(IngredientType.FILLING,"cheese", 150);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.setBuns(bun);
        Assert.assertTrue(burger.getPrice()==450);
    }
    @Test
    //Проверка правильной сборки бургера
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
        burger.addIngredient(new Ingredient(IngredientType.SAUCE, testIngrs.get(2),price));
        burger.setBuns(bun);
        System.out.println(burger.getReceipt());
        StringBuilder expectedReceipt = new StringBuilder(String.format("(==== %s ====)%n", testIngrs.get(0)));
        expectedReceipt.append(String.format("= %s %s =%n", "sauce",
                testIngrs.get(1)));
        expectedReceipt.append(String.format("= %s %s =%n", "sauce",
                testIngrs.get(2)));
        expectedReceipt.append(String.format("(==== %s ====)%n", testIngrs.get(0)));
        expectedReceipt.append(String.format("%nPrice: %f%n", (price*4)));
        System.out.println(expectedReceipt);
        Assert.assertTrue(expectedReceipt.toString().equals(burger.getReceipt()));

    }
}