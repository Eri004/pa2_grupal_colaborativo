package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.Sucursal;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class SucursalRepositoryImpl implements PanacheRepositoryBase<Sucursal, Integer> {

    @Inject
    private EntityManager em;

    public Sucursal buscarPorNombre(String nombreSuc){
        TypedQuery<Sucursal> query = this.em.createNamedQuery("Sucursal.buscarPorNombre", Sucursal.class);
        query.setParameter("nombre", nombreSuc);
        return query.getSingleResult();
    }

}
