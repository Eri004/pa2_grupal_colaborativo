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
        vehiculo.setEstado("DISPONIBLE");
        this.vr.persist(vehiculo);
    }

    public Vehiculo buscarPorId(Integer id) {
        return this.vr.findById(id);
    }

    public Vehiculo buscarPorPlaca(String placa) {
        return this.vr.buscarPorPlaca(placa);
    }

    public List<Vehiculo> buscarTodos() {
        return this.vr.listAll();
    }

    public void actualizar(Vehiculo vehiculo) {
        Vehiculo v = this.vr.buscarPorPlaca(vehiculo.getPlaca());

        v.setPlaca(vehiculo.getPlaca());
        v.setMarca(vehiculo.getMarca());
        v.setModelo(vehiculo.getModelo());
        v.setColor(vehiculo.getColor());
        v.setAnio(vehiculo.getAnio());
        v.setEstado(vehiculo.getEstado());
    }

    public void eliminar(String placa) {
        Vehiculo v = this.vr.buscarPorPlaca(placa);
        if (v == null) {
            throw new RuntimeException("Vehículo no encontrado");
        }
        v.setEstado("INACTIVO");

    }
}
