package uce.edu.grupal.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import uce.edu.grupal.domain.model.Cliente;

@ApplicationScoped
public class ClienteRepositoryImpl implements PanacheRepositoryBase<Cliente, Integer> {

    public Cliente buscarPorCedula(String cedula) {
        return find("cedula", cedula).firstResult();
    }
}
