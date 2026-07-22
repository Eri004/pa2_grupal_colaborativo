package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.domain.model.ServicioAdicional;
import ec.edu.uce.infraestructure.repository.ServicioAdicionalRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ServicioAdicionalService {

    @Inject
    private ServicioAdicionalRepositoryImpl servicioRepo;

    public void guardar(ServicioAdicional servicioAdicional){
        this.servicioRepo.persist(servicioAdicional);
    }
    public ServicioAdicional buscarPorId(Integer id){
        return this.servicioRepo.findById(id);
    }

    public void actualizar(Integer id, ServicioAdicional servicioAdicional){
        ServicioAdicional serv = this.buscarPorId(id);
        if(serv != null){
            serv.setNombre(servicioAdicional.getNombre());
            serv.setCosto(servicioAdicional.getCosto());
            serv.setDescripcion(servicioAdicional.getDescripcion());
        }
    }

    public void eliminar(Integer id){
        ServicioAdicional servicioAdicional = this.buscarPorId(id);
        this.servicioRepo.delete(servicioAdicional);
    }

    public List<ServicioAdicional> buscarTodos(){
        return this.servicioRepo.listAll();
    }


}
