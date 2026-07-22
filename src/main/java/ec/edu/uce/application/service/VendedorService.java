package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.domain.model.Vendedor;
import ec.edu.uce.infrastructure.repository.VendedorRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VendedorService {

    @Inject
    private VendedorRepositoryImpl vendedorRepositoryImpl;

    public void guardarVen(Vendedor vendedor){
        this.vendedorRepositoryImpl.persist(vendedor);
    }

    public List<Vendedor> buscarTodos(){
        return this.vendedorRepositoryImpl.findAll().list();
    }

    public Vendedor buscarPorCedulaVen(String cedulaVend) {
        return this.vendedorRepositoryImpl.buscarPorCedula(cedulaVend);
    }

    public Vendedor buscarPorIdVen(Integer id){
        return this.vendedorRepositoryImpl.findById(id);
    }

    public Vendedor actualizarVen(Vendedor ven, Integer id){
        
        Vendedor nuevo = this.buscarPorIdVen(id);

        if(nuevo != null){
            nuevo.setNombre(ven.getNombre());
            nuevo.setCedula(ven.getCedula());
        }
        return nuevo;
    }

    public void eliminarPorId(Integer id){
        this.vendedorRepositoryImpl.delete(this.buscarPorIdVen(id));
    }

}
