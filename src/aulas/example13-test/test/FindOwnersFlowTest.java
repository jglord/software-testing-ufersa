package example13.test;

import example13.test.pages.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FindOwnersFlowTest {
    private FindOwnersPage page;
    protected static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", "/home/flavia/IdeaProjects/software-testing/src/example13/driver/chromedriver");
    }

    // When the test suite is done, we close the Selenium driver. This
    // method is also a good candidate to move to a base class.
    @AfterAll
    static void close() {
        driver.close();
    }

    // Our user journey
    @Test
    void findOwnersBasedOnTheirLastNames() {
        driver = new ChromeDriver();
        page = new FindOwnersPage(driver); // Creates the FindOwners PO, where the test should start.

        // Creates a bunch of owners to be added. We need owners before testing the listing page.
        AddOwnerInfo owner1 = new AddOwnerInfo("John", "Doe", "some address", "some city", "11111");
        AddOwnerInfo owner2 = new AddOwnerInfo("Jane", "Doe", "some address", "some city", "11111");
        addOwners(owner1, owner2);

        // Visits the Find Owners page
        page.visit();

        // Looks for all owners with Doe as their surname.
        ListOfOwnersPage listPage = page.findOwners("Doe");
        List<OwnerInfo> all = listPage.all();

        // Asserts that we find John and Jane from the Doe family.
        assertThat(all).hasSize(2).
                containsExactlyInAnyOrder(owner1.toOwnerInfo(), owner2.toOwnerInfo());
    }

    // The addOwners helper method adds an owner
    // via the Add Owner page.
    private void addOwners(AddOwnerInfo... owners) {
        AddOwnerPage addOwnerPage = new AddOwnerPage(driver);

        for (AddOwnerInfo owner : owners) {
            addOwnerPage.visit();
            addOwnerPage.add(owner);
        }
    }
}