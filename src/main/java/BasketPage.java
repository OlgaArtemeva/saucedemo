import com.codeborne.selenide.Condition;
//import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.*;
//import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class BasketPage {
    private final SelenideElement basketButtonQuantity = $x("//a[@class='shopping_cart_link']//span[@class='shopping_cart_badge']");
    private final SelenideElement cardQuantity = $x("//div[@class='cart_quantity']");
    private final SelenideElement name = $x("//div[@class='inventory_item_name']");
    private final SelenideElement description = $x("//div[@class='inventory_item_desc']");
    private final SelenideElement price = $x("//div[@class='inventory_item_price']");
    //    private ElementsCollection buttons = $$x("//div[@class='item_pricebar']//button");
    private static final ElementsCollection buttons = $$x("//div[@class='cart_item']//button");
    private final SelenideElement card = $x("//div[@class='cart_item']");

    @Step("Проверяем данные товара в корзине")
    public BasketPage checkCardInBasket(int num) {
//        basketButtonQuantity.shouldHave(Condition.exactText(Integer.toString(number)));
        cardQuantity.shouldHave(Condition.exactText("1"));
        name.shouldHave(Condition.exactText(HomePage.cardList.get(num).get("name")));
        description.shouldHave(Condition.exactText(HomePage.cardList.get(num).get("description")));
        price.shouldHave(Condition.exactText(HomePage.cardList.get(num).get("price")));
        buttons.get(0).shouldBe(Condition.visible);
        buttons.get(0).shouldBe(Condition.exactText("Remove"));
        return this;
    }

    public BasketPage checkQuantityInBasket(int number) {
        basketButtonQuantity.shouldHave(Condition.exactText(Integer.toString(number)));
        return this;
    }

    public BasketPage dellCardFromBasket(int i) {
//        System.out.println("111111111"+ buttons.get(i));
//        buttons.get(i).shouldBe(Condition.visible);
//        buttons.get(i).click();
        System.out.println("33333333333 " + buttons.size());
        buttons.get(i).shouldBe(Condition.visible);
        if (Objects.requireNonNull(buttons.get(i).getAttribute("id")).contains("remove-")) {
            System.out.println("111111111 " + buttons.get(i));
            buttons.get(i).click();
        }
        return this;
    }


    public BasketPage isBasketEmpty() {
        card.shouldNotBe(Condition.visible);
        basketButtonQuantity.shouldNotBe(Condition.visible);
        return this;
    }

}
