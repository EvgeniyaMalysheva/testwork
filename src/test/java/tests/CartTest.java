package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("testwork_test")
@Owner("Evgeniya Malysheva")
@DisplayName("Корзина")
public class CartTest extends TestBase {
    protected final String promoName = "Четвёртая книга в подарок";

    @Test
    @DisplayName("TC_UI_7: Просмотр условий промо-акции Четвертая книга в подарок")
    public void checkCartPromotionTermsTest() {
        cartPage.openCartPage()
                .checkPromoName(promoName)
                .pressPromoTermsButton();
        promoPage.checkPromoTermsTitle(promoName);
    }
}
