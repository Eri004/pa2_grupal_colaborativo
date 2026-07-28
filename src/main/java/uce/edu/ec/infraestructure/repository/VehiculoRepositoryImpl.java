package uce.edu.ec.infraestructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.Vehiculo;

@ApplicationScoped
@Transactional
public class VehiculoRepositoryImpl implements PanacheRepositoryBase<Vehiculo, Integer> {
    @Inject
    private EntityManager em;

    public Vehiculo buscarPorPlaca(String placa){
        TypedQuery<Vehiculo> consulta = this.em.createQuery("Select v From Vehiculo v Where v.placa LIKE :placa", Vehiculo.class);
        consulta.setParameter("placa", placa);
        return consulta.getResultStream().findFirst().orElse(null);

    }

}
