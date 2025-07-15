package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CartPage {

    private final SelenideElement
            cookieElement = $("[data-testid='cookieAcceptPopup']"),
            cookieButton = $(".CookieAcceptPopup-module___NInja__button"),
            promoTitle = $(".FourthArtsOffer-module__Yb0qSq__title"),
            promoTermsLink = $(".FourthArtsOffer-module__Yb0qSq__link"),
            promoTermsTitle = $(".CmsEntity-module__Su2lMG__title");
    protected final String
            promoName = "Четвёртая книга в подарок";

    @Step("Открываем главную страницу")
    public CartPage openCartPage() {
        open("/my-books/cart/");

        return this;
    }

    @Step("Проверяем на всплывающее окно с Cookie")
    public CartPage acceptCookie() {
        if (cookieElement.exists()) {
            cookieButton.click();
        }

        return this;
    }

    @Step("Видим название акции Четвёртая книга в подарок")
    public CartPage checkPromoName() {
        acceptCookie();
        promoTitle.shouldHave(text(promoName));

        return this;
    }

    @Step("Открываем ссылку на условия акции")
    public CartPage pressPromoTermsButton() {
        acceptCookie();
        promoTermsLink.click();

        return this;
    }

    @Step("Отображаются условия акции Четвёртая книга в подарок")
    public void checkPromoTermsTitle() {
        acceptCookie();
        promoTermsTitle.shouldHave(text(promoName));
    }
}
