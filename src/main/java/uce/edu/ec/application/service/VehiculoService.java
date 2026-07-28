package uce.edu.ec.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.Vehiculo;
import uce.edu.ec.infraestructure.repository.VehiculoRepositoryImpl;

@ApplicationScoped
@Transactional
public class VehiculoService {
    @Inject
    private VehiculoRepositoryImpl vri;

    public Vehiculo buscarPorPlaca(String placa){
        return this.vri.buscarPorPlaca(placa);
    }

    public boolean validarVehiculo(String placa){
        if(this.buscarPorPlaca(placa) == null){
            System.out.println("No existe un vehiculo con esa placa: " + placa);
            return false;
        }else{
            return true;
        }
    }

    public void guardar(Vehiculo vehiculo){
        this.vri.persist(vehiculo);
    }
    public void eliminar(Integer id){
        this.vri.deleteById(id);
    }
    public void actualizar(String placa, Vehiculo vehiculo){
        Vehiculo vehiculoBase = this.buscarPorPlaca(placa);
        if (vehiculoBase == null) {
            return;
        }
       
        vehiculoBase.setValor(vehiculo.getValor());
        vehiculoBase.setAños(vehiculo.getAños());
        vehiculoBase.setEsResevado(vehiculo.getEsResevado());
        vehiculoBase.setMarca(vehiculo.getMarca());
    }

}
