package test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Miercoles implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.youtube.com/");

        WebElement searchBox = driver.findElement(By.xpath("//div[@id='search-input']//input[@id='search']"));
        String video = "Jardin con enanitos melendy";
        searchBox.sendKeys(video);
        WebElement buttonSearch = driver.findElement(By.xpath("//button[@aria-label='Buscar']"));
        buttonSearch.click();
        WebElement videoSelected = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/watch?v=v3-9eDFDAFw&pp=ygUbSmFyZGluIGNvbiBlbmFuaXRvcyBtZWxlbmR5']")));
        videoSelected.click();


    }
}
