import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class PlaceOrder {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        //set upan implicit wait in case of slow connections / page load etc
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //1. Browse the petstore page
        driver.get("https://petstore.octoperf.com/");

        //2. Locate the Store enter button and Click
        driver.findElement(By.linkText("Enter the Store")).click();

        //3. navigate to a category page
        driver.findElement(By.xpath("//*[@id=\"QuickLinks\"]/a[1]")).click();

        //4. navigate to a product page
        driver.findElement(By.linkText("FI-SW-01")).click();

        //5. Add item to cart (doesnt matter which one)
        driver.findElement(By.linkText("Add to Cart")).click();

        //6. proceed to checkout
        driver.findElement(By.linkText("Proceed to Checkout")).click();

        //7. sign in using existing account
        //7.1 enter username
        driver.findElement(By.name("username")).sendKeys("TestUsername");
        //7.2 enter password
        WebElement passField = driver.findElement(By.name("password"));
        passField.clear();
        passField.sendKeys("password");
        //7.4 click login button
        driver.findElement(By.name("signon")).click();

        //8. Navigate back to check out
        driver.findElement(By.name("img_cart")).click();
        driver.findElement(By.linkText("Proceed to Checkout")).click();

        //9. enter card details (shipping details pre-filled from account
        //9.1 select card type
        Select cardtype = new Select(driver.findElement(By.name("order.cardType")));
        cardtype.selectByIndex(1);
        //9.2 Enter card number
        driver.findElement(By.name("order.creditCard")).clear();
        driver.findElement(By.name("order.creditCard")).sendKeys("3333 4444 5555 6666");
        //9.3 enter expirary date
        driver.findElement(By.name("order.expiryDate")).clear();
        driver.findElement(By.name("order.expiryDate")).sendKeys("23/40");

        //10 click continue
        driver.findElement(By.name("newOrder")).click();

        //11 confirm details
        driver.findElement(By.linkText("Confirm")).click();
    }
}
