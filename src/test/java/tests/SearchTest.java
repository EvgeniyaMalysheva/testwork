package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@Tag("testwork_test")
@Owner("Evgeniya Malysheva")
@Epic("Litres UI")
@Story("Search Content")
@DisplayName("Поиск")
public class SearchTest extends TestBase {

    @ValueSource(strings = {
            "python", "java"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список книг")
    @Tag("BLOCKER")
    @DisplayName("TC_UI_1: Проверка на непустой результат поиска книг")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        mainPage.openMainPage()
                .fillBookSearchInput(searchQuery);
        searchPage.checkFoundBooksNotZero();
    }

    @CsvSource(value = {
            "Толкин, Властелин Колец",
            "Толстой, Детство. Отрочество. Юность"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в категории Серии должна быть {1}")
    @DisplayName("TC_UI_2: Проверка поиска определенной серии книг")
    void searchResultsShouldContainExpectedBookSeries(String searchQuery, String bookName) {
        mainPage.openMainPage()
                .fillBookSearchInput(searchQuery);
        searchPage.clickNecessaryTab("Серии")
                .checkBookName(bookName);
    }

    @CsvFileSource(resources = "/testdata/searchResultsShouldContainExpectedBooks")
    @ParameterizedTest(name = "Для поискового запроса {0} в категории Книги должна быть {1}")
    @Tag("BLOCKER")
    @DisplayName("TC_UI_3: Проверка поиска определенной книги")
    void searchResultsShouldContainExpectedBooks(String searchQuery, String bookName) {
        mainPage.openMainPage()
                .fillBookSearchInput(searchQuery);
        searchPage.findNecessaryTab("Книги")
                .checkBookName(bookName);
    }
}


