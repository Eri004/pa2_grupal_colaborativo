package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Pago;
import ec.edu.uce.infrastructure.repository.PagoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PagoService {

    @Inject
    private PagoRepositoryImpl pri;

    public void guardar(Pago c){

        this.pri.persist(c);

    }

    public void actualizar(Integer id, Pago p){

        Pago nuevo = this.pri.findById(id);

        nuevo.setMonto(p.getMonto());
        nuevo.setMetodoPago(p.getMetodoPago());
    

    }

    public Pago buscarPorId(Integer id){

        return this.pri.findById(id);

    }

    public void eliminar(Integer id){

        this.pri.deleteById(id);

    }


}
