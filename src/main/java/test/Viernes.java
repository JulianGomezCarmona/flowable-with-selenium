package test;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class Viernes implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        System.out.println("Hoy viernes tenemos las picadas a mitad de precio");
        System.out.println("Precio normal de las picadas: $36.000");
        System.out.println("Precio de las picadas con promoción: $18.000");
    }
}
