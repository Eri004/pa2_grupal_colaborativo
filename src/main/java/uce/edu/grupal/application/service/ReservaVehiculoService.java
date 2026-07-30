package uce.edu.grupal.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.application.interceptor.Auditoria;
import uce.edu.grupal.domain.model.Cliente;
import uce.edu.grupal.domain.model.ReservaVehiculo;
import uce.edu.grupal.infrastructure.repository.ReservaVehiculoRepositoryImpl;

@ApplicationScoped
@Transactional
public class ReservaVehiculoService {
    @Inject
    private ReservaVehiculoRepositoryImpl rri;

    @Inject
    private VehiculoService vs;

    @Inject
    private VendedorService vends;

    @Inject
    private PagoService ps;

    @Inject
    private ClienteService cs;

    public void guardar(ReservaVehiculo c) {
        this.rri.persist(c);
    }

    @Auditoria
    public void nuevaReserva(ReservaVehiculo r) {

        CompletableFuture<Cliente> completableCliente = CompletableFuture.supplyAsync(() -> {
            Cliente clienteParaReserva = null;
            if (r.getCliente() != null) {

                Cliente clienteExistente = this.cs.buscarPorCedula(r.getCliente().getCedula());

                if (clienteExistente != null) {

                    clienteParaReserva = clienteExistente;
                } else {

                    this.cs.guardar(r.getCliente());
                    clienteParaReserva = r.getCliente();
                }
            }
            return clienteParaReserva;
        });

        CompletableFuture<Void> completablePago = CompletableFuture.runAsync(() -> {
            this.ps.guardar(r.getPago());
        });

        CompletableFuture.allOf(completableCliente, completablePago).join();

        ReservaVehiculo nuevo = new ReservaVehiculo();
        nuevo.setFecha(r.getFecha());
        nuevo.setEstado(r.getEstado());

        nuevo.setCliente(completableCliente.join());
        nuevo.setPago(r.getPago());

        nuevo.setVehiculo(this.vs.buscarPorPlaca(r.getVehiculo().getPlaca()));
        nuevo.setVendedor(this.vends.buscarPorCedula(r.getVendedor().getCedula()));

        this.rri.persist(nuevo);
    }

    public void actualizar(Integer id, ReservaVehiculo r) {
        ReservaVehiculo nuevo = this.rri.findById(id);

        nuevo.setFecha(r.getFecha());
        nuevo.setEstado(r.getEstado());
        nuevo.setCliente(this.cs.actualizar(r.getCliente().getId(), r.getCliente()));
        nuevo.setPago(this.ps.buscarPorId(r.getPago().getId()));
        nuevo.setVehiculo(this.vs.buscarPorId(r.getVehiculo().getId()));
        nuevo.setVendedor(this.vends.buscarPorId(r.getVendedor().getId()));

    }

    public ReservaVehiculo buscarPorId(Integer id) {
        return this.rri.findById(id);
    }

    public List<ReservaVehiculo> buscarTodos() {
        return this.rri.findAll().list();
    }

    public void eliminar(Integer id) {
        this.rri.deleteById(id);
    }

    public List<ReservaVehiculo> buscarPorPlaca(String placaVehiculo) {
        return this.rri.list("vehiculo.placa", placaVehiculo);
    }

    public List<ReservaVehiculo> buscarPorCedulaVendedor(String cedulaVendedor) {
    if (cedulaVendedor == null || cedulaVendedor.isBlank()) {
        return List.of();
    }
    return this.rri.list("vendedor.cedula", cedulaVendedor);
}

    public List<ReservaVehiculo> buscarPorFecha(LocalDate fecha) {
        return this.rri.list("fecha", fecha);
    }

    public ReservaVehiculo buscarPorPlacaCedulaFecha(String placaVehiculo, String cedulaVendedor, LocalDate fecha) {
        return this.rri.buscarPorPlacaCedulaFecha(placaVehiculo, cedulaVendedor, fecha);
    }

}
