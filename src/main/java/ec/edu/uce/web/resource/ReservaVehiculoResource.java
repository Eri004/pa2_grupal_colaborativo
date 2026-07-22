package ec.edu.uce.web.resource;

import java.util.List;

import ec.edu.uce.application.service.ReservaVehiculoService;
import ec.edu.uce.domain.model.Cliente;
import ec.edu.uce.domain.model.ReservaVehiculo;
import ec.edu.uce.domain.model.Vehiculo;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/reserva-vehiculo")
public class ReservaVehiculoResource {

    @Inject
    private ReservaVehiculoService reservaVehiculoService;

    @POST
    @Path("/guardar")
    public void guardar(ReservaVehiculo reservaVehiculo){
        this.reservaVehiculoService.guardar(reservaVehiculo);
    }

    @GET
    @Path("/buscarTodos")
    public List<ReservaVehiculo> buscarTodos(){
        return this.reservaVehiculoService.buscarTodos();
    }

    @GET
    @Path("/buscarPorPlaca/{placa}") 
    public List<ReservaVehiculo> buscarPorPlaca(@PathParam("placa") String placa) { 
        return this.reservaVehiculoService.buscarPorPlaca(placa);   
    }

    @DELETE
    @Path("/eliminar/{id}")
    public void eliminar(@PathParam("id") Integer id){
        this.reservaVehiculoService.eliminar(id);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(ReservaVehiculo reservaVehiculo, @PathParam("id") Integer id){
        this.reservaVehiculoService.actualizar(id, reservaVehiculo);
    }

}

