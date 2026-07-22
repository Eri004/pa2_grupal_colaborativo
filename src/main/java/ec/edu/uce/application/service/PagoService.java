package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.domain.model.Pago;
import ec.edu.uce.infrastructure.repository.PagoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class PagoService {

    @Inject
    private PagoRepositoryImpl pagoRepositoryImpl;

    public void guardarPago(Pago pago){
        this.pagoRepositoryImpl.persist(pago);
    }

    public Pago buscarPorIdPago(Integer id){
        return this.pagoRepositoryImpl.findById(id);
    }

    public Pago actualizarPago(Pago pago, Integer id){
        
        Pago nuevo = this.buscarPorIdPago(id);

        if(nuevo != null){
            nuevo.setEstado(pago.getEstado());
            nuevo.setMetodoPago(pago.getMetodoPago());;
            nuevo.setMonto(pago.getMonto());;
        }
        return nuevo;
    }

    public List<Pago> buscarTodos(){
        return this.pagoRepositoryImpl.findAll().list();
    }

    public void eliminarPorId(Integer id){
        this.pagoRepositoryImpl.delete(this.buscarPorIdPago(id));
    }

}
