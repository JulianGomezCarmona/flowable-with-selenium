package test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.nio.file.Paths;

public class Jueves implements JavaDelegate {
        public void execute(DelegateExecution execution) {
        System.out.println("Hoy jueves tenemos las pizza a mitad de precio");
        System.out.println("Precio normal de las pizzas: $16.000");
        System.out.println("Precio de las pizzas con promoción: $8.000");
    }
//    public static void main(String[] args) {
//        try (Playwright playwright = Playwright.create()) {
//            Browser browser = playwright.webkit().launch();
//            Page page = browser.newPage();
//            page.navigate("https://www.youtube.com/");
//            page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("example.png")));
//        }
//    }
}