package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.switchTo;

public class DzenPage {

    private final SelenideElement
            dzenSearchText = $("[data-testid='rotator-text']");

    @Step("Переключаемся на открывшуюся вкладку и проверяем, что действительно открылся Дзен")
    public void checkMoveToDzen() {
        switchTo().window(1);
        dzenSearchText.shouldHave(text("Найти в Дзене"));

    }
}
