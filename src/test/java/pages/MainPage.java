package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            searchInput = $("[data-testid='search__input']"),
            tabList = $("[data-testid='navigation__tabsList']");
    private final String searchContent = "[data-testid='search__content--wrapper']";

    public MainPage openMainPage() {
        open("/");

        return this;
    }

    public MainPage acceptCookie() {
        if ($("[data-testid='cookieAcceptPopup']").exists())
            $(".CookieAcceptPopup-module___NInja__button").click();

        return this;
    }

    public MainPage fillBookSearchInput(String searchQuery) {
        searchInput.setValue(searchQuery).pressEnter();

        return this;
    }

    public void checkFoundBooksNotZero() {
        $$(searchContent).shouldBe(sizeGreaterThan(0));
    }

    public MainPage clickNecessaryTab(String tabName) {
        tabList.$(byText(tabName)).click();

        return this;
    }

    public MainPage findNecessaryTab(String tabName) {
        tabList.shouldHave(text(tabName));

        return this;
    }

    public void checkBookName(String bookName) {
        $(searchContent).shouldHave(text(bookName));

    }
}
