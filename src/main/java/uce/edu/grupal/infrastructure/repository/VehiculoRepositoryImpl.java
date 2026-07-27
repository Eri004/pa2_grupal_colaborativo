package uce.edu.grupal.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.grupal.domain.model.Vehiculo;

@ApplicationScoped
public class VehiculoRepositoryImpl implements PanacheRepositoryBase<Vehiculo, Integer> {

    public Vehiculo buscarPorPlaca(String placa) {
        return find("placa", placa).firstResult();
    }

    
}