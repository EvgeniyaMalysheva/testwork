package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class MainPage {

    private final SelenideElement
            searchInput = $("[data-testid='search__input']"),
            cookieElement = $("[data-testid='cookieAcceptPopup']"),
            cookieButton = $(".CookieAcceptPopup-module___NInja__button"),
            promoCodeButton = $("[data-testid='lowerMenu__item--promoCodes']"),
            catalogButton = $("[data-testid='header-catalog-button']"),
            genrePopup = $("[data-testid='genres_popup']"),
            dzenButton = $("a[href='https://dzen.ru/litres']");

    private final String genresList = "div.Column-module__Tc-eGa__column";

    @Step("Открываем главную страницу")
    public MainPage openMainPage() {
        open("/");

        return this;
    }

    @Step("Проверяем на всплывающее окно с Cookie")
    public void acceptCookie() {
        if (cookieElement.exists()) {
            cookieButton.click();
        }

    }

    @Step("Заполняем поисковый запрос значением {searchQuery}")
    public MainPage fillBookSearchInput(String searchQuery) {
        acceptCookie();
        searchInput.setValue(searchQuery).pressEnter();

        return this;
    }

    @Step("Нажимаем на кнопку Промокод")
    public MainPage pressPromoCodeButton() {
        acceptCookie();
        promoCodeButton.click();

        return this;
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
}

