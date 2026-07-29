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

    
    @Path("/eliminarPorCedula/{cedula}")
    @DELETE
    public void eliminarPorCedula(@PathParam("cedula") String cedula){
        this.cs.eliminarPorCedula(cedula);
    }

    @Path("/buscarTodos")
    @GET
    public List<Cliente> buscarTodos(){
        return this.cs.buscarTodos();
    }

    @Path("/porCedula/{cedula}")
    @GET
    public Cliente buscarPorCedula(@PathParam("cedula") String cedula){
        return this.cs.buscarPorCedula(cedula);
    }    


}