package ec.edu.uce.infraestructure.repository;

import java.util.List;

import ec.edu.uce.domain.model.ReservaVehiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ReservaVehiculoRepositoryImpl implements PanacheRepositoryBase<ReservaVehiculo, Integer> {

    public List<ReservaVehiculo> buscarPorPlaca(String placa){
        return find("vehiculo.placa", placa).list();
    }
}
