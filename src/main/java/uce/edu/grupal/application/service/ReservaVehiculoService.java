package uce.edu.grupal.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.application.interceptor.Auditoria;
import uce.edu.grupal.domain.model.Cliente;
import uce.edu.grupal.domain.model.Pago;
import uce.edu.grupal.domain.model.Vendedor;
import uce.edu.grupal.domain.model.ReservaVehiculo;
import uce.edu.grupal.domain.model.Vehiculo;
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

        CompletableFuture<Void> completableCliente = CompletableFuture.runAsync(() -> {
            this.cs.guardar(r.getCliente());
        });

        CompletableFuture<Void> completablePago = CompletableFuture.runAsync(() -> {
            this.ps.guardar(r.getPago());
        });

        CompletableFuture.allOf(completableCliente, completablePago).join();

        ReservaVehiculo nuevo = new ReservaVehiculo();
        nuevo.setFecha(r.getFecha());
        nuevo.setEstado(r.getEstado());

        nuevo.setCliente(r.getCliente());
        nuevo.setPago(r.getPago());
        
        nuevo.setVehiculo(this.vs.buscarPorId(r.getVehiculo().getId()));
        nuevo.setVendedor(this.vends.buscarPorId(r.getVendedor().getId()));

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

    public void eliminar(Integer id) {
        this.rri.deleteById(id);

    }

    public List<ReservaVehiculo> buscarPorFecha(LocalDate fecha) {

        return this.rri.listAll()
                .parallelStream()
                .filter(r -> r.getFecha().equals(fecha))
                .collect(Collectors.toList());

    }

    public ReservaVehiculo buscarPorPlacaCedulaFecha(String placaVehiculo, String cedulaVendedor, LocalDate fecha) {
        return this.rri.listAll()
                .parallelStream()
                .filter(r -> r.getVehiculo() != null && r.getVehiculo().getPlaca().equals(placaVehiculo))
                .filter(r -> r.getVendedor() != null && r.getVendedor().getCedula().equals(cedulaVendedor))
                .filter(r -> r.getFecha().equals(fecha))
                .findFirst()
                .orElse(null);
    }

    public List<ReservaVehiculo> buscarTodos(){
        return this.rri.findAll().list();
    }
}
