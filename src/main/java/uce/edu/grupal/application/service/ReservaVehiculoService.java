package uce.edu.grupal.application.service;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.application.interceptor.Auditoria;
import uce.edu.grupal.domain.model.Cliente;
import uce.edu.grupal.domain.model.Pago;
import uce.edu.grupal.domain.model.ReservaVehiculo;
import uce.edu.grupal.domain.model.Vehiculo;
import uce.edu.grupal.domain.model.Vendedor;
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
        if (c.getPago() != null && c.getPago().getId() != null) {
            c.setPago(this.ps.buscarPorId(c.getPago().getId()));
        }
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

        completableCliente.join();

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
    if (r == null) {
        throw new IllegalArgumentException("La reserva no puede ser nula");
    }
    ReservaVehiculo nuevo = this.rri.findById(id);
    if (nuevo == null) {
        throw new IllegalArgumentException("Reserva con id " + id + " no encontrada");
    }
    if (r.getFecha() != null)  nuevo.setFecha(r.getFecha());
    if (r.getEstado() != null) nuevo.setEstado(r.getEstado());
    if (r.getCliente() != null) {
        if (r.getCliente().getId() == null) {
            throw new IllegalArgumentException("El cliente debe tener un id para actualizar la reserva");
        }
        Cliente cli = this.cs.buscarPorId(r.getCliente().getId());
        if (cli == null) {
            throw new IllegalArgumentException("Cliente con id " + r.getCliente().getId() + " no encontrado");
        }
        nuevo.setCliente(cli);
    }
    if (r.getVehiculo() != null) {
        if (r.getVehiculo().getId() == null) {
            throw new IllegalArgumentException("El vehiculo debe tener un id para actualizar la reserva");
        }
        Vehiculo veh = this.vs.buscarPorId(r.getVehiculo().getId());
        if (veh == null) {
            throw new IllegalArgumentException("Vehiculo con id " + r.getVehiculo().getId() + " no encontrado");
        }
        nuevo.setVehiculo(veh);
    }
    if (r.getVendedor() != null) {
        if (r.getVendedor().getId() == null) {
            throw new IllegalArgumentException("El vendedor debe tener un id para actualizar la reserva");
        }
        Vendedor vend = this.vends.buscarPorId(r.getVendedor().getId());
        if (vend == null) {
            throw new IllegalArgumentException("Vendedor con id " + r.getVendedor().getId() + " no encontrado");
        }
        nuevo.setVendedor(vend);
    }
    if (r.getPago() != null) {
        if (r.getPago().getId() == null) {
            throw new IllegalArgumentException("El pago debe tener un id para actualizar la reserva");
        }
        Pago pago = this.ps.buscarPorId(r.getPago().getId());
        if (pago == null) {
            throw new IllegalArgumentException("Pago con id " + r.getPago().getId() + " no encontrado");
        }
        nuevo.setPago(pago);
    }
    this.rri.getEntityManager().merge(nuevo);
}


    public ReservaVehiculo buscarPorId(Integer id) {
        return this.rri.findById(id);
    }

    public List<ReservaVehiculo> buscarTodos() {
        return this.rri.findAll().list();
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        ReservaVehiculo r = this.rri.findById(id);
        if (r == null) {
            throw new IllegalArgumentException("Reserva con id " + id + " no encontrada");
        }
        this.rri.delete(r);
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
