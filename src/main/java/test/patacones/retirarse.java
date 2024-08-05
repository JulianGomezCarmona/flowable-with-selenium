package test.patacones;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

public class retirarse implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        System.out.println(execution.getVariable("name")+ " no compró el patacon");
    }
}
