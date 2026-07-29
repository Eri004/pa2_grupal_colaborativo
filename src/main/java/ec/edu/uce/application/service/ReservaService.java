package ec.edu.uce.application.service;

import ec.edu.uce.application.interceptor.Auditoria;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.domain.model.Pago;
import ec.edu.uce.domain.model.Reserva;
import ec.edu.uce.domain.model.Vehiculo;
import ec.edu.uce.domain.model.Vendedor;
import ec.edu.uce.infrastructure.repository.ReservaRepositoryImpl;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReservaService {

    @Inject
    private ReservaRepositoryImpl rri;

    @Inject
    private VehiculoService vs;

    @Inject
    private VendedorService vends;

    @Inject
    private PagoService ps;

    @Inject
    private ClienteService cs;

    public void guardar(Reserva c) {

        this.rri.persist(c);

    }

    @Auditoria
    public void nuevaReserva(Reserva r) {

        CompletableFuture<Void> completableCliente = CompletableFuture.runAsync(() -> {
            this.cs.guardar(r.getCliente());
        });

        CompletableFuture<Void> completablePago = CompletableFuture.runAsync(() -> {
            this.ps.guardar(r.getPago());
        });

        CompletableFuture.allOf(completableCliente, completablePago).join();

        Reserva nuevo = new Reserva();
        nuevo.setFecha(r.getFecha());
        nuevo.setEstado(r.getEstado());

        nuevo.setCliente(r.getCliente());
        nuevo.setPago(r.getPago());
        
        nuevo.setVehiculo(this.vs.buscarPorId(r.getVehiculo().getId()));
        nuevo.setVendedor(this.vends.buscarPorId(r.getVendedor().getId()));

        this.rri.persist(nuevo);

    }

    public void actualizar(Integer id, Reserva r) {

        Reserva nuevo = this.rri.findById(id);

        nuevo.setFecha(r.getFecha());
        nuevo.setEstado(r.getEstado());
        nuevo.setCliente(this.cs.actualizar(r.getCliente().getId(), r.getCliente()));
        nuevo.setPago(this.ps.buscarPorId(r.getPago().getId()));
        nuevo.setVehiculo(this.vs.buscarPorId(r.getVehiculo().getId()));
        nuevo.setVendedor(this.vends.buscarPorId(r.getVendedor().getId()));

    }

    public Reserva buscarPorId(Integer id) {

        return this.rri.findById(id);

    }

    public void eliminar(Integer id) {

        this.rri.deleteById(id);

    }

    public List<Reserva> buscarPorFecha(LocalDate fecha) {

        return this.rri.listAll()
                .parallelStream()
                .filter(r -> r.getFecha().equals(fecha))
                .collect(Collectors.toList());

    }


    public Reserva buscarPorPlacaCedulaFecha(String placaVehiculo, String cedulaVendedor, LocalDate fecha) {
        return this.rri.listAll()
                .parallelStream()
                .filter(r -> r.getVehiculo() != null && r.getVehiculo().getPlaca().equals(placaVehiculo))
                .filter(r -> r.getVendedor() != null && r.getVendedor().getCedula().equals(cedulaVendedor))
                .filter(r -> r.getFecha().equals(fecha))
                .findFirst()
                .orElse(null);
    }

    public Uni<String> realizarReservaReactiva(String placaVehiculo, String cedulaVendedor, Cliente cliente, Pago pago,
            LocalDate fecha) {

        Uni<Vehiculo> promesaVehiculo = this.vs.buscarPorPlacaPromesa(placaVehiculo);
        Uni<Vendedor> promesaVendedor = this.vends.buscarPorCedulaPromesa(cedulaVendedor);

        return Uni.combine().all().unis(promesaVehiculo, promesaVendedor)
                .asTuple()
                .map(result -> {

                    Vehiculo vehiculo = result.getItem1();
                    Vendedor vendedor = result.getItem2();

                    Reserva reserva = new Reserva();
                    reserva.setVehiculo(vehiculo);
                    reserva.setVendedor(vendedor);

                    reserva.setCliente(cliente);
                    reserva.setPago(pago);

                    reserva.setFecha(fecha);
                    reserva.setEstado("RESERVADO");

                    this.cs.guardar(cliente);

                    this.rri.persist(reserva);

                    String mensaje = "Reserva realizada con éxito. Vehículo Placa: " + vehiculo.getPlaca()
                            + ", Vendedor Cédula: " + vendedor.getCedula()
                            + ", Cliente: " + cliente.getNombre();

                    System.out.println(mensaje);
                    return mensaje;
                });
    }

}
