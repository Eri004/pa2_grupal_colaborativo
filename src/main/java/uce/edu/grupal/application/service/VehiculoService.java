package uce.edu.grupal.application.service;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.grupal.domain.model.Vehiculo;
import uce.edu.grupal.infrastructure.repository.VehiculoRepositoryImpl;

@ApplicationScoped
@Transactional
public class VehiculoService {

    @Inject
    private VehiculoRepositoryImpl vr;

    public void guardar(Vehiculo vehiculo) {
        this.vr.persist(vehiculo);
    }

    public void actualizarPorPlaca(String placa, Vehiculo vehiculo) {
        if (placa == null || placa.isBlank()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacia");
        }
        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehiculo no puede ser nulo");
        }
        Vehiculo existente = this.vr.buscarPorPlaca(placa);
        if (existente == null) {
            throw new IllegalArgumentException("Vehiculo con placa " + placa + " no encontrado");
        }
        this.actualizar(existente.getId(), vehiculo);
    }

    public void actualizar(Integer id, Vehiculo c) {
        if (c == null) {
            throw new IllegalArgumentException("El vehiculo no puede ser nulo");
        }
        Vehiculo nuevo = this.vr.findById(id);
        if (nuevo == null) {
            throw new IllegalArgumentException("Vehiculo con id " + id + " no encontrado");
        }
        if (c.getPlaca() != null && !c.getPlaca().equals(nuevo.getPlaca())) {
            Vehiculo existe = this.vr.find("placa", c.getPlaca()).firstResult();
            if (existe != null) {
                throw new IllegalArgumentException("Ya existe un vehiculo con la placa " + c.getPlaca());
            }
            nuevo.setPlaca(c.getPlaca());
        }
        if (c.getMarca() != null)  nuevo.setMarca(c.getMarca());
        if (c.getModelo() != null) nuevo.setModelo(c.getModelo());
        this.vr.getEntityManager().merge(nuevo);
    }

    public Vehiculo buscarPorId(Integer id) {
        return this.vr.findById(id);
    }

    public List<Vehiculo> buscarTodos() {
        return this.vr.listAll();
    }

    public Vehiculo buscarPorPlaca(String placa){

        return this.vr.buscarPorPlaca(placa);
        
    }

    public void eliminar(Integer id){
        Vehiculo vehiculo = this.buscarPorId(id);
        this.vr.delete(vehiculo);
    }
    
    public void eliminarPorPlaca(String placa) {
        Vehiculo v = this.vr.buscarPorPlaca(placa);
        this.vr.delete(v);
    }

}
