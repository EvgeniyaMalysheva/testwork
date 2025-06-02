package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            searchInput = $("[data-testid='search__input']"),
            tabList = $("[data-testid='navigation__tabsList']"),
            cookieElement = $("[data-testid='cookieAcceptPopup']"),
            cookieButton = $(".CookieAcceptPopup-module___NInja__button");
    private final String searchContent = "[data-testid='search__content--wrapper']";

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        open("/");

        return this;
    }

    @Step("Проверяем на всплывающее окно с Cookie")
    public MainPage acceptCookie() {
        if (cookieElement.exists()) {
            cookieButton.click();
        }

        return this;
    }

    @Step("Заполняем поисковый запрос значением {searchQuery}")
    public MainPage fillBookSearchInput(String searchQuery) {
        searchInput.setValue(searchQuery).pressEnter();

        return this;
    }

    @Step("Проверяем, что вернулся не пустой список книг")
    public void checkFoundBooksNotZero() {
        $$(searchContent).shouldBe(sizeGreaterThan(0));
    }

    @Step("Нажимаем на вкладку {tabName}")
    public MainPage clickNecessaryTab(String tabName) {
        tabList.$(byText(tabName)).click();

        return this;
    }

    @Step("Ищем вкладку {tabName}")
    public MainPage findNecessaryTab(String tabName) {
        tabList.shouldHave(text(tabName));

        return this;
    }

    @Step("Проверяем, что книга (серия книг) называется {bookName}")
    public void checkBookName(String bookName) {
        $(searchContent).shouldHave(text(bookName));

    }
}
