import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.util.Objects;

public class Card_ {
//    ???????? нужен ??????????
    private String name;
    private String description;
    private String price;

    private String imageSrc;
    private SelenideElement button;

    public Card_(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImageSrc() {return imageSrc;}

    public SelenideElement getButton() {
        return button;
    }

    @Step("Добавляем товар в корзину (из CardPage)")
    public Card_ addToBasket(){
        this.button.shouldBe(Condition.visible);
        if(Objects.requireNonNull(this.button.getAttribute("id")).contains("add-to-cart")){
            this.button.click();
        }
        return this;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}