package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.domain.model.Auditoria;
import ec.edu.uce.infraestructure.repository.AuditoriaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class AuditoriaService {

    @Inject
    private AuditoriaRepositoryImpl auditRepo;


    public void guardar(Auditoria auditoria){
        this.auditRepo.persist(auditoria);
    }


    public Auditoria buscarPorId(Integer id){
        return this.auditRepo.findById(id);
    }


    public void actualizar(Integer id, Auditoria auditoria){
       Auditoria aud = this.buscarPorId(id);
       if(aud != null){
        aud.setArgumentos(auditoria.getArgumentos());
        aud.setFechaHoraEjecucion(auditoria.getFechaHoraEjecucion());
        aud.setNombreMetodo(auditoria.getNombreMetodo());
        aud.setTiempoEjecucion(auditoria.getTiempoEjecucion());
       }
    }

    public void eliminar(Integer id){
        Auditoria auditoria = this.buscarPorId(id);
        this.auditRepo.delete(auditoria);
    }

    public List<Auditoria> buscarTodos(){
        return this.auditRepo.findAll().list();
    }

}
