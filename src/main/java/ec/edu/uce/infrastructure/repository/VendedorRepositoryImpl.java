package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.Vendedor;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class VendedorRepositoryImpl implements PanacheRepositoryBase<Vendedor, Integer> {

    @Inject
    private EntityManager em;

    public Vendedor buscarPorCedula(String cedulaVendedor){
        TypedQuery<Vendedor> query = this.em.createNamedQuery("Vendedor.buscarPorCedula", Vendedor.class);
        query.setParameter("cedula", cedulaVendedor);
        return query.getSingleResult();
    }

}
