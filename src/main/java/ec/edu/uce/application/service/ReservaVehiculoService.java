package ec.edu.uce.application.service;

import java.util.List;

import ec.edu.uce.application.interceptor.Auditar;
import ec.edu.uce.domain.model.ReservaVehiculo;
import ec.edu.uce.infraestructure.repository.ReservaVehiculoRepositoryImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Transactional
@ApplicationScoped
public class ReservaVehiculoService {

    @Inject
    private ReservaVehiculoRepositoryImpl reservaRepo;

    @Inject
    private ClienteService cliService;

    @Inject
    private VehiculoService vehiculoService;

    @Auditar
    public void guardar(ReservaVehiculo reservaVehiculo){
        this.reservaRepo.persist(reservaVehiculo);
    }

    @Auditar
    public ReservaVehiculo buscarPorId(Integer id){
        return this.reservaRepo.findById(id);
    }

    @Auditar
    public void actualizar(Integer id, ReservaVehiculo reservaVehiculo){
        ReservaVehiculo res = this.buscarPorId(id);
        if(res != null){
            res.setCliente(reservaVehiculo.getCliente());
            res.setCedula(reservaVehiculo.getCedula());
            res.setFechaReserva(reservaVehiculo.getFechaReserva());
            res.setGarantia(reservaVehiculo.getGarantia());
            res.setPlacaVehiculo(reservaVehiculo.getPlacaVehiculo());
            res.setServiciosAdicionales(reservaVehiculo.getServiciosAdicionales());
            res.setVehiculo(reservaVehiculo.getVehiculo());
        }
    }

    @Auditar
    public void eliminar(Integer id){
        ReservaVehiculo reservaVehiculo = this.buscarPorId(id);
        this.reservaRepo.delete(reservaVehiculo);
    }

    @Auditar
    public List<ReservaVehiculo> buscarTodos(){
        return this.reservaRepo.listAll();
    }

    @Auditar
    public List<ReservaVehiculo> buscarPorPlaca(String placa){
        return this.reservaRepo.buscarPorPlaca(placa);
    }



}
