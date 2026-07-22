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
import jakarta.ws.rs.QueryParam;

@Path("/cliente")
public class ClienteResource {

    @Inject
    private ClienteService clienteService;

    @POST
    @Path("/guardar")
    public void guardar(Cliente cliente) {
        this.clienteService.guardar(cliente);
    }

    @GET
    @Path("/buscarTodos")
    public List<Cliente> buscarTodos() {
        return this.clienteService.buscarTodos();
    }

    @DELETE
    @Path("/eliminar")
    public void eliminar(@QueryParam("id") Integer id) {
        this.clienteService.eliminar(id);
    }

    @PUT
    @Path("/actualizar/{id}")
    public void actualizar(Cliente cliente, @PathParam("id") Integer id) {
        this.clienteService.actualizar(id, cliente);
    }
    

}
