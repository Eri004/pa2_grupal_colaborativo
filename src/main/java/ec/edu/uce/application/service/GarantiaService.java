package ec.edu.uce.application.service;

import ec.edu.uce.domain.model.Garantia;
import ec.edu.uce.infraestructure.repository.GarantiaRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Transactional
@ApplicationScoped
public class GarantiaService {

    @Inject
    private GarantiaRepositoryImpl garantRepo;

    public void guardar(Garantia garantia){
        this.garantRepo.persist(garantia);
    }

    public Garantia buscarPorId(Integer id){
        return this.garantRepo.findById(id);
    }

    public void actualizar(Integer id, Garantia garantia){
        Garantia gar = this.buscarPorId(id);
        if(gar != null){
            gar.setEstado(garantia.getEstado());
            gar.setMetodoPago(garantia.getMetodoPago());
            gar.setMontoDeposito(garantia.getMontoDeposito());
        }
    }

    public void eliminar(Integer id){
        Garantia garantia = this.buscarPorId(id);
        this.garantRepo.delete(garantia);
    }

    public List<Garantia> buscarTodos(){
        return this.garantRepo.listAll();
    }

    public void procesarGarantia(Garantia garantia){
        garantia.setEstado("PROCESANDO");
        this.garantRepo.persist(garantia);

        System.out.println(Thread.currentThread().getName() + " - Procesando garantía con ID hilo: " + Thread.currentThread().getId());
    }

    public void procesarGarantiasExecutor(List<Garantia> garantias){
        System.out.println("=== [HILO PRINCIPAL REST/SERVICE] Nombre: " 
        + Thread.currentThread().getName() 
        + " | ID: " + Thread.currentThread().threadId() + " ===");
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try{
            for(Garantia g: garantias){
                Future<?> future = executor.submit(new TareaProcesarGarantia(this, g));
                future.get();
            }
        }catch (Exception e){
                e.printStackTrace();
        }finally{
                executor.shutdown();
        }
    }

}
