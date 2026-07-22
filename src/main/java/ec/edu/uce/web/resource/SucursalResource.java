package ec.edu.uce.web.resource;

import java.util.List;

import ec.edu.uce.application.service.SucursalService;
import ec.edu.uce.domain.model.Sucursal;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/sucursal")
public class SucursalResource {

    @Inject
    private SucursalService sucursalService;

    @Path("/todos")
    @GET
    public List<Sucursal> buscarTodos(){
        return this.sucursalService.buscarTodos();
    }

    @Path("/porId/{id}")
    @GET
    public Sucursal buscarPorId(@PathParam("id") Integer id){
        return this.sucursalService.buscarPorIdSuc(id );
    }
    
    @Path("/guardarSuc")
    @POST
    public void guardar(Sucursal sucursal){
        this.sucursalService.guardarSuc(sucursal);
    }

    @Path("/actualizarSuc/{id}")
    @PUT
    public void actualizar(Sucursal sucursal, @PathParam("id") Integer id){
        this.sucursalService.actualizarSuc(sucursal, id);
    }

    @Path("/eliminarSuc/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.sucursalService.eliminarPorId(id);
    }
 

}
