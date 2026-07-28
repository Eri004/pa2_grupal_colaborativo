package uce.edu.ec.infraestructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.Vendedor;

@ApplicationScoped
@Transactional
public class VendedorRepositoryImpl implements PanacheRepositoryBase<Vendedor, Integer>{

    @Inject
    private EntityManager em;
    public Vendedor buscarPorCedula(String cedula){
        TypedQuery<Vendedor> consulta = this.em.createQuery("Select v From Vendedor v WHERE v.cedula LIKE :cedula", Vendedor.class);
        consulta.setParameter("cedula", cedula);
        return consulta.getResultStream().findFirst().orElse(null);

    }
}
