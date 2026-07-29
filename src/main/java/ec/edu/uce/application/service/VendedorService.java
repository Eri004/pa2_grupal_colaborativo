package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Vendedor;
import ec.edu.uce.infrastructure.repository.VendedorRepositoryImpl;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VendedorService {

    @Inject
    private VendedorRepositoryImpl vrl;

    public void guardar(Vendedor c){

        this.vrl.persist(c);

    }

    public void actualizar(Integer id, Vendedor c){

        Vendedor nuevo = this.vrl.findById(id);

        nuevo.setCedula(c.getCedula());

    }

    public Vendedor buscarPorId(Integer id){

        return this.vrl.findById(id);

    }

    public void eliminar(Integer id){

        this.vrl.deleteById(id);

    }

    public Vendedor buscarPorCedula(String cedula){

        return this.vrl.buscarPorCedula(cedula);

    }
    
    public Uni<Vendedor> buscarPorCedulaPromesa(String cedula) {

        return Uni.createFrom().item(this.vrl.buscarPorCedula(cedula));

    }

}
