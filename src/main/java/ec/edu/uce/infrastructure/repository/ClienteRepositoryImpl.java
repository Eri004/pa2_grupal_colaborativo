package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.Cliente;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@ApplicationScoped
public class ClienteRepositoryImpl implements PanacheRepositoryBase<Cliente, Integer> {

    @Inject
    private EntityManager em;

    public Cliente buscarPorCedula(String cedulaCliente){
        TypedQuery<Cliente> query = this.em.createNamedQuery("Cliente.buscarPorCedula", Cliente.class);
        query.setParameter("cedula", cedulaCliente);
        return query.getSingleResult();
    }

}
