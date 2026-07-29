package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Vehiculo;
import ec.edu.uce.infrastructure.repository.VehiculoRepositoryImpl;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
@Transactional
public class VehiculoService {

    @Inject
    private VehiculoRepositoryImpl vrl;

    public void guardar(Vehiculo c){

        this.vrl.persist(c);

    }

    public void actualizar(Integer id, Vehiculo c){

        Vehiculo nuevo = this.vrl.findById(id);

        nuevo.setPlaca(c.getPlaca());

    }

    public Vehiculo buscarPorId(Integer id){

        return this.vrl.findById(id);

    }

    public void eliminar(Integer id){

        this.vrl.deleteById(id);

    }

    public Vehiculo buscarPorPlaca(String placa){

        return this.vrl.buscarPorPlaca(placa);
        
    }

    public Uni<Vehiculo> buscarPorPlacaPromesa(String placa) {

        return Uni.createFrom().item(this.vrl.buscarPorPlaca(placa));

    }

}
