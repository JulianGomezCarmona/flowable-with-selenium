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

import java.time.Duration;

public class Martes implements JavaDelegate {
    private static final ExtentReports extent = new ExtentReports();

    public void execute(DelegateExecution execution) {
//        Reports reports = new Reports();
//        reports.report("Report/test-new-venta.html");
        ExtentReports extent = Reports.getInstance();
        ExtentTest test = extent.createTest("registro de nueva factura electronica");

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        try {
            driver.get("https://demoface.dynamiaerp.app/login");
            WebElement input = driver.findElement(By.xpath("//input[@placeholder='Correo electrónico o Usuario']"));
            WebElement input2 = driver.findElement(By.xpath("//input[@placeholder='Password']"));


////    Introducir texto
            String text = "admin";
            input.sendKeys(text);
            String text2 = "admindemo";
            input2.sendKeys(text2);

            WebElement button = driver.findElement(By.xpath("//button[@type='button']"));
            button.click();


//        Abrir modulo de ventas
            WebElement ventas = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#ventas-module']")));
            ventas.click();
            WebElement operacion = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='#ventas-operaciones-pg']")));
            operacion.click();
            WebElement facturaElectronica = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Nueva Factura Electronica']")));
            facturaElectronica.click();


//        Generar una nueva venta

//        Seleccionar un cliente
            WebElement cliente = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='z-bandbox-button']")));
            cliente.click();
            WebElement clienteSeleccionado = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='435345345']")));
            clienteSeleccionado.click();
            WebElement ciudad = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Ciudad']")));
            ciudad.click();

//        Ingrezar fecha de entrega
            WebElement fechaEntrega = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Fecha Entrega ']")));
            String entrega = "10/02/2024";
            fechaEntrega.sendKeys(entrega);

//        Buscar productos
            WebElement buscadorProducto = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Buscador de Productos']")));
            buscadorProducto.click();
            WebElement buscador = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Buscar' and @class='form-control'] ")));
            String textbuscar = "+++";
            buscador.sendKeys(textbuscar);
            WebElement buscadorButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Buscar']")));
            buscadorButton.click();

//        Agregar producto
            WebElement agregarProducto = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//tbody/tr[3]//td//div//a[@title='Agregar']")));
            agregarProducto.click();
            ciudad.click();

//        Pagar
            WebElement pagar = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Pagar']")));
            pagar.click();

//        Escoger metodo de pago
            WebElement efectivo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Efectivo']")));
            efectivo.click();

//        Registrar pago
            WebElement registrarPago = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Registrar Pago']")));
            registrarPago.click();
            test.log(Status.PASS,"Se registro la factura electronica");
        }catch (Exception e){
            test.log(Status.FAIL,"No se registro la nueva factura");
            e.printStackTrace();
        }finally {
            driver.quit();
            extent.flush();
        }

    }

}
