package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Vehiculo;
import uce.edu.grupal.infrastructure.repository.VehiculoRepositoryImpl;

@ApplicationScoped
@Transactional
public class VehiculoService {

    @Inject
    private VehiculoRepositoryImpl vr;

    public void guardar(Vehiculo vehiculo) {
        this.vr.persist(vehiculo);
    }

    public void actualizarPorPlaca(String placa, Vehiculo vehiculo) {
        Vehiculo v = this.vr.buscarPorPlaca(vehiculo.getPlaca());

        v.setPlaca(vehiculo.getPlaca());
        v.setMarca(vehiculo.getMarca());
        v.setModelo(vehiculo.getModelo());
    }

    public void actualizar(Integer id, Vehiculo c){
        Vehiculo nuevo = this.vr.findById(id);
        nuevo.setPlaca(c.getPlaca());

    }

    public Vehiculo buscarPorId(Integer id) {
        return this.vr.findById(id);
    }

    public List<Vehiculo> buscarTodos() {
        return this.vr.listAll();
    }

    public Vehiculo buscarPorPlaca(String placa){

        return this.vr.buscarPorPlaca(placa);
        
    }

    public void eliminar(Integer id){
        Vehiculo vehiculo = this.buscarPorId(id);
        this.vr.delete(vehiculo);
    }
    
    public void eliminarPorPlaca(String placa) {
        Vehiculo v = this.vr.buscarPorPlaca(placa);
        this.vr.delete(v);
    }

}
