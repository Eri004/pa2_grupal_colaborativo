package ec.edu.uce.infrastructure.repository;

import ec.edu.uce.domain.model.Pago;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PagoRepositoryImpl implements PanacheRepositoryBase<Pago, Integer> {

}
