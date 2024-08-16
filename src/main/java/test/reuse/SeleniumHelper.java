package test.reuse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumHelper {
    private WebDriver driver;
    private WebDriverWait wait;

    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    public void login( String url,  String username, String password){
        driver.get(url);
        WebElement input = driver.findElement(By.xpath("//input[@placeholder='Correo electrónico o Usuario']"));
        input.sendKeys(username);

        WebElement input2 = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        input2.sendKeys(password);
        WebElement button = driver.findElement(By.xpath("//button[@type='button']"));
        button.click();

    }
    public void closet(){
        driver.quit();
    }

    public WebDriverWait getWait() {
        return wait;
    }
}
