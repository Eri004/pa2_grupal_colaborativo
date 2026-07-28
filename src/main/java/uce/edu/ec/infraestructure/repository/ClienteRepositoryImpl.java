package uce.edu.ec.infraestructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.Cliente;
@ApplicationScoped
@Transactional
public class ClienteRepositoryImpl implements PanacheRepositoryBase<Cliente,Integer>{
    @Inject
    private EntityManager em;
    public Cliente buscarPorCedula(String cedula){
        TypedQuery<Cliente> consulta = this.em.createQuery("Select c From Cliente c WHERE c.cedula LIKE :cedula", Cliente.class);
        consulta.setParameter("cedula", cedula);
        return consulta.getResultStream().findFirst().orElse(null);

    }

}
