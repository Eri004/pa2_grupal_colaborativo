package uce.edu.grupal.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.grupal.domain.model.Pago;

@ApplicationScoped
public class PagoRepositoryImpl implements PanacheRepositoryBase<Pago, Integer> {

    public Pago buscarPorReserva(Integer idReserva) {
        return find("reserva.id", idReserva).firstResult();
    }

    public Pago buscarPorFactura(String numeroFactura) {
        return find("numeroFactura", numeroFactura).firstResult();
    }
}
