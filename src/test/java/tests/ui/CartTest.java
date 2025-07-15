package tests.ui;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import tests.TestBase;

@Tag("testwork_test")
@Owner("Evgeniya Malysheva")
public class CartTest extends TestBase {

    @Test
    @DisplayName("TC_UI_7: Просмотр условий промо-акции Четвертая книга в подарок")
    public void checkCartPromotionTermsTest() {
        cartPage.openCartPage()
                .acceptCookie()
                .checkPromoName()
                .pressPromoTermsButton()
                .checkPromoTermsTitle();
    }
}
