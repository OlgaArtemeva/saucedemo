import com.codeborne.selenide.Condition;
import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class HomeTest extends BaseTest {
//    private Faker faker = new Faker();

    private Random random = new Random();

    @BeforeMethod
    public void login() {
        new LoginPage()
                .setLogin("standard_user")
                .setPassword("secret_sauce")
                .login();
    }

    @Test(testName = "Название раздела -> Products")
    public void checkCloseError() {
        new HomePage()
                .checkLogin();
    }

    @Test(testName = "Наличие фильтра")
    public void checkFilter() {
        new HomePage()
                .checkFilter();
    }

    @Test(testName = "Наличие кнопки открытия корзины")
    public void checkBasket() {
        new HomePage()
                .checkBasket();
    }

    @Test(testName = "Проверяем наличие счетчика в корзине после добавления товара из HomePage")
    public void checkBasketCounter() {
        new HomePage()
//                .addToBasketFromHomePage(faker.number().numberBetween(0, HomePage.buttonsLength - 1))
                .addToBasketFromHomePage(random.nextInt(HomePage.buttonsLength))
                .checkBasketCounter();
    }

    @Test(testName = "Наличие карточек с товарами (элементов в cards) на HomePage (ожидаем  6)")
    public void checkCardsSize() {
        new HomePage()
                .checkProducts(6);
    }

    @Test(testName = "Наличие названия товара, описания, изображения, цены, кнопки")
    public void checkCardFields() {
        Assert.assertTrue(new HomePage().saveCardsData().checkCardData(HomePage.cardList)); //??????????
    }

    @Test(testName = "Проверка добавления товара в корзину из HomePage")
    public void addToBasketFromHomePage() {
        int numCard = random.nextInt(HomePage.buttonsLength);
        new HomePage()
                .saveCardsData()
                .addToBasketFromHomePage(numCard)
                .clickBasket()
                .checkCardInBasket(numCard)
                .checkQuantityInBasket(1);
    }

    @Test(testName = "Проверка добавления товара в корзину из  карточки товара (CardTest))")
    public void addToBasketFromCardPage() {
        int numCard = random.nextInt(HomePage.buttonsLength);
        new HomePage()
                .saveCardsData()
                .goToCard(numCard)
                .checkCardInCardPage(numCard)
                .addToBasketFromCardPage()
                .clickBasketFromCardPage()
                .checkCardInBasket(numCard)
                .checkQuantityInBasket(1);
    }

    @Test(testName = "Проверка удаления товара из корзины")
    public void dellFromBasket() {
        int numCard = random.nextInt(HomePage.buttonsLength);
        new HomePage()
                .saveCardsData()
                .addToBasketFromHomePage(numCard)
                .clickBasket()
                .checkCardInBasket(numCard)
                .checkQuantityInBasket(1)
                .dellCardFromBasket(0)
                .isBasketEmpty();
    }

    @Test(testName = "Проверка изменения значения счетчика при добавлении/удалении товара")
    public void checkCounterInBasket() {
//        int numCard = random.nextInt(HomePage.buttonsLength);
        int numberOfCard = random.nextInt(HomePage.buttonsLength) + 1;
        System.out.println(numberOfCard + "!!!!!!!!!!");
        HomePage.counter = 1;
        new HomePage()
                .saveCardsData();
        // добавляем товары в корзину // и не проверяем их там
        for (; HomePage.counter <= numberOfCard; ) {
            int numCard = random.nextInt(HomePage.buttonsLength);
            new HomePage()
                    .addToBasketFromHomePage(numCard)
                    .clickBasket();
//                    .checkQuantityInBasket(HomePage.counter-1);
//                    .checkCardInBasket(numCard);    // надо переделать
        }

        // проверяем счетчик корзины
        System.out.println(HomePage.counter + "!!!!!!!!!!");
        new BasketPage()
                .checkQuantityInBasket(numberOfCard);

        // удаляем товары
        for (int i = numberOfCard-1; i >= 0; i--) {

            new BasketPage()
                    .dellCardFromBasket(i)      // удаляет произвольную card
                    .checkQuantityInBasket(i);
            HomePage.counter -= 1;
        }

        if (HomePage.counter == 0) {
            new BasketPage()
                    .isBasketEmpty();
        }
    }
}
