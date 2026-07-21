package ec.edu.uce.application.service;

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

    public Vendedor buscarPorIdVen(Integer id){
        return this.vendedorRepositoryImpl.findById(id);
    }

    public void actualizarVen(Vendedor ven, Integer id){
        
        Vendedor nuevo = this.buscarPorIdVen(id);

        if(nuevo != null){
            nuevo.setNombre(ven.getNombre());
            nuevo.setCedula(ven.getCedula());
        }

    }

    public void eliminarPorId(Integer id){
        this.vendedorRepositoryImpl.delete(this.buscarPorIdVen(id));
    }

}
