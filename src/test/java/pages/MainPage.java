package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            searchInput = $("[data-testid='search__input']"),
            tabList = $("[data-testid='navigation__tabsList']"),
            cookieElement = $("[data-testid='cookieAcceptPopup']"),
            cookieButton = $(".CookieAcceptPopup-module___NInja__button"),
            promoCodeButton = $("[data-testid='lowerMenu__item--promoCodes']"),
            promoCodeModuleTitle = $("h1.PromocodeActivation-module__ws3j9G__content__title"),
            promoCodeModuleInput = $("input[name='promocode']"),
            catalogButton = $("[data-testid='header-catalog-button']"),
            genrePopup = $("[data-testid='genres_popup']"),
            dzenButton = $("a[href='https://dzen.ru/litres']"),
            dzenSearchText = $("[data-testid='rotator-text']"),
            cartButton = $("[data-testid='tab-basket']");

    private final String searchContent = "[data-testid='search__content--wrapper']",
            genresList = "div.Column-module__Tc-eGa__column";

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
        acceptCookie();
        searchInput.setValue(searchQuery).pressEnter();

        return this;
    }

    @Step("Проверяем, что вернулся не пустой список книг")
    public void checkFoundBooksNotZero() {
        acceptCookie();
        $$(searchContent).shouldBe(sizeGreaterThan(0));
    }

    @Step("Нажимаем на вкладку {tabName}")
    public MainPage clickNecessaryTab(String tabName) {
        acceptCookie();
        tabList.$(byText(tabName)).click();

        return this;
    }

    @Step("Ищем вкладку {tabName}")
    public MainPage findNecessaryTab(String tabName) {
        acceptCookie();
        tabList.shouldHave(text(tabName));

        return this;
    }

    @Step("Проверяем, что книга (серия книг) называется {bookName}")
    public void checkBookName(String bookName) {
        acceptCookie();
        $(searchContent).shouldHave(text(bookName));

    }

    @Step("Нажимаем на кнопку Промокод")
    public MainPage pressPromoCodeButton() {
        acceptCookie();
        promoCodeButton.click();

        return this;
    }

    @Step("Проверяем надпись Активируйте промокод и поле для его ввода")
    public void checkPromoCodeActivationModule() {
        acceptCookie();
        promoCodeModuleTitle.shouldHave(text("Активируйте промокод"));
        promoCodeModuleInput.shouldBe(visible);

    }

    @Step("Нажимаем на кнопку Каталог")
    public MainPage pressCatalogButton() {
        acceptCookie();
        catalogButton.click();

        return this;
    }

    @Step("Проверяем всплывающее меню каталога и наличие в нем раздела Переговоры")
    public void checkCatalogGenre(String genreName) {
        acceptCookie();
        genrePopup.should(appear);
        $$(genresList).findBy(text(genreName)).shouldBe(visible);
    }

    @Step("Нажимаем на значок Дзен в футере главной страницы")
    public MainPage pressDzenButton() {
        acceptCookie();
        dzenButton.click();

        return this;
    }

    @Step("Переключаемся на открывшуюся вкладку и проверяем, что действительно открылся Дзен")
    public void checkMoveToDzen() {
        acceptCookie();
        switchTo().window(1);
        dzenSearchText.shouldHave(text("Найти в Дзене"));

    }

    @Step("Нажимаем на значок Дзен в футере главной страницы")
    public MainPage pressCartButton() {
        acceptCookie();
        cartButton.click();

        return this;
    }

}

