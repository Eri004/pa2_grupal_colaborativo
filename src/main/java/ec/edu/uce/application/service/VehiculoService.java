package ec.edu.uce.application.service;

import java.util.List;

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

    @Inject
    private SucursalService sucursalService;

    public void guardarVeh(Vehiculo vehiculo){
        this.vehiculoRepositoryImpl.persist(vehiculo);
    }

    public List<Vehiculo> buscarTodos(){
        return this.vehiculoRepositoryImpl.findAll().list();
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return this.vehiculoRepositoryImpl.buscarPorPlaca(placa);
    }

    public Vehiculo buscarPorIdVeh(Integer id){
        return this.vehiculoRepositoryImpl.findById(id);
    }

    public Vehiculo actualizarVeh(Vehiculo veh, Integer id){
        
        Vehiculo nuevo = this.buscarPorIdVeh(id);

        if(nuevo != null){
            nuevo.setModelo(veh.getModelo());
            nuevo.setMarca(veh.getMarca());
            nuevo.setPlaca(veh.getPlaca());
            nuevo.setEstado(veh.getEstado());
            nuevo.setSucursal(this.sucursalService.actualizarSuc(veh.getSucursal(), veh.getSucursal().getId()));
        }

        return nuevo;
    }

    public void eliminarPorId(Integer id){
        this.vehiculoRepositoryImpl.delete(this.buscarPorIdVeh(id));
    }

}
