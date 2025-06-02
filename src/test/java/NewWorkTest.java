import io.qameta.allure.Owner;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;

import pages.MainPage;

@Tag("testwork_test")
public class NewWorkTest extends TestBase {

    MainPage mainPage = new MainPage();

    @ValueSource(strings = {
            "python", "java"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список книг")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_1: Проверка на непустой результат поиска книг")
    @Owner("Evgeniya Malysheva")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        mainPage.openMainPage()
                .acceptCookie()
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
    @Owner("Evgeniya Malysheva")
    void searchResultsShouldContainExpectedBookSeries(String searchQuery, String bookName) {
        mainPage.openMainPage()
                .acceptCookie()
                .fillBookSearchInput(searchQuery)
                .clickNecessaryTab("Серии")
                .checkBookName(bookName);
    }

    @CsvFileSource(resources = "/testdata/searchResultsShouldContainExpectedBooks")
    @ParameterizedTest(name = "Для поискового запроса {0} в категории Книги должна быть {1}")
    @Tag("BLOCKER")
    @DisplayName("TC_WEB_3: Проверка поиска определенной книги")
    @Owner("Evgeniya Malysheva")
    void searchResultsShouldContainExpectedBooks(String searchQuery, String bookName) {
        mainPage.openMainPage()
                .acceptCookie()
                .fillBookSearchInput(searchQuery)
                .findNecessaryTab("Книги")
                .checkBookName(bookName);
    }

    @Test
    @DisplayName("TC_WEB_4: Проверка страницы ввода промокода")
    @Owner("Evgeniya Malysheva")
    public void openPromoCodeWindowTest() {
        mainPage.openMainPage();
        step("Нажимаем на кнопку Промокод", () -> {
            mainPage.acceptCookie();
            $("[data-testid='lowerMenu__item--promoCodes']")
                    .click();
        });

        step("Проверяем надпись Активируйте промокод и поле для ввода", () -> {
            $("h1.PromocodeActivation-module__ws3j9G__content__title")
                    .shouldHave(text("Активируйте промокод"));
            $("input[name='promocode']").shouldBe(visible);
        });
    }

    @Test
    @DisplayName("TC_WEB_5: Поиск раздела Переговоры в выпадающем каталоге")
    @Owner("Evgeniya Malysheva")
    public void checkCatalogTest() {
        mainPage.openMainPage();
        step("Нажимаем на кнопку Каталог", () -> {
            mainPage.acceptCookie();
            $("[data-testid='header-catalog-button']").click();
        });
        step("Проверяем всплывающее меню каталога и наличие в нем раздела Переговоры", () -> {
            $("[data-testid='genres_popup']").should(appear);
            $$("div.Column-module__Tc-eGa__column")
                    .findBy(text("Переговоры")).shouldBe(visible);
        });
    }

    @Test
    @DisplayName("TC_WEB_6: Переход в сторонний ресурс")
    @Owner("Evgeniya Malysheva")
    public void moveToAnotherSiteTest() {
        mainPage.openMainPage();
        step("Нажимаем на значок Дзен в футере главной страницы", () -> {
            mainPage.acceptCookie();
            $("a[href='https://dzen.ru/litres']").click();
        });
        step("Переключаемся на открывшуюся вкладку", () -> {
            switchTo().window(1);
        });
        step("Проверяем, что действительно открылся Дзен", () -> {
            $("[data-testid='rotator-text']").shouldHave(text("Найти в Дзене"));
        });
    }
}


