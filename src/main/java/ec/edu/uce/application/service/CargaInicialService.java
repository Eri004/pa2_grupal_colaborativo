package ec.edu.uce.application.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.domain.model.Vehiculo;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CargaInicialService {

    @Inject
    private ClienteService clienteService;

    @Inject
    private VehiculoService vehiculoService;

    // MÉTODO QUE GESTIONA LOS HILOS
    public void ejecutarInsercionesEnParalelo(List<Cliente> clientes, List<Vehiculo> vehiculos) {

        System.out.println("=== [INICIO] Hilo Principal: " + Thread.currentThread().getName() + " ===");

        // 1. Hilo A: Ejecuta el método de guardar clientes
        CompletableFuture<Void> hiloClientes = CompletableFuture.runAsync(() -> {
            this.clienteService.guardarListaClientes(clientes);
        });

        // 2. Hilo B: Ejecuta el método de guardar vehículos
        CompletableFuture<Void> hiloVehiculos = CompletableFuture.runAsync(() -> {
            this.vehiculoService.guardarListaVehiculos(vehiculos);
        });

        // 3. Sincronización: Espera a que AMBOS hilos terminen
        CompletableFuture.allOf(hiloClientes, hiloVehiculos).join();

        System.out.println("=== [FIN] Ambos hilos han terminado de guardar. Continuamos... ===");
    }
}