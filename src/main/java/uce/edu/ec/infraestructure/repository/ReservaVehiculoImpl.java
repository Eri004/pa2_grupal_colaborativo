package uce.edu.ec.infraestructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.ReservaVehiculo;

@ApplicationScoped
@Transactional
public class ReservaVehiculoImpl implements PanacheRepositoryBase<ReservaVehiculo,Integer>{

}
