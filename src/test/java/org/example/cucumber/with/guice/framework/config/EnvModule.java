package org.example.cucumber.with.guice.framework.config;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvModule extends AbstractModule {
    private final String envConfig;

    public EnvModule(@Nullable String envConfig) {
        if (envConfig != null && !envConfig.trim().isEmpty()) {
            this.envConfig = envConfig.trim();
        } else {
            this.envConfig = "qa";
        }
    }

    @Override
    protected void configure() {
        ClassLoader classLoader = getClass().getClassLoader();

        try (InputStream resourceAsStream =
                     classLoader.getResourceAsStream("env/" + envConfig + ".properties")) {
                        final Properties testProperties = new Properties();
            testProperties.load(resourceAsStream);
            Names.bindProperties(binder(), testProperties);

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Failed to initialize test based on environment properties: " + envConfig, e);
        }
    }
}
