package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.domain.model.Vehiculo;
import ec.edu.uce.infraestructure.repository.VehiculoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class VehiculoService {

    @Inject
    private VehiculoRepositoryImpl vehiculoRepo;

    public void guardar(Vehiculo vehiculo){
        this.vehiculoRepo.persist(vehiculo);
    }

    public Vehiculo buscarPorId(Integer id){
        return this.vehiculoRepo.findById(id);
    }

    public void actualizar(Integer id, Vehiculo vehiculo){
        Vehiculo v = this.buscarPorId(id);
        if(v != null){
            v.setPlaca(vehiculo.getPlaca());
            v.setMarca(vehiculo.getMarca());
            v.setModelo(vehiculo.getModelo());
            v.setPrecioPorDia(vehiculo.getPrecioPorDia());
        }
    }

    public void eliminar(Integer id){
        Vehiculo vehiculo = this.buscarPorId(id);
        this.vehiculoRepo.delete(vehiculo);
    }

    public List<Vehiculo> buscarTodos(){
        return this.vehiculoRepo.listAll();
    }

    @Transactional
    public void guardarListaVehiculos(List<Vehiculo> vehiculos) {
        System.out.println(">>> [HILO VEHÍCULOS] Guardando " + vehiculos.size() 
            + " vehículos en Hilo: " + Thread.currentThread().getName());
        
        for (Vehiculo v : vehiculos) {
            v.persist(); // O tu método guardar individual
        }
    }
}
