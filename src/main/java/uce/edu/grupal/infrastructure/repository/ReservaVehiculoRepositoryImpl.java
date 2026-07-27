package uce.edu.grupal.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.grupal.domain.model.ReservaVehiculo;

@ApplicationScoped
public class ReservaVehiculoRepositoryImpl implements PanacheRepositoryBase<ReservaVehiculo, Integer> {

    public List<ReservaVehiculo> buscarPorFecha(LocalDate fecha) {
        return find("fecha", fecha).list();
    }

    public List<ReservaVehiculo> buscarPorCedulaCliente(String cedula) {
        return find("cliente.cedula", cedula).list();
    }

    public List<ReservaVehiculo> buscarPorPlaca(String placa) {
        return find("vehiculo.placa", placa).list();
    }

    public ReservaVehiculo buscarPorCodigo(String codigoReserva) {
        return find("codigoReserva", codigoReserva).firstResult();
    }
}
