import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.interactions.Actions;

import static org.assertj.core.api.Assertions.assertThat;

public class StometrovkaTest {

    private static WebDriver driver;

    @BeforeClass
public static void before() {
        WebDriverManager.chromedriver().setup();
        System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
        driver = new ChromeDriver();
        driver.get("https://stometrovka.com.ua/");
    }
    public WebDriver getDriver() {
        return driver;
    }

    @Test
    public void verifyTitle() {
        System.out.println("verifyTitle");
        String actualTitle = driver.getTitle();
        assertThat(actualTitle).isEqualTo("Стометровка интернет магазин хозяйственных товаров в Киеве");
    }
    @Test
    public void basket(){
        System.out.println("searchButton");
        String visibilityBasket = driver.findElement(By.xpath("//span[2]//a[1]//span[1]")).getText();
        assertThat(visibilityBasket).contains("Корзина");
    }
    @Test
    public void selectProductDisplay(){
        WebElement forHome = driver.findElement(By.xpath("//*[@id=\"vina-cmenu-jshopping307\"]/ul/li[2]/a/span"));
        WebElement forKitchen = driver.findElement(By.xpath("//*[@id=\"vina-cmenu-jshopping307\"]/ul/li[2]/ul/li[1]/a/span"));
        WebElement forTeaAndCoffee = driver.findElement(By.xpath("//*[@id=\"vina-cmenu-jshopping307\"]/ul/li[2]/ul/li[1]/ul/li[1]/a/span"));
        WebElement zavarnyk = driver.findElement(By.xpath("//a[contains(text(),'-')]"));
        WebElement productOpened = driver.findElement(By.xpath("//div[@id='sp-component-area']"));

        Actions actions = new Actions(getDriver());
        actions
                .moveToElement(forHome).click()
                .moveToElement(forKitchen).click()
                .moveToElement(forTeaAndCoffee).click()
                .click(zavarnyk);

        assertThat(productOpened).descriptionText();
    }

    @AfterClass
    public static void after() {
        driver.quit();
    }



}
