package test.hamburguesas;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Scanner;

public class pagar implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        System.out.println(execution.getVariable("name")+ " compró la hamburguesa");
    }
}
