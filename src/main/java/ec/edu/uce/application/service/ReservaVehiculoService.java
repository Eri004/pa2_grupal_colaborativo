package ec.edu.uce.application.service;

import java.time.LocalDate;
import java.util.List;

import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.domain.model.Pago;
import ec.edu.uce.domain.model.ReservaVehiculo;
import ec.edu.uce.domain.model.Sucursal;
import ec.edu.uce.domain.model.Vehiculo;
import ec.edu.uce.domain.model.Vendedor;
import ec.edu.uce.infrastructure.repository.ReservaVehiculoRepositoryImpl;
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

    public List<ReservaVehiculo> buscarTodos() {
        return this.reservaVehiculo.listAll();
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

    public ReservaVehiculo nuevaReserva(ReservaVehiculo nuevaReserva) {

        String cedulaCli = nuevaReserva.getCliente().getCedula() ;
        String cedulaVend = nuevaReserva.getVendedor().getCedula();
        String placa = nuevaReserva.getVehiculo().getPlaca();
        String nombreSuc =  nuevaReserva.getSucursal().getNombre();

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

        return res;
    }

    public void eliminarPorIdRes(Integer id) {
        this.reservaVehiculo.delete(this.buscarPorIdRes(id));
    }

}
