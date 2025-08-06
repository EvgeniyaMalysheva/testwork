package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class SearchPage {

    private final SelenideElement
            cookieElement = $("[data-testid='cookieAcceptPopup']"),
            cookieButton = $(".CookieAcceptPopup-module___NInja__button"),
            tabList = $("[data-testid='navigation__tabsList']");

    private final String searchContent = "[data-testid='search__content--wrapper']";

    @Step("Проверяем на всплывающее окно с Cookie")
    public SearchPage acceptCookie() {
        if (cookieElement.exists()) {
            cookieButton.click();
        }

        return this;
    }

    @Step("Проверяем, что вернулся не пустой список книг")
    public void checkFoundBooksNotZero() {
        acceptCookie();
        $$(searchContent).shouldBe(sizeGreaterThan(0));
    }

    @Step("Нажимаем на вкладку {tabName}")
    public SearchPage clickNecessaryTab(String tabName) {
        acceptCookie();
        tabList.$(byText(tabName)).click();

        return this;
    }

    @Step("Проверяем, что книга (серия книг) называется {bookName}")
    public void checkBookName(String bookName) {
        acceptCookie();
        $(searchContent).shouldHave(text(bookName));
    }

    @Step("Ищем вкладку {tabName}")
    public SearchPage findNecessaryTab(String tabName) {
        acceptCookie();
        tabList.shouldHave(text(tabName));

        return this;
    }

}
