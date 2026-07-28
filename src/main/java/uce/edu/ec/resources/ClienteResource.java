package uce.edu.ec.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import uce.edu.ec.application.service.ClienteService;
import uce.edu.ec.domain.model.Cliente;

@Path("/clientes")
public class ClienteResource {

    @Inject
    private ClienteService clienteService;
    @Path("/guardar")
    @POST
    public void guardar(Cliente cliente) {
        clienteService.guardar(cliente);
    }
    @Path("/buscarPorCedula/{cedula}")
    @GET
    public Cliente buscarPorCedula(@PathParam("cedula") String cedula) {
        return this.clienteService.buscarPorCedula(cedula);
    }
    @Path("/actualizar/{cedula}")
    @PUT
    public void actualizar(Cliente cliente, @PathParam("cedula") String cedula) {
        this.clienteService.actualizar(cedula, cliente);
    }
    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id) {
        this.clienteService.eliminar(id);
    }

}
