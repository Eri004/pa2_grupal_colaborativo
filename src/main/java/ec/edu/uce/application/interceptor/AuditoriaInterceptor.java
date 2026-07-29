package ec.edu.uce.application.interceptor;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import ec.edu.uce.domain.model.Reserva;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Interceptor
@Auditoria
public class AuditoriaInterceptor {

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        String nombreMetodo = context.getMethod().getName();
        Object[] args = context.getParameters();

        Object obj = args[0];
        Reserva res = (Reserva) obj;

        CompletableFuture.runAsync(() -> {
            try (FileWriter writer = new FileWriter("auditoria.txt", true)) {
                writer.write("=================================================\n");
                writer.write("Fecha Auditoría: " + LocalDateTime.now() + "\n");
                writer.write("Método invocado: " + nombreMetodo + "\n");
                writer.write("Datos de la reserva:\n");
                writer.write("Estado: " + res.getEstado() + ", Fecha: " + res.getFecha() + "\n");
                writer.write(res.toString() + "\n");
                writer.write("=================================================\n\n");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir la auditoría.");
                e.printStackTrace();
            }
        });


        Object resultado = context.proceed();

        return resultado;

    }

}
