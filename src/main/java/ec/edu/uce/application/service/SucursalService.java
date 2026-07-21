package ec.edu.uce.application.service;


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

    public Sucursal buscarPorIdSuc(Integer id){
        return this.sucursalRepositoryImpl.findById(id);
    }

    public void actualizarSuc(Sucursal suc, Integer id){
        
        Sucursal nuevo = this.buscarPorIdSuc(id);

        if(nuevo != null){
            nuevo.setNombre(suc.getNombre());
            nuevo.setDireccion(suc.getDireccion());
        }

    }

    public void eliminarPorId(Integer id){
        this.sucursalRepositoryImpl.delete(this.buscarPorIdSuc(id));
    }

}
