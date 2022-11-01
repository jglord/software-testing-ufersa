package example13.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ListOfOwnersPage extends PetClinicPageObject{
    // All POs receive the WebDriver in the constructor.
    public ListOfOwnersPage(WebDriver driver) {
        super(driver);
    }

    // The isReady method lets us know whether the page is ready in the browser so we can start
    // manipulating it. This is important, as some pages take more time than others to load.
    @Override
    public void isReady() {
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("owners")));
        // The Owners page is considered ready when the list of
        // owners is loaded. We find the table with owners by its
        // ID. We wait up to three seconds for that to happen.
    }

    public List<OwnerInfo> all() {
        // Creates a list to hold all the owners.
        // For that, we create an OwnerInfo class.
        List<OwnerInfo> owners = new ArrayList<>();
        // Gets the HTML table and all its rows. The table’s ID is owners,
        // which makes it easy to find.
        WebElement table = driver.findElement(By.id("owners"));
        List<WebElement> rows = table.findElement(By.tagName("tbody")).findElements(By.tagName("tr"));

        // For each row in the table
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            // Gets the value of each HTML cell. The first column contains the
            // name, the second the address, and so on.
            String name = columns.get(0).getText().trim();
            String address = columns.get(1).getText().trim();
            String city = columns.get(2).getText().trim();
            String telephone = columns.get(3).getText().trim();
            String pets = columns.get(4).getText().trim();

            // Once all the information is collected from the
            // HTML, we build an OwnerInfo class.
            OwnerInfo ownerInfo = new OwnerInfo(name, address, city, telephone, pets);
            owners.add(ownerInfo);
        }

        return owners;
    }
}