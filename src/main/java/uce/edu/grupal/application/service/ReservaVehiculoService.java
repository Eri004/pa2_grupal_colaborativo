package uce.edu.grupal.application.service;

import java.time.LocalDate;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Cliente;
import uce.edu.grupal.domain.model.Empleado;
import uce.edu.grupal.domain.model.ReservaVehiculo;
import uce.edu.grupal.domain.model.Vehiculo;
import uce.edu.grupal.infrastructure.repository.ReservaVehiculoRepositoryImpl;
import uce.edu.grupal.web.resources.request.ReservaRequestDTO;

@ApplicationScoped
@Transactional
public class ReservaVehiculoService {

    @Inject
    private ReservaVehiculoRepositoryImpl rr;

    @Inject
    private ClienteService cs;

    @Inject
    private EmpleadoService es;

    @Inject
    private VehiculoService vs;

    public void guardar(ReservaRequestDTO dto) {

        Cliente cliente = this.cs.buscarPorCedula(dto.getCedulaCliente());

        if (cliente == null) {
            throw new RuntimeException("No existe el cliente: " + dto.getCedulaCliente());
        }

        Vehiculo vehiculo = this.vs.buscarPorPlaca(dto.getPlacaVehiculo());

        if (vehiculo == null) {
            throw new RuntimeException("No existe el vehículo: " + dto.getPlacaVehiculo());
        }

        Empleado empleado = this.es.buscarPorCedula(dto.getCedulaEmpleado());

        if (empleado == null) {
            throw new RuntimeException("No existe el empleado: " + dto.getCedulaEmpleado());
        }

        if (!cliente.getEstado().equals("ACTIVO")) {
            throw new RuntimeException("El cliente no está activo");
        }

        if (!empleado.getEstado().equals("ACTIVO")) {
            throw new RuntimeException("El empleado no está activo");
        }

        if (!vehiculo.getEstado().equals("DISPONIBLE")) {
            throw new RuntimeException("El vehículo no está disponible");
        }

        ReservaVehiculo reserva = new ReservaVehiculo();

        reserva.setCliente(cliente);
        reserva.setVehiculo(vehiculo);
        reserva.setEmpleado(empleado);
        reserva.setFecha(LocalDate.now());

        reserva.setEstado("CONFIRMADA");
        reserva.setCodigoReserva(generarCodigoReserva());

        this.rr.persist(reserva);
    }

    public void guardar(ReservaVehiculo reserva) {
        if (!reserva.getCliente().getEstado().equals("ACTIVO")) {
            throw new RuntimeException("El cliente no está activo");
        }

        if (!reserva.getEmpleado().getEstado().equals("ACTIVO")) {
            throw new RuntimeException("El empleado no está activo");
        }

        if (!reserva.getVehiculo().getEstado().equals("DISPONIBLE")) {
            throw new RuntimeException("El vehículo no está disponible");
        }

        reserva.setEstado("CONFIRMADA");
        reserva.setCodigoReserva(generarCodigoReserva());

        this.rr.persist(reserva);
    }

    public ReservaVehiculo buscarPorId(Integer id) {
        return this.rr.findById(id);
    }

    public List<ReservaVehiculo> buscarTodos() {
        return this.rr.listAll();
    }

    public List<ReservaVehiculo> buscarPorFecha(LocalDate fecha) {
        return this.rr.buscarPorFecha(fecha);
    }

    public List<ReservaVehiculo> buscarPorCedulaCliente(String cedula) {
        return this.rr.buscarPorCedulaCliente(cedula);
    }

    public List<ReservaVehiculo> buscarPorPlaca(String placa) {
        return this.rr.buscarPorPlaca(placa);
    }

    public void eliminar(String codigoReserva) {

        ReservaVehiculo reserva = this.rr.buscarPorCodigo(codigoReserva);

        if (reserva == null) {
            throw new RuntimeException("No existe la reserva: " + codigoReserva);
        }

        reserva.setEstado("CANCELADA");
    }

    public void actualizar(ReservaVehiculo reserva) {

        ReservaVehiculo r = this.rr.findById(reserva.getId());

        r.setCliente(reserva.getCliente());
        r.setEmpleado(reserva.getEmpleado());
        r.setVehiculo(reserva.getVehiculo());
        r.setFecha(reserva.getFecha());
        r.setEstado(reserva.getEstado());

    }

    public void actualizar(String codigo, ReservaRequestDTO dto) {

        ReservaVehiculo reserva = this.rr.buscarPorCodigo(codigo);

        Cliente cliente = cs.buscarPorCedula(dto.getCedulaCliente());

        Vehiculo vehiculo = vs.buscarPorPlaca(dto.getPlacaVehiculo());

        Empleado empleado = es.buscarPorCedula(dto.getCedulaEmpleado());

        reserva.setCliente(cliente);
        reserva.setVehiculo(vehiculo);
        reserva.setEmpleado(empleado);
        reserva.setFecha(dto.getFecha());
        reserva.setEstado(dto.getEstado());

    }

    private String generarCodigoReserva() {

        Long numero = this.rr.count() + 1;

        return String.format("RES-%06d", numero);
    }

    public ReservaVehiculo buscarPorCodigo(String codigoReserva) {
        return this.rr.buscarPorCodigo(codigoReserva);
    }
}
