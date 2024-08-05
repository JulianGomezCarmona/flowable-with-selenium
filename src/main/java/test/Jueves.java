package test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class Jueves implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        System.out.println("Hoy jueves tenemos las pizza a mitad de precio");
        System.out.println("Precio normal de las pizzas: $16.000");
        System.out.println("Precio de las pizzas con promoción: $8.000");
    }
}
