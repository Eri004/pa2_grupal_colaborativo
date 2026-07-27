package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Empleado;
import uce.edu.grupal.infrastructure.repository.EmpleadoRepositoryImpl;

@ApplicationScoped
@Transactional
public class EmpleadoService {

    @Inject
    private EmpleadoRepositoryImpl er;

    public void guardar(Empleado empleado) {
        empleado.setEstado("ACTIVO");
        this.er.persist(empleado);
    }

    public Empleado buscarPorId(Integer id) {
        return this.er.findById(id);
    }

    public List<Empleado> buscarTodos() {
        return this.er.listAll();
    }

    public void eliminar(String cedula) {
        Empleado e = this.er.buscarPorCedula(cedula);
        e.setEstado("INACTIVO");
    }

    public void actualizar(Empleado empleado) {

        Empleado e = this.er.buscarPorCedula(empleado.getCedula());

        e.setNombres(empleado.getNombres());
        e.setApellidos(empleado.getApellidos());
        e.setCargo(empleado.getCargo());
        e.setTelefono(empleado.getTelefono());

    }

    public Empleado buscarPorCedula(String cedula) {
        return this.er.buscarPorCedula(cedula);
    }
}
