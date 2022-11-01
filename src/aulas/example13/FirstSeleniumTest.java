package aulas.example13;

import aulas.example13.pages.ListOfOwnersPage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class FirstSeleniumTest {
    @BeforeAll
    public static void beforeAll() {
        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\Users\\Aluno\\Documents\\software-testing-studies\\src\\aulas\\example13\\driver\\chromedriver.exe"
        );
    }

    @Test
    void firstSeleniumTest() {
        WebDriver browser = new ChromeDriver();
        browser.get("http://localhost:8080");
        WebElement welcomeHeader = browser.findElement(By.tagName("h2"));
        assertThat(welcomeHeader.getText()).isEqualTo("Welcome");
        browser.close();
    }

    ListOfOwnersPage ListOfOwnersPage(String Owners) {
        WebDriver browser = new ChromeDriver();
        browser.get("http://localhost:8080");

        browser.findElement(By.id("lastNameGroup")).sendKeys(Owners);
        WebElement button = browser.findElement(By.id("search-owner-form")).findElement(By.tagName("button"));
        button.click();

        ListOfOwnersPage listOfOwnersPage = new ListOfOwnersPage(browser);

        listOfOwnersPage.isReady();

        return listOfOwnersPage;
    }

}