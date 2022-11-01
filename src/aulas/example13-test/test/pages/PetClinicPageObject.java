package example13.test.pages;

import org.openqa.selenium.WebDriver;

public abstract class PetClinicPageObject { // It serves as a common abstraction for our POs.
    protected final WebDriver driver; // The base class keeps the reference to the WebDriver.

    public PetClinicPageObject(WebDriver driver) {
        this.driver = driver;
    }

    // The visit method should be overridden by the child classes.
    public void visit() {
        throw new RuntimeException("This page does not have a visit link");
    }

    // Provides a helper method for the base
    // classes to help them visit the page
    protected void visit(String url) {
        driver.get("http://localhost:8080" + url);
        isReady();
    }

    // All POs are forced to implement an isReady method. Making methods abstract is a nice
    // way to force all POs to implement their minimum required behavior.
    public abstract void isReady();
}
