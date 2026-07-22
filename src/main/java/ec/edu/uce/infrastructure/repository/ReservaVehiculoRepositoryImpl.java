package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.ReservaVehiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class ReservaVehiculoRepositoryImpl implements PanacheRepositoryBase<ReservaVehiculo, Integer> {

    @Inject
    private EntityManager em;

    public ReservaVehiculo buscarPorCedula(String cedulaVendedor){
        TypedQuery<ReservaVehiculo> query = this.em.createNamedQuery("ReservaVehiculo.buscarPorCedula", ReservaVehiculo.class);
        query.setParameter("cedulaVendedor", cedulaVendedor);
        return query.getSingleResult();
    }

}
