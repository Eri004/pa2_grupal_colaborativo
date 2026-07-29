package uce.edu.grupal.infrastructure.repository;

import java.time.LocalDate;
import java.util.List;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import uce.edu.grupal.domain.model.ReservaVehiculo;

@ApplicationScoped
public class ReservaVehiculoRepositoryImpl implements PanacheRepositoryBase<ReservaVehiculo, Integer> {

    @Inject
    private EntityManager em;

    public ReservaVehiculo buscarPorFecha(LocalDate fecha){

        TypedQuery<ReservaVehiculo> miQuery = this.em.createQuery("SELECT r FROM Reserva r WHERE r.fecha = :fecha", ReservaVehiculo.class);
        miQuery.setParameter("fecha", fecha);

        return miQuery.getSingleResult();

    }

}
