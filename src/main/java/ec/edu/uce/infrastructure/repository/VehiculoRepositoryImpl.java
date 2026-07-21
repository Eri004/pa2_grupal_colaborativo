package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.Vehiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VehiculoRepositoryImpl implements PanacheRepositoryBase<Vehiculo, Integer> {

  
}
