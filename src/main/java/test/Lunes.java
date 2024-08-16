package test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.reuse.SeleniumHelper;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;


public class Lunes implements JavaDelegate {
//    private static final ExtentReports extent = new ExtentReports();


    public void execute(DelegateExecution execution) {
        ExtentReports extent = Reports.getInstance();
        ExtentTest test = extent.createTest("Registro de objeto en el  inventario");

        SeleniumHelper helper = new SeleniumHelper();
        helper.setup();
        WebDriverWait wait = helper.getWait();

        try {
//      login
            helper.login("https://demoface.dynamiaerp.co/login","admin","admindemo");


//      Abrir modulo de inventario
            WebElement inventarioModule = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#inventario-module']")));
            inventarioModule.click();


//      Abrir apartado de productos o items
            WebElement Products = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Items o Productos']")));
            Products.click();

//       Agregar producto
            WebElement newProducts = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Nuevo']")));
            newProducts.click();

//       Registrar nuevo producto
            WebElement nombreProduct = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Nombre *']")));
            nombreProduct.sendKeys("Perro caliente");
            WebElement productType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='tipo-field entitypickerbox']//a[@role='button']")));
            productType.click();
            WebElement selectProductType = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='PRODUCTO']")));
            selectProductType.click();
            WebElement lineaPrincipal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='lineaPrincipal-field entitypickerbox']//a[@role='button']")));
            lineaPrincipal.click();
            WebElement selectLineaPrincipal = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='01 - PRODUCTOS']")));
            selectLineaPrincipal.click();
            WebElement precioVenta = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='decimalbox-calc precioVenta-field z-span']//input")));
            precioVenta.sendKeys("5000");
            WebElement costoManual = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='decimalbox-calc costoAproximado-field z-span']//input")));
            costoManual.sendKeys("4000");

//      Guardar producto
            WebElement guardar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Guardar']")));
            guardar.click();
            test.log(Status.PASS,"Se registro el producto");
        } catch (Exception e) {
            test.log(Status.FAIL,"No se registro el producto");
            e.printStackTrace();
        }finally {
            helper.closet();
            extent.flush();
        }
    }
}
