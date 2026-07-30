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

    public void actualizar(Integer id, Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        Cliente nuevo = this.cr.findById(id);
        if (nuevo == null) {
            throw new IllegalArgumentException("Cliente con id " + id + " no encontrado");
        }
        if (cliente.getCedula() != null && !cliente.getCedula().equals(nuevo.getCedula())) {
            Cliente existe = this.cr.find("cedula", cliente.getCedula()).firstResult();
            if (existe != null) {
                throw new IllegalArgumentException("Ya existe un cliente con la cédula " + cliente.getCedula());
            }
            nuevo.setCedula(cliente.getCedula());
        }
        if (cliente.getCorreo() != null && !cliente.getCorreo().equals(nuevo.getCorreo())) {
            Cliente existe = this.cr.find("correo", cliente.getCorreo()).firstResult();
            if (existe != null) {
                throw new IllegalArgumentException("Ya existe un cliente con el correo " + cliente.getCorreo());
            }
            nuevo.setCorreo(cliente.getCorreo());
        }
        if (cliente.getNombres() != null)   nuevo.setNombres(cliente.getNombres());
        if (cliente.getApellidos() != null) nuevo.setApellidos(cliente.getApellidos());
        if (cliente.getTelefono() != null)  nuevo.setTelefono(cliente.getTelefono());
        this.cr.getEntityManager().merge(nuevo);
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
