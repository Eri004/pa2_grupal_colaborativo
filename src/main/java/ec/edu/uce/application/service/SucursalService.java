package ec.edu.uce.application.service;


import java.util.List;

import ec.edu.uce.domain.model.Sucursal;
import ec.edu.uce.infrastructure.repository.SucursalRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class SucursalService {

    @Inject
    private SucursalRepositoryImpl sucursalRepositoryImpl;

    public void guardarSuc(Sucursal sucursal){
        this.sucursalRepositoryImpl.persist(sucursal);
    }

    public List<Sucursal> buscarTodos(){
        return this.sucursalRepositoryImpl.findAll().list();
    }

    public Sucursal buscarPorNombre(String nombreSuc) {
        return this.sucursalRepositoryImpl.buscarPorNombre(nombreSuc);
    }

    public Sucursal buscarPorIdSuc(Integer id){
        return this.sucursalRepositoryImpl.findById(id);
    }

    public Sucursal actualizarSuc(Sucursal suc, Integer id){
        
        Sucursal nuevo = this.buscarPorIdSuc(id);

        if(nuevo != null){
            nuevo.setNombre(suc.getNombre());
            nuevo.setDireccion(suc.getDireccion());
        }

        return nuevo;
    }

    public void eliminarPorId(Integer id){
        this.sucursalRepositoryImpl.delete(this.buscarPorIdSuc(id));
    }

}
