package uce.edu.grupal.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import uce.edu.grupal.domain.model.Vendedor;

@ApplicationScoped
public class VendedorRepositoryImpl implements PanacheRepositoryBase<Vendedor, Integer> {

    @Inject
    private EntityManager em;

    public Vendedor buscarPorCedula(String cedula){

        TypedQuery<Vendedor> miQuery = this.em.createQuery("SELECT v FROM Vendedor v WHERE v.cedula = :cedula", Vendedor.class);
        miQuery.setParameter("cedula", cedula);
        return miQuery.getSingleResult();

    }

}
