package tests;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("testwork_test")
@Owner("Evgeniya Malysheva")
@DisplayName("Навигация по сайту")
public class SiteNavigationTest extends TestBase {

    @Test
    @DisplayName("TC_UI_4: Проверка страницы ввода промокода")
    public void checkPromoCodeWindowTest() {
        mainPage.openMainPage()
                .pressPromoCodeButton();
        promoPage.checkPromoCodeActivationModule();
    }

    @Test
    @DisplayName("TC_UI_5: Поиск раздела Переговоры в выпадающем каталоге")
    public void checkCatalogNegotiationsGenreTest() {
        mainPage.openMainPage()
                .pressCatalogButton()
                .checkCatalogGenre("Переговоры");
    }

    @Test
    @DisplayName("TC_UI_6: Переход в сторонний ресурс")
    public void moveToAnotherSiteTest() {
        mainPage.openMainPage()
                .pressDzenButton();
        dzenPage.checkMoveToDzen();
    }
}
