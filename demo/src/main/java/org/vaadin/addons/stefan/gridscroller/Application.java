package org.vaadin.addons.stefan.gridscroller;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Viewport;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {
    public static void main(String[] args) throws Throwable {
        SpringApplication.run(Application.class, args);
    }
}
