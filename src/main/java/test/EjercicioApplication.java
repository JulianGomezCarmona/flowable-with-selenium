package test;

import org.flowable.engine.*;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EjercicioApplication {
//    Realizar venta de manera automatica en el demoface
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        // Configuración de Flowable
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        ProcessEngine processEngine = cfg.buildProcessEngine();
        try {
            // Despliegue del BPMN
            RepositoryService repositoryService = processEngine.getRepositoryService();
            Deployment deployment = repositoryService.createDeployment()
                    .addClasspathResource("testing.bpmn20.xml")
                    .deploy();
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                    .deploymentId(deployment.getId())
                    .singleResult();
            if (processDefinition == null) {
                System.out.println("No se encontró la definición del proceso.");
                return;
            }
            System.out.println("Found process definition : " + processDefinition.getName());
            // Inicio del proceso
            RuntimeService runtimeService = processEngine.getRuntimeService();
            Map<String, Object> variables = new HashMap<>();
            ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("testing", variables);
            // Consultar tareas pendientes
            TaskService taskService = processEngine.getTaskService();
            List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
            System.out.println("Tienes " + tasks.size() + " tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ") " + tasks.get(i).getName());
            }
            // Selección y completado de tareas
            if (!tasks.isEmpty()) {
                Task task = tasks.get(0); // Selecciona la primera tarea
                // Configurar variables automáticamente
                variables.put("lunes", true);
                variables.put("ventas", false);
                variables.put("miercoles", false);
                variables.put("jueves", false);
                variables.put("viernes", false);
                taskService.complete(task.getId(), variables);
                System.out.println("Tarea " + task.getName() + " completada automáticamente.");
            } else {
                System.out.println("No hay tareas disponibles para completar.");
            }
        } catch (Exception e) {
            System.out.println("Error durante el despliegue o la ejecución del proceso: " + e.getMessage());
            e.printStackTrace();
        }
    }
}