package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Vehiculo;
import ec.edu.uce.infrastructure.repository.VehiculoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VehiculoService {

    @Inject
    private VehiculoRepositoryImpl vehiculoRepositoryImpl;

    public void guardarVeh(Vehiculo vehiculo){
        this.vehiculoRepositoryImpl.persist(vehiculo);
    }

    public Vehiculo buscarPorIdVeh(Integer id){
        return this.vehiculoRepositoryImpl.findById(id);
    }

    public void actualizarCli(Vehiculo veh, Integer id){
        
        Vehiculo nuevo = this.buscarPorIdVeh(id);

        if(nuevo != null){
            nuevo.setModelo(veh.getModelo());
            nuevo.setMarca(veh.getMarca());
            nuevo.setPlaca(veh.getPlaca());
            nuevo.setEstado(veh.getEstado());
        }

    }

    public void eliminarPorId(Integer id){
        this.vehiculoRepositoryImpl.delete(this.buscarPorIdVeh(id));
    }

}
