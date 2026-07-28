package uce.edu.ec.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.Vendedor;
import uce.edu.ec.infraestructure.repository.VendedorRepositoryImpl;

@ApplicationScoped
@Transactional
public class VendedorService {
    @Inject
    private VendedorRepositoryImpl vri;
    public Vendedor buscarPorCedula(String cedula){
        return this.vri.buscarPorCedula(cedula);
    }
    public boolean validarVendedor(String cedula){
        if(this.buscarPorCedula(cedula) == null){
            System.out.println("No existe un vendedor con esa cedula: " + cedula);
            return false;

        }else{
            return true;
        }
    }
    public void guardar(Vendedor vendedor){
        this.vri.persist(vendedor);
    }
    public void eliminar(Integer id){
        this.vri.deleteById(id);
    }
    public void actualizar(Vendedor vendedor, String cedula){
        Vendedor vendedorBase = this.buscarPorCedula(cedula);
        if (vendedorBase == null) {
            return;
        }
        vendedorBase.setTelefono(vendedor.getTelefono());
        vendedorBase.setCorreo(vendedor.getCorreo());
        vendedorBase.setNombre(vendedor.getNombre());
    }

}
