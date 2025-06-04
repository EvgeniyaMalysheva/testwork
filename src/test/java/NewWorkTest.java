import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import pages.MainPage;

@Tag("testwork_test")
@Owner("Evgeniya Malysheva")
public class NewWorkTest extends TestBase {

    MainPage mainPage = new MainPage();

    @ValueSource(strings = {
            "python", "java"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список книг")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_1: Проверка на непустой результат поиска книг")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        mainPage.openMainPage()
                .fillBookSearchInput(searchQuery)
                .checkFoundBooksNotZero();
    }

    @CsvSource(value = {
            "Толкин, Властелин Колец",
            "Толстой, Детство. Отрочество. Юность"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в категории Серии должна быть {1}")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_2: Проверка поиска определенной серии книг")
    void searchResultsShouldContainExpectedBookSeries(String searchQuery, String bookName) {
        mainPage.openMainPage()
                .fillBookSearchInput(searchQuery)
                .clickNecessaryTab("Серии")
                .checkBookName(bookName);
    }

    @CsvFileSource(resources = "/testdata/searchResultsShouldContainExpectedBooks")
    @ParameterizedTest(name = "Для поискового запроса {0} в категории Книги должна быть {1}")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_3: Проверка поиска определенной книги")
    void searchResultsShouldContainExpectedBooks(String searchQuery, String bookName) {
        mainPage.openMainPage()
                .acceptCookie()
                .fillBookSearchInput(searchQuery)
                .findNecessaryTab("Книги")
                .checkBookName(bookName);
    }

    @Test
    @DisplayName("TC_WEB_4: Проверка страницы ввода промокода")
    public void checkPromoCodeWindowTest() {
        mainPage.openMainPage()
                .pressPromoCodeButton()
                .checkPromoCodeActivationModule();

    }

    @Test
    @DisplayName("TC_WEB_5: Поиск раздела Переговоры в выпадающем каталоге")
    public void checkCatalogNegotiationsGenreTest() {
        mainPage.openMainPage()
                .pressCatalogButton()
                .checkCatalogGenre("Переговоры");
    }

    @Test
    @DisplayName("TC_WEB_6: Переход в сторонний ресурс")
    public void moveToAnotherSiteTest() {
        mainPage.openMainPage()
                .pressDzenButton()
                .checkMoveToDzen();
    }
}


