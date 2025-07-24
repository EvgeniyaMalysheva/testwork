package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "system:properties",
        "classpath:local.properties"
})

public interface WebConfig extends Config {
    @Key("baseUrl")
    @DefaultValue("https://www.litres.ru")
    String getBaseUrl();

    @Key("pageLoadStrategy")
    @DefaultValue("eager")
    String getPageLoadStrategy();

    @Key("browser")
    @DefaultValue("CHROME")
    Browser getBrowser();

     @Key("browserVersion")
    @DefaultValue("127.0")
    String getBrowserVersion();

     @Key("browserSize")
    @DefaultValue("1920x1080")
    String getBrowserSize();

//    @Key("isRemote")
//    @DefaultValue("false")
//    boolean isRemote();

     @Key("selenoidUser")
    @DefaultValue("${SELENOID_USER}")
    String getSelenoidUser();

     @Key("selenoidPassword")
    @DefaultValue("${SELENOID_PASSWORD}")
    String getSelenoidPassword();

     @Key("selenoidUrl")
    @DefaultValue("${SELENOID_HOST}")
    String getSelenoidUrl();

}
