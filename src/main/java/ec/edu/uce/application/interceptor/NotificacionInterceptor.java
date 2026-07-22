package ec.edu.uce.application.interceptor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;

@Notificacion
@Interceptor
public class NotificacionInterceptor {

    @AroundInvoke
    public Object notificar(InvocationContext context) throws Exception {
        String metodo = context.getMethod().getName();
        Object[] parametros = context.getParameters();
        String paramsStr = Arrays.toString(parametros);
        String horaActual = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        long inicio = System.currentTimeMillis();

        Object result = context.proceed();
        long fin = System.currentTimeMillis();
        long tiempoEjecucion = fin - inicio;

        System.out.println("Notificando el uso del método [" + metodo
                + "], con los parámetros " + paramsStr
                + ", la hora " + horaActual
                + " y tiempo que tarda en ejecutar: " + tiempoEjecucion + " ms");

        return result;

    }

}
