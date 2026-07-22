package ec.edu.uce.web.resource;

import java.util.List;

import ec.edu.uce.application.service.VehiculoService;
import ec.edu.uce.domain.model.Vehiculo;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


@Path("/vehiculo")
public class VehiculoResource {

    @Inject
    private VehiculoService vehiculoService;

    @POST
    @Path("/guardar")
    public void guardar(Vehiculo vehiculo) {
        this.vehiculoService.guardar(vehiculo);
    }

    @GET
    @Path("/buscarTodos")
    public List<Vehiculo> buscarTodos() {
        return this.vehiculoService.buscarTodos();
    }
    
    @DELETE
    @Path("/eliminar")
    public void eliminar(@PathParam("id") Integer id) {
        this.vehiculoService.eliminar(id);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(Vehiculo vehiculo, @PathParam("id") Integer id) {
        this.vehiculoService.actualizar(id, vehiculo);
    }
}
