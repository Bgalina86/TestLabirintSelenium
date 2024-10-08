package testLabirint;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestLabirint {

    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(4));
    }

    @AfterEach
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void getTitleWebsite() throws InterruptedException {
        driver.get("https://www.labirint.ru/");
        Cookie cookie = new Cookie("cookie_policy", "1");
        driver.manage().addCookie(cookie);
        driver.navigate().refresh();
        By formLocatorSearch = By.xpath("//*[@id = \"searchform\"]");
        By formSearchInput = By.xpath("//*[@id=\"search-field\"]");
        By cartIconLocator = By.cssSelector(".b-header-b-personal-e-icon-count-m-cart");
        By cartItemsLocator = By.cssSelector(".main_order-container div.need-watch");

        // assertEquals(driver.getTitle(),
        //   "Ћабиринт |  нижный интернет-магазин: купить книги, новинки, бестселлеры");
        // driver.findElement(By.xpath("//title"))
        //   .equals("Ћабиринт |  нижный интернет-магазин: купить книги, новинки, бестселлеры");
        //  WebElement test = driver.findElement(MainPageLabirint.getTextActualSearch());
        // test.sendKeys("Java");

        // driver.findElement(By.xpath("//*[@id=\"search-field\"]")).sendKeys("Java");
        //   driver.findElement(By.xpath("//button[@form]")).click();

        //через форму
        WebElement form = driver.findElement(formLocatorSearch);
        form.findElement(formSearchInput).sendKeys("Java", Keys.RETURN);
        //form.submit();
        ////div[@class="search-result"]//*[@href="/cart/"]

        List<WebElement> buyButtons = driver.findElements(By.xpath("//div[@class=\"product-card__controls-container\"]"));
        for (int i = 0; i < 5; i++) {
            buyButtons.get(i).click();
        }

        wait.until(ExpectedConditions.textToBe(cartIconLocator, "5"));
        driver.findElement(cartIconLocator).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(cartItemsLocator, 5));
        int countBooksInBasket = driver.findElements(cartItemsLocator).size();

        Thread.sleep(2000L);

        assertEquals(5, countBooksInBasket);
        assertEquals("5", driver.findElement(cartIconLocator).getText());
        driver.findElement(By.xpath(
                "//li[@class=\"b-header-b-personal-e-list-item have-dropdown  last-child have-dropdown-notouch\"]"))
            .click();
        driver.findElement(By.xpath("//*[@id=\"basket-default-prod-count2\"]")).equals("5 товаров");
    }
}
