package example13.test;

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
        System.setProperty("webdriver.chrome.driver", "/home/flavia/IdeaProjects/software-testing/src/example13/driver/chromedriver");
    }

    @Test
    void firstSeleniumTest() {
        WebDriver browser = new ChromeDriver();
        browser.get("http://localhost:8080");
        WebElement welcomeHeader = browser.findElement(By.tagName("h2"));
        assertThat(welcomeHeader.getText()).isEqualTo("Welcome");
        browser.close();
    }
}