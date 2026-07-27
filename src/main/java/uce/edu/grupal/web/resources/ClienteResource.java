package uce.edu.grupal.web.resources;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.grupal.application.service.ClienteService;
import uce.edu.grupal.domain.model.Cliente;

@Path("/clientes")
public class ClienteResource {

    @Inject
    private ClienteService cs;

    @GET
    public List<Cliente> obtenerTodos() {
        return this.cs.buscarTodos();
    }

    @GET
    @Path("/{cedula}")
    public Cliente obtenerPorCedula(@PathParam("cedula") String cedula) {
        return this.cs.buscarPorCedula(cedula);
    }

    @POST
    public void guardar(Cliente cliente) {
        this.cs.guardar(cliente);
    }

    @PUT
    public void actualizar(Cliente cliente) {
        this.cs.actualizar(cliente);
    }

    @DELETE
    @Path("/{cedula}")
    public void eliminar(@PathParam("cedula") String cedula) {
        this.cs.eliminar(cedula);
    }
}