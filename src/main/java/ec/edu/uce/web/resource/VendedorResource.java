package ec.edu.uce.web.resource;

import java.util.List;

import ec.edu.uce.application.service.VendedorService;
import ec.edu.uce.domain.model.Vendedor;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/vendedor")
public class VendedorResource {

    @Inject
    private VendedorService vendedorService;

    @Path("/todos")
    @GET
    public List<Vendedor> buscarTodos(){
        return this.vendedorService.buscarTodos();
    }

    @Path("/porId/{id}")
    @GET
    public Vendedor buscarPorId(@PathParam("id") Integer id){
        return this.vendedorService.buscarPorIdVen(id );
    }
    
    @Path("/guardarVend")
    @POST
    public void guardar(Vendedor vendedor){
        this.vendedorService.guardarVen(vendedor);
    }

    @Path("/actualizarVend/{id}")
    @PUT
    public void actualizar(Vendedor vendedor, @PathParam("id") Integer id){
        this.vendedorService.actualizarVen(vendedor, id);
    }

    @Path("/eliminarVend/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.vendedorService.eliminarPorId(id);
    }
 

}
