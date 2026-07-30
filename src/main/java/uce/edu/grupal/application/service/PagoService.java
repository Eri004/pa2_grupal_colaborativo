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
        Pago nuevo = this.pri.findById(id);
        nuevo.setMonto(p.getMonto());
        nuevo.setMetodoPago(p.getMetodoPago());

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
