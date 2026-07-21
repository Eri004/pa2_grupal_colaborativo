package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.ReservaVehiculo;
import ec.edu.uce.infrastructure.repository.ReservaVehiculoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReservaVehiculoService {

    @Inject
    private ReservaVehiculoRepositoryImpl reservaVehiculo;

    public void guardarRes(ReservaVehiculo reservaVehiculo) {
        this.reservaVehiculo.persist(reservaVehiculo);
    }

    public ReservaVehiculo buscarPorIdRes(Integer id) {
        return this.reservaVehiculo.findById(id);
    }

    public void actualizarCli(ReservaVehiculo res, Integer id) {

        ReservaVehiculo nuevo = this.buscarPorIdRes(id);

        if (nuevo != null) {
            nuevo.setCedulaVendedor(res.getCedulaVendedor());
            nuevo.setPlacaVehiculo(res.getPlacaVehiculo());;
            nuevo.setFecha(res.getFecha());;
        }

    }

    public void eliminarPorIdRes(Integer id) {
        this.reservaVehiculo.delete(this.buscarPorIdRes(id));
    }

}
