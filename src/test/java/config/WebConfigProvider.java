package config;

import org.aeonbits.owner.ConfigFactory;

public enum WebConfigProvider {
    INSTANCE;

    public WebConfig getConfig() {
        return config;
    }

    private final WebConfig config =
            ConfigFactory.create(WebConfig.class, System.getProperties());
}
