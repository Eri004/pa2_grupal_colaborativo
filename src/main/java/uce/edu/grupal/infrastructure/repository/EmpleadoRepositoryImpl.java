package uce.edu.grupal.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.grupal.domain.model.Empleado;

@ApplicationScoped
public class EmpleadoRepositoryImpl implements PanacheRepositoryBase<Empleado, Integer> {

    public Empleado buscarPorCedula(String cedula) {
        return find("cedula", cedula).firstResult();
    }
}
