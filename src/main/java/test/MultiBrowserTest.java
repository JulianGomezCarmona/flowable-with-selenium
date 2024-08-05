package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultiBrowserTest {
    // Definir la función que abre el navegador y realiza alguna acción
    public static void openBrowser() {
        // Configurar el path al driver de Chrome
        System.setProperty("webdriver.chrome.driver", "../../../../chrome-win64/chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");

        // Encontrar el campo de búsqueda por el atributo "name"
        WebElement searchBox = driver.findElement(By.name("q"));

        // Escribir "Selenium" en el campo de búsqueda
        searchBox.sendKeys("Selenium");

        // Cerrar el navegador
        driver.quit();
    }

    public static void main(String[] args) {
        // Crear un array de threads
        Thread[] threads = new Thread[5];

        // Crear y lanzar 5 threads
        for (int i = 0; i < 5; i++) {
            threads[i] = new Thread(() -> openBrowser());
            threads[i].start();
        }

        // Esperar a que todos los threads terminen
        for (int i = 0; i < 5; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}