package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.Vehiculo;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VehiculoRepositoryImpl implements PanacheRepositoryBase<Vehiculo, Integer>{

    @Inject
    private EntityManager em;

    public Vehiculo buscarPorPlaca(String placa){

        TypedQuery<Vehiculo> miQuery = this.em.createQuery("SELECT v FROM Vehiculo v WHERE v.placa = :placa", Vehiculo.class);
        miQuery.setParameter("placa", placa);

        return miQuery.getSingleResult();

    }

}
