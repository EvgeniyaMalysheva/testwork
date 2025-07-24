package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import config.ProjectConfiguration;
import config.WebConfigProvider;
import config.WebConfig;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import pages.CartPage;
import pages.MainPage;

import static com.codeborne.selenide.Selenide.*;

public class TestBase {

    protected MainPage mainPage = new MainPage();
    protected CartPage cartPage = new CartPage();
    protected static final WebConfig webConfig = WebConfigProvider.INSTANCE.getConfig();

    @BeforeAll
    static void setBrowserParams() {
        ProjectConfiguration projectConfiguration = new ProjectConfiguration(webConfig);
        projectConfiguration.webConfig();
    }

    @BeforeEach
    void addAllureSelenideListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void getAttachAndCloseDriver() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        closeWebDriver();
    }
}

