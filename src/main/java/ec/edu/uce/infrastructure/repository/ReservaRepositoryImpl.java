package ec.edu.uce.infrastructure.repository;

import java.time.LocalDate;

import ec.edu.uce.domain.model.Reserva;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class ReservaRepositoryImpl implements PanacheRepositoryBase<Reserva, Integer>{

    @Inject
    private EntityManager em;

    public Reserva buscarPorFecha(LocalDate fecha){

        TypedQuery<Reserva> miQuery = this.em.createQuery("SELECT r FROM Reserva r WHERE r.fecha = :fecha", Reserva.class);
        miQuery.setParameter("fecha", fecha);

        return miQuery.getSingleResult();

    }

}
