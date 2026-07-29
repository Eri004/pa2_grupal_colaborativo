package ec.edu.uce.web.service;

import ec.edu.uce.application.service.ClienteService;
import ec.edu.uce.domain.model.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/cliente")
public class ClienteResource {
    
    @Inject
    private ClienteService cs;

    @Path("/guardar")
    @POST
    public void guardar(Cliente c){

        this.cs.guardar(c);

    }

    @Path("/actualizar/{id}")
    @PUT
    public Cliente actualizar(@PathParam("id") Integer id, Cliente c){

        return this.cs.actualizar(id, c);

    }

    @Path("/porId/{id}")
    @GET
    public Cliente buscarPorId(@PathParam("id") Integer id){

        return this.cs.buscarPorId(id);

    }

    @Path("/eliminar/{id}")
    @DELETE
    public void eliminar(@PathParam("id") Integer id){

        this.cs.eliminar(id);

    }

}
