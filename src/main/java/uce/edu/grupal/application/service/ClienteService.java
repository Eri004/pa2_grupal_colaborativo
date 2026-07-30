package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Cliente;
import uce.edu.grupal.infrastructure.repository.ClienteRepositoryImpl;

@ApplicationScoped
@Transactional
public class ClienteService {

    @Inject
    private ClienteRepositoryImpl cr;

    public void guardar(Cliente cliente) {
        this.cr.persist(cliente);
    }

    public Cliente actualizar(Integer id, Cliente cliente) {
        Cliente c = this.cr.buscarPorCedula(cliente.getCedula());
        c.setCedula(cliente.getCedula());
        c.setNombres(cliente.getNombres());
        c.setApellidos(cliente.getApellidos());
        c.setTelefono(cliente.getTelefono());
        c.setCorreo(cliente.getCorreo());
        return c;

    }

    public Cliente buscarPorId(Integer id) {
        return this.cr.findById(id);
    }

    public Cliente buscarPorCedula(String cedula) {
        return this.cr.buscarPorCedula(cedula);
    }

    public void eliminar(Integer id){
        Cliente cli = this.buscarPorId(id);
        this.cr.delete(cli);
    }

    public void eliminarPorCedula(String cedula) {
        Cliente c = this.cr.buscarPorCedula(cedula);
        this.cr.delete(c);
    }

    public List<Cliente> buscarTodos() {
        return this.cr.listAll();
    }


}
