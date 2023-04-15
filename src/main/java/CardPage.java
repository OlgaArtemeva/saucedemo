import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.$x;

public class CardPage {
    private SelenideElement img = $x("//div[@class='inventory_details']//img");
    private SelenideElement name = $x("//div[@class='inventory_details_desc_container']//div[contains(@class, 'inventory_details_name')]");
    private SelenideElement description = $x("//div[@class='inventory_details_desc_container']//div[contains(@class, 'inventory_details_desc')]");
    private SelenideElement price = $x("//div[@class='inventory_details_desc_container']//div[contains(@class, 'inventory_details_price')]");
    private SelenideElement button = $x("//div[@class='inventory_details']//button");
    private static final SelenideElement basketButton = $x("//a[@class='shopping_cart_link']");

    public CardPage checkCardInCardPage(int numCard) {
        System.out.println("!!!!!!!! " + img.shouldHave(Condition.attribute("src", HomePage.cardList.get(numCard).get("img"))));
        img.shouldHave(Condition.attribute("src", HomePage.cardList.get(numCard).get("img")));
//        img.getAttribute("src").shouldHave(Condition.exactText(HomePage.cardList.get(numCard).get("img")));
        name.shouldHave(Condition.exactText(HomePage.cardList.get(numCard).get("name")));
        description.shouldHave(Condition.exactText(HomePage.cardList.get(numCard).get("description")));
        price.shouldHave(Condition.exactText(HomePage.cardList.get(numCard).get("price")));
        button.shouldBe(Condition.visible);
        button.shouldBe(Condition.exactText("Add to cart"));
        return this;
    }

    public CardPage addToBasketFromCardPage() {
        button.shouldBe(Condition.visible);
        if (Objects.requireNonNull(button.getAttribute("id")).contains("add-to-cart")) {
            button.click();
        }
        return this;
    }

    public BasketPage clickBasketFromCardPage() {
        basketButton.shouldBe(Condition.visible).click();
        return new BasketPage();
    }


}
