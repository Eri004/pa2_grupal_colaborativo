package ec.edu.uce.application.interceptor;

import java.time.LocalDateTime;
import java.util.Arrays;

import ec.edu.uce.application.service.AuditoriaService;
import ec.edu.uce.domain.model.Auditoria;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Auditar
@Interceptor
@Priority(1)
public class AuditoriaInterceptor {

    @Inject
    private AuditoriaService auditoriaService;

    @AroundInvoke
    public Object Auditar(InvocationContext context) throws Exception {

        Auditoria auditoria = new Auditoria();
        String nombreMetodo = context.getMethod().getName();
        System.out.println("Nombre del método interceptado: " + nombreMetodo);

        Object argumentos[] = context.getParameters();
        String argumentosString = Arrays.toString(argumentos);

        System.out.println("Iniciando Método: " +nombreMetodo + " con argumentos: " + argumentosString);
        long inicio = System.currentTimeMillis();
        Object resultado = null;

        try {
            resultado = context.proceed(); // Ejecuta la reserva en la BD
            return resultado;
        } finally {
            long fin = System.currentTimeMillis();
            long tiempoTranscurrido = fin - inicio;

            System.out.println("========================================");
            System.out.println("Método: " + nombreMetodo);
            System.out.println("Tiempo de ejecución: " + tiempoTranscurrido + " ms");
            System.out.println("========================================");
            auditoria.setNombreMetodo(nombreMetodo);
            auditoria.setArgumentos(argumentosString);
            auditoria.setTiempoEjecucion(tiempoTranscurrido);
            auditoria.setFechaHoraEjecucion(LocalDateTime.now());

            this.auditoriaService.guardar(auditoria);
        
        }
    }
}
