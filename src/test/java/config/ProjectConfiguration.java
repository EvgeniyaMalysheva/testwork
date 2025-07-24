package config;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ProjectConfiguration {
    public final WebConfig webConfig;

    public ProjectConfiguration(WebConfig webConfig) {
        this.webConfig = webConfig;
    }

    public void webConfig() {
        Configuration.baseUrl = webConfig.getBaseUrl();
        Configuration.pageLoadStrategy = webConfig.getPageLoadStrategy();
        Configuration.browser = webConfig.getBrowser().toString();

        Configuration.browserVersion = webConfig.getBrowserVersion();
        Configuration.browserSize = webConfig.getBrowserSize();

        if (System.getProperty("getSelenoidUser") != null) {
            Configuration.remote = getSelenoidConfiguration();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }
    }

    public String getSelenoidConfiguration() {
        return "https://"
                + webConfig.getSelenoidUser() + ":"
                + webConfig.getSelenoidPassword() + "@"
                + webConfig.getSelenoidUrl()
                + "/wd/hub";
    }
}
