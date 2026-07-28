package uce.edu.ec.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.ReservaVehiculo;
import uce.edu.ec.infraestructure.repository.ReservaVehiculoImpl;

@ApplicationScoped
@Transactional
public class ReservaVehiculoService {
    @Inject 
    private ReservaVehiculoImpl rvi;
    
    public void guardar(ReservaVehiculo reserva){
        this.rvi.persist(reserva);
    }
    public void eliminar(Integer id){
        this.rvi.deleteById(id);
    }
    public ReservaVehiculo buscarPorId(Integer id){
        return this.rvi.findById(id);
    }
    

}
