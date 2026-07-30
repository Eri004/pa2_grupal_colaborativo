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

    public void actualizar(Integer id, Vendedor c){
        Vendedor nuevo = this.er.findById(id);
        nuevo.setCedula(c.getCedula());
    }

    public Vendedor buscarPorId(Integer id) {
        return this.er.findById(id);
    }

    public Vendedor buscarPorCedula(String cedula) {
        return this.er.buscarPorCedula(cedula);
    }

    public List<Vendedor> buscarTodos() {
        return this.er.listAll();
    }

    public void eliminarPorCedula(String cedula) {
        Vendedor e = this.er.buscarPorCedula(cedula);
        this.er.delete(e);
    }

    public void eliminar(Integer id){
        Vendedor vendedor = this.buscarPorId(id);
        this.er.delete(vendedor);
    }



}
