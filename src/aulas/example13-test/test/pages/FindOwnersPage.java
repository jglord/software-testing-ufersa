package example13.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Optional;

public class FindOwnersPage extends PetClinicPageObject {

    // The constructor of all our POs receives the Selenium
    // driver. The PO needs it to manipulate the web page.
    public FindOwnersPage(WebDriver driver) {
        super(driver);
    }

    // This method is responsible for finding an owner
    // on this page based on their last name.
    public ListOfOwnersPage findOwners(String ownerLastName) {

        // Finds the HTML element whose ID is lastName and
        // types the last name of the owner we are looking for.
        driver.findElement(By.id("lastName")).sendKeys(ownerLastName); // the sendKeys() fills in the input with ownerLastName.
        WebElement findOwnerButton = driver.findElement(By.id("search-owner-form")).findElement(By.tagName("button"));
        findOwnerButton.click(); // Clicks the Find Owner button. We find it on the page by its ID.

        // Takes us to another page. To represent that, we
        // make the PO return the new page, also as a PO.
        ListOfOwnersPage listOfOwnersPage = new ListOfOwnersPage(driver);
        listOfOwnersPage.isReady(); // Waits for the page to be ready before returning it
        return listOfOwnersPage;
    }

    public AddOwnerPage addOwner() {
        Optional<WebElement> link = driver.findElements(By.tagName("a"))
                .stream().filter(el -> el.getText().equals("Add Owner")).findFirst();
        link.get().click();

        AddOwnerPage addOwnerPage = new AddOwnerPage(driver);
        addOwnerPage.isReady();
        return addOwnerPage;
    }

    public void visit() {
        visit("/owners/find");
    }

    @Override
    public void isReady() {
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-owner-form")));
    }
}
