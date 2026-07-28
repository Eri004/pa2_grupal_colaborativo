package uce.edu.ec.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import uce.edu.ec.domain.model.Marca;
import uce.edu.ec.infraestructure.repository.MarcaRepositoryImpl;

@ApplicationScoped
@Transactional
public class MarcaService {
    @Inject
    private MarcaRepositoryImpl mri;

    public void guardar(Marca marca){
        this.mri.persist(marca);
    }
    public Marca buscarPorId(Integer id){
        return this.mri.findById(id);
    }
    public void actualizar(Marca marca, Integer id){
        Marca marcaBase = this.buscarPorId(id);
        if (marcaBase == null) {
            return;
        }
        marcaBase.setEmpresa(marca.getEmpresa());
        marcaBase.setNombre(marca.getNombre());
        marcaBase.setPais(marca.getPais());

    }
    public void eliminar(Integer id){
        this.mri.deleteById(id);
    }

}
