import com.codeborne.selenide.*;
import io.qameta.allure.Step;

import java.util.*;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class HomePage {
    private static final SelenideElement secondaryHeader = $x("//div[@class='header_secondary_container']/span[@class='title']");
    private static final SelenideElement filter = $x("//select[@data-test='product_sort_container']");
    private static final SelenideElement basketButton = $x("//a[@class='shopping_cart_link']");
    private static final ElementsCollection cards = $$x("//div[@class='inventory_item']");
    private static final ElementsCollection imgs = $$x("//div[@class='inventory_item_img']//img");
    private static final ElementsCollection names = $$x("//div[@class='inventory_item_name']");
    private static final ElementsCollection descriptions = $$x("//div[@class='inventory_item_desc']");
    private static final ElementsCollection prices = $$x("//div[@class='inventory_item_price']");
    private static final ElementsCollection buttons = $$x("//div[@class='inventory_item']//button");
    public static int buttonsLength = buttons.size();
    public static List<Map<String, String>> cardList = new ArrayList<>();

    @Step("Проверяем переход на страницу")
    public HomePage checkLogin() {
        secondaryHeader.shouldHave(Condition.exactText("Products"));
        return this;
    }

    @Step("Проверяем наличие фильтра")
    public HomePage checkFilter() {
        filter.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем наличие корзины")
    public HomePage checkBasket() {
        basketButton.shouldBe(Condition.visible);
        return this;
    }

    @Step("Проверяем наличие счетчика в корзине")
    public HomePage checkBasketCounter() {
        basketButton.$x("./span[@class='shopping_cart_badge']").shouldBe(Condition.visible);
        return this;
    }

    public static int counter;

    @Step("Добавляем товар в корзину (из HomePage)")
    public HomePage addToBasketFromHomePage(int num) {
        System.out.println("444444444444 " + buttons.size());
        buttons.get(num).shouldBe(Condition.visible);
        if (Objects.requireNonNull(buttons.get(num).getAttribute("id")).contains("add-to-cart")) {
            System.out.println("22222222222 " + buttons.get(num));
            buttons.get(num).click();
            counter += 1;
        }
        return this;
    }

    @Step("Проверяем количество карточек (элементов в cards) на HomePage (ожидаем '{size}')")
    public HomePage checkProducts(int size) {
        cards.shouldHave(CollectionCondition.size(size));
        return this;
    }

    @Step("Сохраняем данные из карточек на HomePage")
    public HomePage saveCardsData() {
        for (int k = 0; k <= cards.size() - 1; k++) {
            Map<String, String> cardCell = new HashMap<>();

            cardCell.put("img", imgs.get(k).getAttribute("src"));
//            cardCell.put("img", "?????????");
            cardCell.put("name", names.get(k).getText());
            cardCell.put("description", descriptions.get(k).getText());
            cardCell.put("price", prices.get(k).getText());
            cardCell.put("button", buttons.get(k).getText());
//            System.out.println(cardCell);
            cardList.add(cardCell);

        }
        System.out.println(cardList);
        return this;
    }

    @Step("Проверяем наличие данных в полях карточек на HomePage")
    public boolean checkCardData(List<Map<String, String>> cardList) {
        boolean flag = true;
        for (int k = 0; k <= cards.size() - 1; k++) {
                if (cardList.get(k).get("img").isEmpty()) {
                    flag = false;
                    System.out.println("поле 'img' карточки " + k + " не заполнено");
                }
                if (cardList.get(k).get("name").isEmpty()) {
                    flag = false;
                    System.out.println("поле 'name' карточки " + k + " не заполнено");
                }
                if (cardList.get(k).get("description").isEmpty()) {
                    flag = false;
                    System.out.println("поле 'description' карточки " + k + " не заполнено");
                }
                if (cardList.get(k).get("price").isEmpty()) {
                    flag = false;
                    System.out.println("поле 'price' карточки " + k + " не заполнено");
                }
                if (cardList.get(k).get("button").isEmpty()) {
                    flag = false;
                    System.out.println("поле 'button' карточки " + k + " не заполнено");
                }
            }
        return flag;
    }


    public BasketPage clickBasket() {
        basketButton.shouldBe(Condition.visible).click();
    return new BasketPage();
    }

    public CardPage goToCard(int num) {
        names.get(num).shouldBe(Condition.visible).click();
        return new CardPage();
    }
}