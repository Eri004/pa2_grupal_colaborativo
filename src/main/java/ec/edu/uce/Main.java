package ec.edu.uce;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.application.service.CargaInicialService;
import ec.edu.uce.application.service.ClienteService;
import ec.edu.uce.application.service.ReservaVehiculoService;
import ec.edu.uce.application.service.VehiculoService;
import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.domain.model.Vehiculo;
import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import jakarta.inject.Inject;

public class Main {
 
    public static void main(String[] args) {
        Quarkus.run(App.class, args);
    }
 
    public static class App implements QuarkusApplication {

        @Inject
        ClienteService clienteService;

        @Inject
        VehiculoService vehiculoService;

        @Inject
        ReservaVehiculoService reservaVehiculoService;

        @Inject
        CargaInicialService cargaInicialService;
        @Override
        public int run(String... args) throws Exception {

            List<Cliente> listaClientes = new ArrayList<>();
        
            Cliente c1 = new Cliente();
            c1.setNombre("Julia");
            c1.setApellido("Pérez");
            c1.setCedula("1711111111");
            c1.setEmail("julia@uce.edu.ec");
            listaClientes.add(c1);

            Cliente c2 = new Cliente();
            c2.setNombre("Carlos");
            c2.setApellido("Mendoza");
            c2.setCedula("1722222222");
            c2.setEmail("carlos@uce.edu.ec");
            listaClientes.add(c2);

            // 2. Crear manualmente la lista de Vehículos
            List<Vehiculo> listaVehiculos = new ArrayList<>();

            Vehiculo v1 = new Vehiculo();
            v1.setPlaca("PBA-1001");
            v1.setMarca("Toyota");
            v1.setModelo("Yaris");
            v1.setPrecioPorDia(new BigDecimal(15.5));
            listaVehiculos.add(v1);

            Vehiculo v2 = new Vehiculo();
            v2.setPlaca("PBB-2002");
            v2.setMarca("Hyundai");
            v2.setModelo("Accent");
            v2.setPrecioPorDia(BigDecimal.valueOf(15.0));
            listaVehiculos.add(v2);

            // 3. Instanciar el servicio y llamar al método concurrente
            // (En un entorno CDI inyectas los servicios, o si es un main puro instancias las clases)
            cargaInicialService.ejecutarInsercionesEnParalelo(listaClientes, listaVehiculos);

            System.out.println("--- PROCESO COMPLETADO ---");
               return 0;
       }
    }
}
