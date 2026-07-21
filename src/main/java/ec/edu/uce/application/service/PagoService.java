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
    private PagoRepositoryImpl pagoRepositoryImpl;

    public void guardarPago(Pago pago){
        this.pagoRepositoryImpl.persist(pago);
    }

    public Pago buscarPorIdPago(Integer id){
        return this.pagoRepositoryImpl.findById(id);
    }

    public void actualizarCli(Pago pago, Integer id){
        
        Pago nuevo = this.buscarPorIdPago(id);

        if(nuevo != null){
            nuevo.setEstado(pago.getEstado());
            nuevo.setMetodoPago(pago.getMetodoPago());;
            nuevo.setMonto(pago.getMonto());;
        }

    }

    public void eliminarPorId(Integer id){
        this.pagoRepositoryImpl.delete(this.buscarPorIdPago(id));
    }

}
