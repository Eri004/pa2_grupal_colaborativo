package uce.edu.ec.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.ec.application.service.ReservaVehiculoService;
import uce.edu.ec.domain.model.ReservaVehiculo;

@Path("reservas")
public class ReservaVehiculoResource {
    @Inject
    private ReservaVehiculoService reservaVehiculoService;

    @Path("/buscarId/{id}")
    @GET
    public ReservaVehiculo buscarPorId(@PathParam("id") Integer id){
        return this.reservaVehiculoService.buscarPorId(id);
    }

    @Path("/guardar")
    @POST
    public void guardar(ReservaVehiculo reserva){
        this.reservaVehiculoService.guardar(reserva);
    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.reservaVehiculoService.eliminar(id);
    }

    @Path("/actualizar/")
    @PUT
    public void actualizar(){

    }
    

}
