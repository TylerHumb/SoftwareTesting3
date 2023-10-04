import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class SignIn {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        //set upan implicit wait in case of slow connections / page load etc
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        //1. Browse the petstore page
        driver.get("https://petstore.octoperf.com/");

        //2. Locate the Store enter button and Click
        driver.findElement(By.linkText("Enter the Store")).click();

        //3. Navigate to the sign in section
        driver.findElement(By.linkText("Sign In")).click();

        //4. Navigate to the register user section
        driver.findElement(By.linkText("Register Now!")).click();

        //5.0 Generate random username (we need to do this as the username is requried to be unique in the system otherwise it crashes
        String username = UsernameGenerator.GenerateUsername();
        //5 enter user details
        //5.1 Enter User ID
        driver.findElement(By.name("username")).sendKeys(username);
        //5.2 Enter Password
        driver.findElement(By.name("password")).sendKeys("password");
        //5.2.1 confirm Password
        driver.findElement(By.name("repeatedPassword")).sendKeys("password");
        //5.3 enter name
        driver.findElement(By.name("account.firstName")).sendKeys("John");
        //5.4 enter lastname
        driver.findElement(By.name("account.lastName")).sendKeys("smith");
        //5.5 enter password
        driver.findElement(By.name("account.email")).sendKeys("JohnSmith@gmail.com");
        //5.6 enter phone
        driver.findElement(By.name("account.phone")).sendKeys("0000000000");
        //5.7 enter Address1
        driver.findElement(By.name("account.address1")).sendKeys("401 swanston street");
        //5.7.1 enter address2
        driver.findElement(By.name("account.address2")).sendKeys("527 woolongong drive");
        //5.8 enter City
        driver.findElement(By.name("account.city")).sendKeys("Lockington");
        //5.9 enter State
        driver.findElement(By.name("account.state")).sendKeys("Bavaria");
        //5.10 enter zip
        driver.findElement(By.name("account.zip")).sendKeys("3001");
        //5.11 enter country
        driver.findElement(By.name("account.country")).sendKeys("Australia");
        //5.12 select language preference
        Select langpref = new Select(driver.findElement(By.name("account.languagePreference")));
        langpref.selectByIndex(1);
        //5.13 select favourite animal
        Select favAnimal = new Select(driver.findElement(By.name("account.favouriteCategoryId")));
        favAnimal.selectByIndex(2);
        //5.14 enable myList
        driver.findElement(By.name("account.listOption")).click();
        //5.15 enable MyBanner
        driver.findElement(By.name("account.bannerOption")).click();

        //6. Save Account Information
        driver.findElement(By.name("newAccount")).click();

        //7. Log into new account
        //7.1 navigate to Sign in page
        driver.findElement(By.linkText("Sign In")).click();
        //7.2 enter username
        driver.findElement(By.name("username")).sendKeys(username);
        //7.3 enter password
        WebElement passField = driver.findElement(By.name("password"));
        passField.clear();
        passField.sendKeys("password");
        //7.4 click login button
        driver.findElement(By.name("signon")).click();
    }
}
