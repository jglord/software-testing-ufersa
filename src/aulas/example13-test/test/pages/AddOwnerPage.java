package example13.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddOwnerPage extends PetClinicPageObject {
    public AddOwnerPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void visit() {
        visit("/owners/new");
    }

    // The HTML page is ready when the
    // form appears on the screen.
    @Override
    public void isReady() {
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-owner-form")));
    }

    // Fills out all the HTML form elements with the data provided in AddOwnerInfo,
    // a class created for that purpose. We find the form elements by their IDs.
    public OwnerInformationPage add(AddOwnerInfo ownerToBeAdded) {
        driver.findElement(By.id("firstName")).sendKeys(ownerToBeAdded.getFirstName());
        driver.findElement(By.id("lastName")).sendKeys(ownerToBeAdded.getLastName());
        driver.findElement(By.id("address")).sendKeys(ownerToBeAdded.getAddress());
        driver.findElement(By.id("city")).sendKeys(ownerToBeAdded.getCity());
        driver.findElement(By.id("telephone")).sendKeys(ownerToBeAdded.getTelephone());

        driver.findElement(By.id("add-owner-form"))
                .findElement(By.tagName("button"))
                .click();

        // When an owner is added, the web application redirects us to the Owner
        // Information page. The method then returns the PO of the class we are
        // redirected to.
        OwnerInformationPage ownerInformationPage = new OwnerInformationPage(driver);
        ownerInformationPage.isReady();
        return ownerInformationPage;
    }
}