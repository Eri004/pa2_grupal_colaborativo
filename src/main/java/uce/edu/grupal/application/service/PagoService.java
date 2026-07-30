package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Pago;
import uce.edu.grupal.infrastructure.repository.PagoRepositoryImpl;

@ApplicationScoped
@Transactional
public class PagoService {

    @Inject
    private PagoRepositoryImpl pri;

    public void guardar(Pago c) {
        this.pri.persist(c);
    }

    public void actualizar(Integer id, Pago p) {
        if (p == null) {
            throw new IllegalArgumentException("El pago no puede ser nulo");
        }
        Pago nuevo = this.pri.findById(id);
        if (nuevo == null) {
            throw new IllegalArgumentException("Pago con id " + id + " no encontrado");
        }
        if (p.getMonto() != null)      nuevo.setMonto(p.getMonto());
        if (p.getMetodoPago() != null) nuevo.setMetodoPago(p.getMetodoPago());
        this.pri.getEntityManager().merge(nuevo);
    }

    public List<Pago> buscarTodos() {
        return this.pri.findAll().list();
    }

    public Pago buscarPorId(Integer id) {
        return this.pri.findById(id);

    }

    public void eliminar(Integer id) {

        this.pri.deleteById(id);

    }

}
