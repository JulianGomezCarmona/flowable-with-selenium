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

import java.time.Duration;

public class Martes implements JavaDelegate {
//    private static final ExtentReports extent = new ExtentReports();

    public void execute(DelegateExecution execution) {
//        Reports reports = new Reports();
//        reports.report("Report/test-new-venta.html");
        ExtentReports extent = Reports.getInstance();
        ExtentTest test = extent.createTest("Registro de nueva factura electronica");

        SeleniumHelper helper = new SeleniumHelper();
        helper.setup();
        WebDriverWait wait = helper.getWait();

        try {
//        login
            helper.login("https://demoface.dynamiaerp.co/login","admin","admindemo");


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
            fechaEntrega.sendKeys("10/02/2024");

//        Buscar productos
            WebElement buscadorProducto = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@title='Buscador de Productos']")));
            buscadorProducto.click();
            WebElement buscador = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Buscar' and @class='form-control'] ")));
            buscador.sendKeys("+++");
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
            helper.closet();
            extent.flush();
        }

    }

}
