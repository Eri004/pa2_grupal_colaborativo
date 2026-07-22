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

    @Path("/todos")
    @GET
    public List<Vehiculo> buscarTodos(){
        return this.vehiculoService.buscarTodos();
    }

    @Path("/porId/{id}")
    @GET
    public Vehiculo buscarPorId(@PathParam("id") Integer id){
        return this.vehiculoService.buscarPorIdVeh(id );
    }
    
    @Path("/guardarVeh")
    @POST
    public void guardar(Vehiculo vehiculo){
        this.vehiculoService.guardarVeh(vehiculo);
    }

    @Path("/actualizarVeh/{id}")
    @PUT
    public void actualizar(Vehiculo vehiculo, @PathParam("id") Integer id){
        this.vehiculoService.actualizarVeh(vehiculo, id);
    }

    @Path("/eliminarVeh/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.vehiculoService.eliminarPorId(id);
    }
 

}
