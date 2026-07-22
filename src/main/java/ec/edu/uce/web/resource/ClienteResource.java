package ec.edu.uce.web.resource;

import java.util.List;

import ec.edu.uce.application.service.ClienteService;
import ec.edu.uce.domain.model.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("cliente")
public class ClienteResource {

    @Inject
    private ClienteService clienteService;

    @Path("/todos")
    @GET
    public List<Cliente> buscarTodos(){
        return this.clienteService.buscarTodosCls();
    }

    @Path("/porId/{id}")
    @GET
    public String buscarPorId(@PathParam("id") Integer id){
        return this.clienteService.buscarPorIdReactivo(id );
    }
    
    @Path("/guardarCli")
    @POST
    public void guardar(Cliente cliente){
        this.clienteService.guardarClienteReactivo(cliente);
    }

    @Path("/actualizarCli/{id}")
    @PUT
    public void actualizar(Cliente cliente, @PathParam("id") Integer id){
        this.clienteService.actualizarCli(cliente, id);
    }

    @Path("/eliminarCli/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){
        this.clienteService.eliminarPorId(id);
    }
 

}
