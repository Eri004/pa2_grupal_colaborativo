package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Cliente;
import uce.edu.grupal.infrastructure.repository.ClienteRepositoryImpl;
import uce.edu.grupal.infrastructure.repository.ReservaVehiculoRepositoryImpl;

@ApplicationScoped
@Transactional
public class ClienteService {

    @Inject
    private ClienteRepositoryImpl cr;

    @Inject
    private ReservaVehiculoRepositoryImpl rri;

    public void guardar(Cliente cliente) {
        this.cr.persist(cliente);
    }

    public void actualizar(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        Cliente nuevo = this.cr.buscarPorCedula(cliente.getCedula());
        if (nuevo == null) {
            throw new IllegalArgumentException("Cliente con cedula " + cliente.getCedula() + " no encontrado");
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

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        Cliente cli = this.cr.findById(id);
        if (cli == null) {
            throw new IllegalArgumentException("Cliente con id " + id + " no encontrado");
        }
        long reservas = this.rri.count("cliente.id", id);
        if (reservas > 0) {
            throw new IllegalArgumentException("No se puede eliminar: el cliente tiene " + reservas + " reserva(s) asociada(s)");
        }
        this.cr.delete(cli);
    }

    public void eliminarPorCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("La cedula no puede ser nula o vacia");
        }
        Cliente c = this.cr.buscarPorCedula(cedula);
        if (c == null) {
            throw new IllegalArgumentException("Cliente con cedula " + cedula + " no encontrado");
        }
        long reservas = this.rri.count("cliente.id", c.getId());
        if (reservas > 0) {
            throw new IllegalArgumentException("No se puede eliminar: el cliente tiene " + reservas + " reserva(s) asociada(s)");
        }
        this.cr.delete(c);
    }

    public List<Cliente> buscarTodos() {
        return this.cr.listAll();
    }


}
