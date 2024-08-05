package test.perros_calientes;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import java.util.Scanner;

public class decidir implements JavaDelegate {
    public void execute(DelegateExecution execution) {
        Scanner scanner = new Scanner(System.in);
        boolean opcionValida = false;

        try{
            while (!opcionValida) {
                System.out.println("¿Desea comprar la perros calientes (si/no)?");
                String respuesta = scanner.nextLine().toLowerCase();

                try {
                    if (respuesta.equals("si")) {
                        execution.setVariable("si", true);
                        execution.setVariable("no", false);
//                       execution.setVariable("no", false);
                        System.out.println(respuesta+"adios");
                        opcionValida = true;
                    } else if (respuesta.equals("no")) {
                        execution.setVariable("no", true);
                        execution.setVariable("si", false);
//                       execution.setVariable("si", false);
                        System.out.println(respuesta+"hola");
                        opcionValida = true;
                    }
                }catch (Exception e){
                    System.out.println("Opción no válida. Por favor, ingrese 'si' o 'no'.");

                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
//
    }
}
