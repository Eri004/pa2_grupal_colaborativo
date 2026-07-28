package uce.edu.ec.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.ec.application.service.VehiculoService;
import uce.edu.ec.domain.model.Vehiculo;

@Path("/vehiculos")
public class VehiculoResource {
    @Inject
    private VehiculoService vehiculoService;
    @Path("/guardar")
    @POST
    public void guardar(Vehiculo vehiculo){
        this.vehiculoService.guardar(vehiculo);
    }

    @Path("/actualizar/{placa}")
    @PUT
    public void actualizar(Vehiculo vehiculo, @PathParam("placa") String placa){
        this.vehiculoService.actualizar(placa, vehiculo);
    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.vehiculoService.eliminar(id);
    }

    @Path("/buscarPlaca/{placa}")
    @GET
    public Vehiculo buscarPorPlaca(@PathParam("placa") String placa){
        return this.vehiculoService.buscarPorPlaca(placa);
    }

}
