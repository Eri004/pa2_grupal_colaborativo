package ec.edu.uce.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import ec.edu.uce.application.interceptor.Notificacion;
import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.domain.model.Pago;
import ec.edu.uce.domain.model.ReservaVehiculo;
import ec.edu.uce.domain.model.Sucursal;
import ec.edu.uce.domain.model.Vehiculo;
import ec.edu.uce.domain.model.Vendedor;
import ec.edu.uce.infrastructure.repository.ReservaVehiculoRepositoryImpl;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReservaVehiculoService {

    @Inject
    private ReservaVehiculoRepositoryImpl reservaVehiculo;

    @Inject
    private VehiculoService vehiculoService;

    @Inject
    private ClienteService clienteService;

    @Inject
    private SucursalService sucursalService;

    @Inject
    private PagoService pagoService;

    @Inject
    private VendedorService vendedorService;

    public void guardarRes(ReservaVehiculo reservaVehiculo) {
        this.reservaVehiculo.persist(reservaVehiculo);
    }

    @Notificacion
    public List<ReservaVehiculo> buscarTodos() {
        List<ReservaVehiculo> lista = this.reservaVehiculo.listAll();
        return lista.parallelStream()
                .filter(r -> r != null)
                .collect(Collectors.toList());
    }

    public ReservaVehiculo buscarPorIdRes(Integer id) {
        return this.reservaVehiculo.findById(id);
    }

    public void actualizarRes(ReservaVehiculo res, Integer id) {

        ReservaVehiculo nuevo = this.buscarPorIdRes(id);

        if (nuevo != null) {
            nuevo.setEstado(res.getEstado());
            nuevo.setFecha(res.getFecha());
            nuevo.setCliente(this.clienteService.actualizarCli(res.getCliente(), res.getCliente().getId()));
            nuevo.setPago(this.pagoService.actualizarPago(res.getPago(), res.getPago().getId()));
            nuevo.setSucursal(this.sucursalService.actualizarSuc(res.getSucursal(), res.getSucursal().getId()));
            nuevo.setVendedor(this.vendedorService.actualizarVen(res.getVendedor(), res.getVendedor().getId()));
            nuevo.setVehiculo(this.vehiculoService.actualizarVeh(res.getVehiculo(), res.getVehiculo().getId()));
        }

    }

    @Notificacion
    public ReservaVehiculo nuevaReserva(ReservaVehiculo nuevaReserva) {

        String cedulaCli = nuevaReserva.getCliente().getCedula();
        String cedulaVend = nuevaReserva.getVendedor().getCedula();
        String placa = nuevaReserva.getVehiculo().getPlaca();
        String nombreSuc = nuevaReserva.getSucursal().getNombre();

        Double montoPago = nuevaReserva.getPago().getMonto();
        String metodoPago = nuevaReserva.getPago().getMetodoPago();

        Cliente cliente = this.clienteService.buscarPorCedulaCli(cedulaCli);
        Vehiculo vehiculo = this.vehiculoService.buscarPorPlaca(placa);
        Vendedor vendedor = this.vendedorService.buscarPorCedulaVen(cedulaVend);
        Sucursal sucursal = this.sucursalService.buscarPorNombre(nombreSuc);

        ReservaVehiculo res = new ReservaVehiculo();
        res.setCliente(cliente);
        res.setEstado("CONFIRMADO");
        res.setFecha(LocalDate.now());
        res.setSucursal(sucursal);
        res.setVendedor(vendedor);
        res.setVehiculo(vehiculo);

        Pago pago = new Pago();
        pago.setEstado("Completado");
        pago.setMetodoPago(metodoPago != null ? metodoPago : "Efectivo");
        pago.setMonto(montoPago);
        res.setPago(pago);

        vehiculo.setEstado("RESERVADO");

        this.guardarRes(res);

        CompletableFuture.runAsync(() -> {
            System.out.println("Reserva #" + res.getId()
                    + " confirmada exitosamente en hilo: " + Thread.currentThread().getName());
        });

        return res;
    }

    @Notificacion
    public String consultarReservaReactiva(String cedulaCli, String placa) {
        System.out.println("[RESERVA REACTIVA HILO]: " + Thread.currentThread().getName()
                + " (ID: " + Thread.currentThread().threadId() + ")");

        long inicio = System.currentTimeMillis();

        Uni<Cliente> clienteUni = Uni.createFrom().item(() -> this.clienteService.buscarPorCedulaCli(cedulaCli))
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());

        Uni<Vehiculo> vehiculoUni = Uni.createFrom().item(() -> this.vehiculoService.buscarPorPlaca(placa))
                .runSubscriptionOn(Infrastructure.getDefaultWorkerPool());

        Uni<String> mensajeFinal = Uni.combine().all().unis(clienteUni, vehiculoUni).asTuple().map(resultado -> {
            Cliente cliente = resultado.getItem1();
            Vehiculo vehiculo = resultado.getItem2();

            String mensaje = "Se realizo con exito la consulta reactiva."
                    + "\nCliente obtenido: " + (cliente != null ? cliente.getNombre() : "No encontrado")
                    + "\nVehiculo obtenido: "
                    + (vehiculo != null ? vehiculo.getModelo() + " (" + vehiculo.getPlaca() + ")" : "No encontrado");

            System.out.println(mensaje);

            long fin = System.currentTimeMillis();
            long tiempoTranscurrido = fin - inicio;
            System.out.println("Tiempo demora: " + tiempoTranscurrido + " ms");

            return mensaje;
        });

        return mensajeFinal.await().indefinitely();
    }

    public void eliminarPorIdRes(Integer id) {
        this.reservaVehiculo.delete(this.buscarPorIdRes(id));
    }

}
