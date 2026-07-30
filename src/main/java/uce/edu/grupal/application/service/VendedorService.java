package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Vendedor;
import uce.edu.grupal.infrastructure.repository.ReservaVehiculoRepositoryImpl;
import uce.edu.grupal.infrastructure.repository.VendedorRepositoryImpl;

@ApplicationScoped
@Transactional
public class VendedorService {

    @Inject
    private VendedorRepositoryImpl er;

    @Inject
    private ReservaVehiculoRepositoryImpl rri;

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
        if (cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("La cedula no puede ser nula o vacia");
        }
        Vendedor e = this.er.buscarPorCedula(cedula);
        if (e == null) {
            throw new IllegalArgumentException("Vendedor con cedula " + cedula + " no encontrado");
        }
        long reservas = this.rri.count("vendedor.id", e.getId());
        if (reservas > 0) {
            throw new IllegalArgumentException("No se puede eliminar: el vendedor tiene " + reservas + " reserva(s) asociada(s)");
        }
        this.er.delete(e);
    }

    public void eliminar(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo");
        }
        Vendedor vendedor = this.er.findById(id);
        if (vendedor == null) {
            throw new IllegalArgumentException("Vendedor con id " + id + " no encontrado");
        }
        long reservas = this.rri.count("vendedor.id", id);
        if (reservas > 0) {
            throw new IllegalArgumentException("No se puede eliminar: el vendedor tiene " + reservas + " reserva(s) asociada(s)");
        }
        this.er.delete(vendedor);
    }

    public void actualizar(Integer id, Vendedor c) {
        if (c == null) {
            throw new IllegalArgumentException("El vendedor no puede ser nulo");
        }
        Vendedor nuevo = this.er.findById(id);
        if (nuevo == null) {
            throw new IllegalArgumentException("Vendedor con id " + id + " no encontrado");
        }
        if (c.getCedula() != null && !c.getCedula().equals(nuevo.getCedula())) {
            Vendedor existente = this.er.find("cedula", c.getCedula()).firstResult();
            if (existente != null) {
                throw new IllegalArgumentException("Ya existe un vendedor con la cédula " + c.getCedula());
            }
            nuevo.setCedula(c.getCedula());
        }
        if (c.getNombres() != null)    nuevo.setNombres(c.getNombres());
        if (c.getApellidos() != null) nuevo.setApellidos(c.getApellidos());
        this.er.getEntityManager().merge(nuevo);
    }

    public Vendedor buscarPorCedula(String cedula) {
        return this.er.buscarPorCedula(cedula);
    }

}
