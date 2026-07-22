package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.Vehiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class VehiculoRepositoryImpl implements PanacheRepositoryBase<Vehiculo, Integer> {

    @Inject
    private EntityManager em;

    public Vehiculo buscarPorPlaca(String placa){
        TypedQuery<Vehiculo> query = this.em.createNamedQuery("Vehiculo.buscarPorPlaca", Vehiculo.class);
        query.setParameter("placa", placa);
        return query.getSingleResult();
    }
  
}
