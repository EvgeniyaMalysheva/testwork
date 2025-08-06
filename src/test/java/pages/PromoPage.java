package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class PromoPage {

    private final SelenideElement
            cookieElement = $("[data-testid='cookieAcceptPopup']"),
            cookieButton = $(".CookieAcceptPopup-module___NInja__button"),
            promoTermsTitle = $(".CmsEntity-module__Su2lMG__title"),
            promoCodeModuleTitle = $("h1.PromocodeActivation-module__ws3j9G__content__title"),
            promoCodeModuleInput = $("input[name='promocode']");

    @Step("Проверяем на всплывающее окно с Cookie")
    public PromoPage acceptCookie() {
        if (cookieElement.exists()) {
            cookieButton.click();
        }

        return this;
    }

    @Step("Отображаются условия корректной акции")
    public void checkPromoTermsTitle(String promoName) {
        acceptCookie();
        promoTermsTitle.shouldHave(text(promoName));
    }

    @Step("Проверяем надпись Активируйте промокод и поле для его ввода")
    public void checkPromoCodeActivationModule() {
        acceptCookie();
        promoCodeModuleTitle.shouldHave(text("Активируйте промокод"));
        promoCodeModuleInput.shouldBe(visible);

    }
}
