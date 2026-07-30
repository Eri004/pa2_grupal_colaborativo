package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Vendedor;
import uce.edu.grupal.infrastructure.repository.VendedorRepositoryImpl;

@ApplicationScoped
@Transactional
public class VendedorService {

    @Inject
    private VendedorRepositoryImpl er;

    public void guardar(Vendedor empleado) {
        this.er.persist(empleado);
    }

    public Vendedor buscarPorId(Integer id) {
        return this.er.findById(id);
    }

    public List<Vendedor> buscarTodos() {
        return this.er.listAll();
    }

    public void eliminarPorCedula(String cedula) {
        Vendedor e = this.er.buscarPorCedula(cedula);
        this.er.delete(e);
    }

    public void eliminar(Integer id) {
        Vendedor vendedor = this.buscarPorId(id);
        this.er.delete(vendedor);
    }

    public void actualizar(Integer id, Vendedor c) {

    // Este metodo exije enviar todo el Json
        
        //Validaciones
            // Validar que el objeto Vendedor no sea nulo
        if (c == null) {
            throw new IllegalArgumentException("El vendedor no puede ser nulo");
        }
        Vendedor nuevo = this.er.findById(id);
            // Validar que el objeto Vendedor con el id proporcionado exista
        if (nuevo == null) {
            throw new IllegalArgumentException("Vendedor con id " + id + " no encontrado");
        }

            // Validar que la cédula del objeto Vendedor no sea nula o vacía
        if (!nuevo.getCedula().equals(c.getCedula())) {
            Vendedor existente = this.er.find("cedula", c.getCedula()).firstResult();
            if (existente != null) {
                throw new IllegalArgumentException("Ya existe un vendedor con la cédula " + c.getCedula());
            }
        }
        nuevo.setCedula(c.getCedula());
        nuevo.setNombres(c.getNombres());
        nuevo.setApellidos(c.getApellidos());
        nuevo.setCargo(c.getCargo());
        // Actualizar el objeto Vendedor en la base de datos
        this.er.getEntityManager().merge(nuevo);
    }

    public Vendedor buscarPorCedula(String cedula) {
        return this.er.buscarPorCedula(cedula);
    }

}
