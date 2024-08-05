package test.pizzas;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class pagar implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        System.out.println(execution.getVariable("name")+ " compró la pizza");
    }
}
